package event;

import inv.main.MainMenuBuilder;
import inv.main.MilestoneItems;
import inv.master.MasterMenuBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class BookOpenListener implements Listener {
    private final JavaPlugin plugin;
    private final MainMenuBuilder mainMenuBuilder;
    private final NamespacedKey customId;
    private final NamespacedKey milestoneIdKey;
    private final MilestoneItems milestoneItems;
    public BookOpenListener(JavaPlugin plugin,MainMenuBuilder mainMenuBuilder, MilestoneItems milestoneItems){
        this.plugin = plugin;
        this.mainMenuBuilder = mainMenuBuilder;
        this.customId = new NamespacedKey(plugin, "custom_id");
        this.milestoneIdKey = new NamespacedKey(plugin, "milestone_1");
        this.milestoneItems = milestoneItems;
    }
    @EventHandler
    public void onBookOpen(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
       if (item == null || item.getType() != Material.BOOK) return;
        if(item.getType() == Material.BOOK && item != null){
           mainMenuBuilder.getMainMenu(e.getPlayer());
        }
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if(playerData.has(milestoneIdKey, PersistentDataType.STRING)){
            MasterMenuBuilder.openMasterMenu(player);
            player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 1.0f);
            return;
        }
    }
}
