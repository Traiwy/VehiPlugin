package traiwy.skillVehPlugin;

import event.BookOpenListener;
import inv.main.MainMenuBuilder;
import inv.main.MainMenuListener;
import inv.main.MilestoneItems;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillVehPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        MilestoneItems milestoneItems = new MilestoneItems(this);
        MainMenuBuilder mainMenuBuilder = new MainMenuBuilder(this, milestoneItems);
        getServer().getPluginManager().registerEvents(new BookOpenListener(mainMenuBuilder), this);
        getServer().getPluginManager().registerEvents(new MainMenuListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
