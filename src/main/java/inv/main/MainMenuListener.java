package inv.main;

import inv.master.MasterMenuBuilder;
import milestone.EntityDamageListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import util.MilestoneData;
import util.PlayerDataManager;

import java.util.*;

public class MainMenuListener implements Listener {
    private final MasterMenuBuilder masterMenuBuilder;
    private final Set<UUID> awaitingVeh = new HashSet<>();
    private final JavaPlugin plugin;
    private final NamespacedKey customIdKey;
    private final NamespacedKey milestoneIdKey;
    private final NamespacedKey tempMilestoneKey;
    private final NamespacedKey milestoneLevelKey;
    private Map<Player, MilestoneData> nameMilestone;
    private final PlayerDataManager playerDataManager;

    public MainMenuListener(MasterMenuBuilder masterMenuBuilder, JavaPlugin plugin, PlayerDataManager playerDataManager) {
        this.masterMenuBuilder = masterMenuBuilder;
        this.plugin = plugin;
        this.customIdKey = new NamespacedKey(plugin, "custom_id");
        this.milestoneIdKey = new NamespacedKey(plugin, "milestone_1");
        this.tempMilestoneKey = new NamespacedKey(plugin, "temp_milestone");
        this.milestoneLevelKey = new NamespacedKey(plugin, "milestone_1_level");
        this.playerDataManager = playerDataManager;
        this.nameMilestone = new HashMap<>();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        MilestoneData milestoneData = playerDataManager.loadPlayerMilestone(player);
        if (milestoneData != null) {
            nameMilestone.put(player, milestoneData);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        nameMilestone.remove(player);
        awaitingVeh.remove(player.getUniqueId());
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        InventoryHolder holder = e.getInventory().getHolder();

        if (!(holder instanceof MainHolder)) return;
        e.setCancelled(true);

        if (item == null || !item.hasItemMeta()) return;
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        String customId = container.get(customIdKey, PersistentDataType.STRING);
        if (playerData.has(milestoneIdKey, PersistentDataType.STRING)) {
            player.sendMessage(Component.text("Вы уже выбрали веху. Изменить её невозможно", NamedTextColor.RED));
            return;
        }
        if (customId != null && customId.startsWith("milestone_")) {
            clickItem(player, item, customId, e.getInventory(), e);
        } else if ("confirm_selection".equals(customId) && item.getType() == Material.EMERALD_BLOCK) {
            String tempMilestone = playerData.get(tempMilestoneKey, PersistentDataType.STRING);
            if (tempMilestone == null) {
                player.sendMessage(Component.text("Ошибка: веха не выбрана", NamedTextColor.RED));
                return;
            }

            int level = 1;
            nameMilestone.put(player, new MilestoneData(tempMilestone, level));
            playerDataManager.savePlayerMilestone(player, tempMilestone, level);
            confirmationItem(player);
        }
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) return;
        Player player = (Player) e.getPlayer();
        InventoryHolder holder = e.getInventory().getHolder();
        if (!(holder instanceof MainHolder)) return;

        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if (playerData.has(tempMilestoneKey, PersistentDataType.STRING)) {
            playerData.remove(tempMilestoneKey);
            playerData.remove(milestoneLevelKey);
            awaitingVeh.remove(player.getUniqueId());
        }
    }

    private void clickItem(Player player, ItemStack item, String customId, Inventory inventory, InventoryClickEvent e) {
        if (item == null || !item.hasItemMeta()) return;
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if (playerData.has(milestoneIdKey, PersistentDataType.STRING) || playerData.has(tempMilestoneKey, PersistentDataType.STRING)) {
            player.sendMessage(Component.text("Вы уже выбрали веху", NamedTextColor.RED));
            player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
            return;
        }
        UUID target = player.getUniqueId();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        item.setItemMeta(meta);
        inventory.setItem(e.getSlot(), item);
        playerData.set(tempMilestoneKey, PersistentDataType.STRING, customId);
        playerData.set(milestoneLevelKey, PersistentDataType.INTEGER, 1);

        awaitingVeh.add(target);
        player.sendMessage(Component.text("Вы выбрали веху: " + meta.displayName() + ". Нажмите на изумрудный блок для подтверждения.", NamedTextColor.GREEN));
        player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
    }

    private void confirmationItem(Player player) {
        UUID target = player.getUniqueId();
        if (!awaitingVeh.contains(target)) {
            player.sendMessage(Component.text("Сначала выберите веху", NamedTextColor.RED));
            return;
        }
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        String tempMilestone = playerData.get(tempMilestoneKey, PersistentDataType.STRING);
        if (tempMilestone == null) {
            player.sendMessage(Component.text("Ошибка! Веха не выбрана", NamedTextColor.RED));
            return;
        }
        int level = 1;
        nameMilestone.put(player, new MilestoneData(tempMilestone, level));
        playerDataManager.savePlayerMilestone(player, tempMilestone, level);
        playerData.remove(tempMilestoneKey);
        awaitingVeh.remove(target);
        masterMenuBuilder.openMasterMenu(player);
        player.sendMessage(Component.text("Выбор вехи подтверждён!", NamedTextColor.GREEN));
    }

    public Set<UUID> getAwaitingVeh() {
        return awaitingVeh;
    }

    public Map<Player, MilestoneData> getNameMilestone() {
        return Collections.unmodifiableMap(nameMilestone);
    }
    public void setPlayerMilestone(Player player, MilestoneData data) {
    nameMilestone.put(player, data);
}
}