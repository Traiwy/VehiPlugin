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
import util.PlayerDataManager;

public class BookOpenListener implements Listener {
    private final JavaPlugin plugin;
    private final MasterMenuBuilder masterMenuBuilder;
    private final MainMenuBuilder mainMenuBuilder;
    private final NamespacedKey customId;
    private final NamespacedKey milestoneIdKey;
    private final MilestoneItems milestoneItems;
    private final PlayerDataManager playerDataManager;
    public BookOpenListener(JavaPlugin plugin, MasterMenuBuilder masterMenuBuilder, MainMenuBuilder mainMenuBuilder, MilestoneItems milestoneItems, PlayerDataManager playerDataManager){
        this.plugin = plugin;
        this.masterMenuBuilder = masterMenuBuilder;
        this.mainMenuBuilder = mainMenuBuilder;
        this.customId = new NamespacedKey(plugin, "custom_id");
        this.milestoneIdKey = new NamespacedKey(plugin, "milestone_1");
        this.milestoneItems = milestoneItems;
        this.playerDataManager = playerDataManager;
    }
    @EventHandler
    public void onBookOpen(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
       if (item == null || item.getType() != Material.BOOK) return;
        if(item.getType() == Material.BOOK && item != null && !playerDataManager.hasPlayerMilestone(player)) {
            mainMenuBuilder.getMainMenu(e.getPlayer());
        }
        if(playerDataManager.hasPlayerMilestone(player)){
            masterMenuBuilder.openMasterMenu(player);
            player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 1.0f);
            return;
        }
    }
}
