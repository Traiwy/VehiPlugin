package milestone;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import util.MilestoneData;
import util.MilestonesConfigManager;
import util.PlayerDataManager;

public class VampirismListener implements Listener {
    private final PlayerDataManager playerDataManager;
    private final MilestonesConfigManager milestonesConfigManager;

    public VampirismListener(PlayerDataManager playerDataManager, MilestonesConfigManager milestonesConfigManager) {
        this.playerDataManager = playerDataManager;
        this.milestonesConfigManager = milestonesConfigManager;
    }

    @EventHandler
    public void onVampirismEvent(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof LivingEntity)) return;
        Player player = (Player) e.getDamager();

        MilestoneData data = playerDataManager.loadPlayerMilestone(player);
       if (data == null) return;
       if (data == null || !data.getCustomId().equals("milestone_vampirism")) return;

        int level = data.getLevel();
        double vampirismPercent  = milestonesConfigManager.getMilestoneValue("vampirism", level);
        double damage = e.getFinalDamage();

        double healAmount = damage * vampirismPercent;
        double newHealth = Math.min(player.getHealth() + healAmount, player.getMaxHealth());
        player.setHealth(newHealth);

        player.sendActionBar("§cВампиризм: +" + String.format("%.1f", healAmount) + "❤");
    }
}
