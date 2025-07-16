package inv.master;

import inv.main.MainHolder;
import inv.main.MainMenuListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import util.MilestoneData;
import util.MilestonesConfigManager;
import util.PlayerDataManager;
import util.TaskConfigManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collector;

public class MasterMenuBuilder {
    private final JavaPlugin plugin;
   private final MilestonesConfigManager milestonesConfigManager;
    private MainMenuListener mainMenuListener;
    private final TaskConfigManager taskConfigManager;
    private final PlayerDataManager playerDataManager;

    public MasterMenuBuilder(JavaPlugin plugin, MilestonesConfigManager milestonesConfigManager, MainMenuListener mainMenuListener, TaskConfigManager taskConfigManager, PlayerDataManager playerDataManager) {
        this.plugin = plugin;
        this.milestonesConfigManager = milestonesConfigManager;
        this.mainMenuListener = mainMenuListener;
        this.taskConfigManager = taskConfigManager;
        this.playerDataManager = playerDataManager;
    }

    public void setMainMenuListener(MainMenuListener mainMenuListener) {
        this.mainMenuListener = mainMenuListener;
    }

    public void openMasterMenu(Player player) {
        Inventory inv = Bukkit.createInventory(new MasterHolder(), 54, Component.text("Главное меню"));

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemStack yellowConcrete = new ItemStack(Material.YELLOW_CONCRETE);
        ItemStack redConcrete = new ItemStack(Material.RED_CONCRETE);
        ItemMeta meta = item.getItemMeta();


        Component displayName = Component.text(player.getName())
                .color(NamedTextColor.WHITE)
                .decoration(TextDecoration.ITALIC, false);
        meta.displayName(displayName);

        if (mainMenuListener == null) {
            player.sendMessage(Component.text("Ошибка: mainMenuListener не инициализирован", NamedTextColor.RED));
            return;
        }

        MilestoneData data = playerDataManager.loadPlayerMilestone(player);
        if (data != null) {
            String customId = data.getCustomId();
            int level = data.getLevel();
            String milestoneName = customId.replace("milestone_", "");
            String loreText;
            switch (level) {
                case 1:
                    loreText = milestonesConfigManager.getTitle1Level(milestoneName);
                    break;
                case 2:
                    loreText = milestonesConfigManager.getTitle2Level(milestoneName);
                    break;
                case 3:
                    loreText = milestonesConfigManager.getTitle3Level(milestoneName);
                    break;
                default:
                    loreText = "Неизвестный уроень вехи";
                    break;
            }
            meta.setLore(Collections.singletonList(loreText));
        }else{
            player.sendMessage("Ошибка, веха не найдена");
            meta.setLore(Collections.singletonList("§cВеха не выбрана"));
        }
        String taskName = data.getCustomId().replace("milestone_", "");
        String rarity = taskConfigManager.getRarityTask("milestones", taskName);

        int[] stainedGlass = {27,28, 29,30,31,32,33,34,35,45,46,47,48,49,50,51,52,53};


        item.setItemMeta(meta);
        inv.setItem(20, item);
        inv.setItem(15, yellowConcrete);
        inv.setItem(16, redConcrete);
        player.openInventory(inv);

    }
    public MainMenuListener getMainMenuListener() {
        return mainMenuListener;
    }
    private ItemStack getGlassByRarity(String rarity){
        Material material;
        switch (rarity.toLowerCase()){
            case "common":
                material = Material.LIME_STAINED_GLASS_PANE;
                break;
            case "uncommon":
                material = Material.YELLOW_STAINED_GLASS_PANE;
                break;
            case "rare":
                material = Material.BLUE_STAINED_GLASS_PANE;
                break;
            case "epic":
                material = Material.PURPLE_STAINED_GLASS_PANE;
                break;
            case "legendary":
                material = Material.ORANGE_STAINED_GLASS_PANE;
                break;
            default:
                material = Material.GRAY_STAINED_GLASS_PANE;
                break;
        }
        ItemStack pane = new ItemStack(material);

    }
}
