package command;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class ResetVehCommand implements CommandExecutor {
    private final NamespacedKey tempMilestoneKey;
    private final NamespacedKey milestoneLevelKey;
    private final NamespacedKey milestoneKey;
    private final JavaPlugin plugin;
    private final Set<UUID> awaitingVeh;
    public ResetVehCommand(JavaPlugin plugin, Set<UUID> awaitingVeh){
        this.awaitingVeh = awaitingVeh;
        this.plugin = plugin;
        this.milestoneKey = new NamespacedKey(plugin, "milestone_1");
        this.milestoneLevelKey = new NamespacedKey(plugin, "milestone_1_level");
        this.tempMilestoneKey =  new NamespacedKey(plugin, "temp_milestone");
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        boolean hadMilestone = playerData.has(tempMilestoneKey, PersistentDataType.STRING);
        playerData.remove(milestoneKey);
        playerData.remove(milestoneLevelKey);
        playerData.remove(tempMilestoneKey);
        awaitingVeh.remove(player.getUniqueId());

        if(hadMilestone){
            player.sendMessage("Все вехи были сброшены");
        }else{
            player.sendMessage("У вас не было вех");
        }
        return true;
    }
}
