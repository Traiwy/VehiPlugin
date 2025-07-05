package inv.main;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class MainMenuBuilder {
    private final JavaPlugin plugin;
    private final MilestoneItems milestoneItems;

    public MainMenuBuilder(JavaPlugin plugin, MilestoneItems milestoneItems) {
        this.plugin = plugin;
        this.milestoneItems = milestoneItems;
    }

    public void getMainMenu(Player player){
        Inventory inv = Bukkit.createInventory(new MainHolder(), 54, "Выберите веху");
        ItemStack[] milestoneIcons = {
            milestoneItems.createAcrobaticsItem(),
            milestoneItems.createSwiftTechniquesItem(),
            milestoneItems.createVampirismItem(),
            milestoneItems.createMasterAlchemistItem(),
            milestoneItems.createChiefProspectorItem(),
            milestoneItems.createMobSlayerItem(),
            milestoneItems.createEnlightenmentItem(),
            milestoneItems.createWarriorItem(),
            milestoneItems.createRegenerationItem(),
            milestoneItems.createRunnerItem(),
            milestoneItems.createIronWillItem(),
            milestoneItems.createMidasTouchItem(),
            milestoneItems.createInitiativeItem(),
            milestoneItems.createColossusItem(),
            milestoneItems.createIronSkinItem(),
            milestoneItems.createCrescendoItem(),
            milestoneItems.createPoisonerItem(),
            milestoneItems.createDevourItem(),
            milestoneItems.createRevengeItem(),
            milestoneItems.createMightItem(),
            milestoneItems.createImpatienceItem(),
            milestoneItems.createRejectionItem(),
            milestoneItems.createRepulsionItem(),
            milestoneItems.createFirstStrikeItem(),
            milestoneItems.createFullReachItem(),
            milestoneItems.createSwimmerItem(),
            milestoneItems.createAgilityItem(),
            milestoneItems.createFerocityItem(),
//            milestoneItems.createStrongmanItem(),
//            milestoneItems.createSpeedOfThoughtItem(),
//            milestoneItems.createSteelMusclesItem(),
//            milestoneItems.createGlassWeaponItem(),
//            milestoneItems.createPrecisionItem(),
//            milestoneItems.createHeavyArmorItem(),
//            milestoneItems.createDodgeItem(),
//            milestoneItems.createEliminationItem(),
//            milestoneItems.createChampionItem(),
//            milestoneItems.createPureLuckItem(),
//            milestoneItems.createCheatingItem()
        };
        int[] slotIndexes = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
         for(int i = 0; i < milestoneIcons.length && i < slotIndexes.length; i++){
            inv.setItem(slotIndexes[i], milestoneIcons[i]);
        }

        ItemStack emerald = milestoneItems.createrEmeraldItem();
        inv.setItem(53, emerald);
        player.openInventory(inv);
    }
}
