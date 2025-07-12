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
import util.MilestonesConfigManager;

public class EntityDamageListener implements Listener {
    private final JavaPlugin plugin;
    private final MilestonesConfigManager milestonesConfigManager;
    private final NamespacedKey milestoneIdKey;
    private final NamespacedKey milestoneLevelKey;
    private final NamespacedKey customIdKey;

    public EntityDamageListener(JavaPlugin plugin, MilestonesConfigManager milestonesConfigManager) {
        this.plugin = plugin;
        this.milestonesConfigManager = milestonesConfigManager;
        this.milestoneIdKey = new NamespacedKey(plugin, "milestone_1");
        this.milestoneLevelKey = new NamespacedKey(plugin, "milestone_1_level");
        this.customIdKey = new NamespacedKey(plugin, "custom_id");
    }

    @EventHandler
    public void onAcrobaticMilestones(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if (playerData.has(customIdKey, PersistentDataType.STRING)) {
            String customValue = playerData.get(customIdKey, PersistentDataType.STRING);
            if (customValue != null && customValue.equals("milestone_acrobatics")) {
                if (e.getDamager() instanceof AbstractArrow || e.getDamager() instanceof Trident) {
                    int level = player.getPersistentDataContainer().get(milestoneLevelKey, PersistentDataType.INTEGER);
                    double dodgeChance = milestonesConfigManager.getMilestoneValue("acrobatics", level);
                    if (dodgeChance > 0.0 && Math.random() < dodgeChance) {
                        e.setCancelled(true);
                        player.sendMessage("Вы улонились от атаки");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
                    }
                }

            }


        }
    }
}
