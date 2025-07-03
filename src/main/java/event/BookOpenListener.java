package event;

import inv.main.MainMenuBuilder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BookOpenListener implements Listener {
    private final MainMenuBuilder mainMenuBuilder;
    public BookOpenListener(MainMenuBuilder mainMenuBuilder){
        this.mainMenuBuilder = mainMenuBuilder;
    }
    @EventHandler
    public void onBookOpen(PlayerInteractEvent e){
        ItemStack item = e.getItem();
       if (item == null || item.getType() != Material.BOOK) return;
        if(item.getType() == Material.BOOK && item != null){
           mainMenuBuilder.getMainMenu(e.getPlayer());
        }
    }
}
