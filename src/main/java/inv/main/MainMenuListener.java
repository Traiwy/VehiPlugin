package inv.main;

import inv.master.MasterMenuBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MainMenuListener implements Listener {
    private final Set<UUID> awaitingVeh = new HashSet<>();
    private final JavaPlugin plugin;
    private final MilestoneItems milestoneItems;
    private final NamespacedKey customIdKey;
    private final NamespacedKey milestoneIdKey;
    private final NamespacedKey tempMilestoneKey;

    public MainMenuListener(JavaPlugin plugin, MilestoneItems milestoneItems) {
        this.plugin = plugin;
        this.milestoneItems = milestoneItems;
        this.customIdKey = new NamespacedKey(plugin, "custom_id");
        this.milestoneIdKey = new NamespacedKey(plugin, "milestone_1");
        this.tempMilestoneKey = new NamespacedKey(plugin, "temp_milestone");
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
            player.sendMessage("Вы уже выбрали веху. Изменить её невозможно");
            return;
        }
        if (customId != null && customId.startsWith("milestone_")) {
            clickItem(player, item, customId,e.getInventory(), e);
        } else if (("confirm_selection").equals(customId) && item.getType() == Material.EMERALD_BLOCK) {
            confirmationItem(player);
        }

    }
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e){
        if(!(e.getPlayer() instanceof Player)) return;
        Player player = (Player)e.getPlayer();

        InventoryHolder holder = e.getInventory().getHolder();
        if(!(holder instanceof  MainHolder)) return;

        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if(playerData.has(tempMilestoneKey, PersistentDataType.STRING)){
            playerData.remove(tempMilestoneKey);
            playerData.remove(tempMilestoneKey);
            awaitingVeh.remove(player.getUniqueId());
        }
    }

    private void clickItem(Player player, ItemStack item, String customId, Inventory inventory, InventoryClickEvent e) {
        if (item == null || !item.hasItemMeta()) return;
         PersistentDataContainer playerData = player.getPersistentDataContainer();
        if(playerData.has(milestoneIdKey, PersistentDataType.STRING) || playerData.has(new NamespacedKey(plugin, "temp_milestone"), PersistentDataType.STRING)){
            player.sendMessage("Вы уже выбрали веху");
            player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
            return;
        }
        UUID target = player.getUniqueId();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        item.setItemMeta(meta);
        inventory.setItem(e.getSlot(), item);
        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "temp_milestone"), PersistentDataType.STRING, customId);

        awaitingVeh.add(target);
        player.sendMessage("§aВы выбрали веху: " + meta.getDisplayName() + ". Нажмите на изумрудный блок для подтверждения.");

        player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);

    }

    private void confirmationItem(Player player) {
        UUID target = player.getUniqueId();
        if(!awaitingVeh.contains(target)){
            player.sendMessage("Сначала выберите веху");
            return;
        }
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        String tempMilestone = playerData.get(new NamespacedKey(plugin, "temp_milestone"), PersistentDataType.STRING);
        if(tempMilestone == null){
            player.sendMessage("Ошибка! Веха не выбрана");
            return;
        }
        playerData.set(milestoneIdKey, PersistentDataType.STRING, tempMilestone.replace("milestone_", ""));
        playerData.set(new NamespacedKey(plugin, "milestone_1_level"), PersistentDataType.INTEGER, 1);
        playerData.remove(new NamespacedKey(plugin, "temp_milestone"));
        awaitingVeh.remove(target);


        MasterMenuBuilder.openMasterMenu(player);
        player.sendMessage("§aВыбор вехи подтверждён!");
    }
    public Set<UUID> getAwaitingVeh(){
        return awaitingVeh;
    }
}


