package inv.master;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MasterMenuBuilder {
    public static void openMasterMenu(Player player){
        Inventory inv = Bukkit.createInventory(new MasterHolder(), 54, "Главное меню");
        player.openInventory(inv);
    }
}
