package inv.master;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MasterMenuBuilder {
    public static void openMasterMenu(Player player){
        Inventory inv = Bukkit.createInventory(new MasterHolder(), 54, "Главное меню");

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        String name = player.getName();
        Component displayName = Component.text(player.getName())
                .color(NamedTextColor.WHITE)
                .decoration(TextDecoration.ITALIC, false);
        meta.displayName(displayName);
        meta.setLore(Arrays.asList());
        item.setItemMeta(meta);

        inv.setItem(20, item);
        player.openInventory(inv);
    }
}
