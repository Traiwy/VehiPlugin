package inv.main;

import inv.master.MasterMenuBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MainMenuListener implements Listener {
    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        InventoryHolder holder = e.getInventory().getHolder();

        if (holder instanceof MainHolder) {
            e.setCancelled(true);
            switch (item.getType()) {
                case FEATHER, SHIELD, POTION, BREWING_STAND, DIAMOND_PICKAXE,
                     IRON_SWORD, EXPERIENCE_BOTTLE, DIAMOND_SWORD, GOLDEN_APPLE,
                     LEATHER_BOOTS, IRON_CHESTPLATE, GOLD_INGOT, CLOCK,
                     TOTEM_OF_UNDYING, LEATHER, RED_DYE, SPIDER_EYE, ROTTEN_FLESH,
                     GOLDEN_SWORD, ENCHANTED_BOOK, SPLASH_POTION, MILK_BUCKET,
                     DIAMOND, FISHING_ROD, HEART_OF_THE_SEA, DIAMOND_AXE,
                     IRON_AXE, BOW, DIAMOND_CHESTPLATE, GLASS, CROSSBOW, NETHERITE_CHESTPLATE,
                     FLINT, NETHER_STAR, EMERALD, BONE -> {
                    MasterMenuBuilder.openMasterMenu(player);
                }
            }


        }
    }
}
