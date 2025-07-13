package milestone;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import util.MilestoneData;
import util.MilestonesConfigManager;
import util.PlayerDataManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpeedEffectTask implements Runnable{
    private final PlayerDataManager playerDataManager;
    private final MilestonesConfigManager configManager;
    private final Set<Material> combatItems = new HashSet<>(Arrays.asList(
        Material.BOW,
        Material.SHIELD,
        Material.CROSSBOW,
        Material.TRIDENT
    ));

    public SpeedEffectTask(PlayerDataManager playerDataManager, MilestonesConfigManager configManager) {
        this.playerDataManager = playerDataManager;
        this.configManager = configManager;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack main = player.getInventory().getItemInMainHand();
            ItemStack off = player.getInventory().getItemInOffHand();

            boolean holdingCombatItem = combatItems.contains(main.getType()) || combatItems.contains(off.getType());

            if (!holdingCombatItem) {
                player.removePotionEffect(PotionEffectType.SPEED);
                continue;
            }

            MilestoneData data = playerDataManager.loadPlayerMilestone(player);
             if (data == null || !data.getCustomId().equals("milestone_swift_techniques")) return;

            double speedBonus = configManager.getMilestoneValue("swift_techniques", data.getLevel());
            if (speedBonus <= 0.0) {
                player.removePotionEffect(PotionEffectType.SPEED);
                continue;
            }

            int amplifier = (int)(speedBonus * 10) - 1;
            int durationTicks = 40;

            player.sendActionBar("Ускорение на " + speedBonus * 100 + "%");
            PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, durationTicks, amplifier, false, false);
            player.addPotionEffect(effect);
        }
    }
}
