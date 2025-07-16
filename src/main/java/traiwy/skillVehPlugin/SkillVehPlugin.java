package traiwy.skillVehPlugin;

import command.GiveLevelCommand;
import command.ResetVehCommand;
import event.BookOpenListener;
import inv.main.MainMenuBuilder;
import inv.main.MainMenuListener;
import inv.main.MilestoneItems;
import inv.master.MasterMenuBuilder;
import milestone.EntityDamageListener;
import milestone.SpeedEffectTask;
import milestone.VampirismListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import util.MilestonesConfigManager;
import util.PlayerDataManager;
import util.TaskConfigManager;

public final class SkillVehPlugin extends JavaPlugin {
    public  MainMenuListener mainMenuListener;
    @Override
    public void onEnable() {

        MilestoneItems milestoneItems = new MilestoneItems(this);
        MainMenuBuilder mainMenuBuilder = new MainMenuBuilder(this, milestoneItems);
        MilestonesConfigManager milestonesConfigManager = new MilestonesConfigManager(this);
        PlayerDataManager playerDataManager = new PlayerDataManager(this);
        TaskConfigManager taskConfigManager = new TaskConfigManager(this);

        MasterMenuBuilder masterMenuBuilder = new MasterMenuBuilder(this, milestonesConfigManager, null);
        mainMenuListener = new MainMenuListener(masterMenuBuilder, this, playerDataManager);
        masterMenuBuilder.setMainMenuListener(mainMenuListener);

        getServer().getPluginManager().registerEvents(new BookOpenListener(this, masterMenuBuilder, mainMenuBuilder, milestoneItems, playerDataManager), this);
        getServer().getPluginManager().registerEvents(mainMenuListener, this);
        getServer().getPluginManager().registerEvents( new EntityDamageListener(this, milestonesConfigManager, playerDataManager), this);
        Bukkit.getScheduler().runTaskTimer(this, new SpeedEffectTask(playerDataManager, milestonesConfigManager), 0L, 10L);
        getServer().getPluginManager().registerEvents(new VampirismListener(playerDataManager, milestonesConfigManager), this);
        getCommand("resetVeh").setExecutor(new ResetVehCommand(this, mainMenuListener.getAwaitingVeh(), playerDataManager));
        getCommand("vehlevel").setExecutor(new GiveLevelCommand(playerDataManager, masterMenuBuilder, mainMenuListener, this));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
