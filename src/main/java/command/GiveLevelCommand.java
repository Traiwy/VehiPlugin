package command;

import inv.main.MainMenuListener;
import inv.master.MasterMenuBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import util.MilestoneData;
import util.PlayerDataManager;

public class GiveLevelCommand implements CommandExecutor {
    private final PlayerDataManager playerDataManager;
    private final MasterMenuBuilder masterMenuBuilder;
    private final MainMenuListener mainMenuListener;
    private final JavaPlugin plugin;

    public GiveLevelCommand(PlayerDataManager playerDataManager, MasterMenuBuilder masterMenuBuilder, MainMenuListener mainMenuListener, JavaPlugin plugin) {
        this.playerDataManager = playerDataManager;
        this.masterMenuBuilder = masterMenuBuilder;
        this.mainMenuListener = mainMenuListener;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Введите уровень /vehlevel <уровень>");
            return true;
        }
        int level;
        try {
            level = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("Неверный формат уровня.");
            return true;
        }

        if (!playerDataManager.hasPlayerMilestone(player)) {
            player.sendMessage("У вас нет выбранной вехи");
            return true;
        }

        if (level < 1 || level > 3) {
            player.sendMessage("Уровень должен быть от 1 до 3.");
            return true;
        }

        playerDataManager.setPlayerLevel(player, level);

       MilestoneData newData = playerDataManager.loadPlayerMilestone(player);
       mainMenuListener.setPlayerMilestone(player, newData);

        masterMenuBuilder.openMasterMenu(player);

        return true;

    }
}
