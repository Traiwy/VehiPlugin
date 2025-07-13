package milestone;

import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import util.MilestoneData;
import util.MilestonesConfigManager;
import util.PlayerDataManager;

public class EntityDamageListener implements Listener {
    private final JavaPlugin plugin;
    private final MilestonesConfigManager milestonesConfigManager;
    private final NamespacedKey milestoneIdKey;
    private final NamespacedKey milestoneLevelKey;
    private final NamespacedKey customIdKey;
    private final PlayerDataManager playerDataManager;

    public EntityDamageListener(JavaPlugin plugin, MilestonesConfigManager milestonesConfigManager, PlayerDataManager playerDataManager) {
        this.plugin = plugin;
        this.milestonesConfigManager = milestonesConfigManager;
        this.milestoneIdKey = new NamespacedKey(plugin, "milestone_1");
        this.milestoneLevelKey = new NamespacedKey(plugin, "milestone_1_level");
        this.customIdKey = new NamespacedKey(plugin, "custom_id");
        this.playerDataManager = playerDataManager;
    }

    @EventHandler
    public void onAcrobaticMilestones(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        MilestoneData data = playerDataManager.loadPlayerMilestone(player);
        if (data == null || !data.getCustomId().equals("milestone_acrobatics")) return;
        if(!(e.getDamager() instanceof AbstractArrow || !(e.getDamager() instanceof Trident))) return;
        if (e.getDamager() instanceof AbstractArrow || e.getDamager() instanceof Trident) {
            int level = data.getLevel();
            double dodgeChance = milestonesConfigManager.getMilestoneValue("acrobatics", level);
            double roll = Math.random();
            String percent = ("Шанс уклонения: " + (int)(dodgeChance * 100) + "% | Ролл: " + String.format("%.2f", roll));
            if (dodgeChance > 0.0 && roll < dodgeChance) {
                e.setCancelled(true);
                player.sendActionBar("Вы уклонились от атаки. " + percent);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
            }else {
                player.sendActionBar("Вы не смогли уклониться от атаки. " + percent);
            }
        }
    }

}
