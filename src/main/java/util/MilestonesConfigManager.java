package util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MilestonesConfigManager {
    private final JavaPlugin plugin;
    private final Map<String, Map<Integer, Double>> milestoneLevels;
    private File milestoneFile;
    private FileConfiguration milestoneConfig;


    public MilestonesConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.milestoneLevels = new HashMap<>();
        this.milestoneFile = new File(plugin.getDataFolder(),"milestones.yml");
        this.milestoneConfig = YamlConfiguration.loadConfiguration(milestoneFile);
    }
    private void loadConfig(){
        if(!milestoneFile.exists()){
            plugin.saveResource("milestones.yml", false);
            milestoneConfig = YamlConfiguration.loadConfiguration(milestoneFile);
        }

        ConfigurationSection milestonesSection = milestoneConfig.getConfigurationSection("milestones");
        if(milestonesSection == null){
            plugin.getLogger().warning("No 'milestones' section found in milestone.yml. Using default values.");
            plugin.saveDefaultConfig();
            milestonesSection = milestoneConfig.getConfigurationSection("milestones");
        }
        for(String milestone : milestonesSection.getKeys(false)){
            ConfigurationSection milestoneSection = milestonesSection.getConfigurationSection(milestone);
            Map<Integer, Double> levels = new HashMap<>();
            levels.put(1, milestoneSection.getDouble("level_1", 0.0));
            levels.put(2, milestoneSection.getDouble("level_2", 0.0));
            levels.put(3, milestoneSection.getDouble("level_3", 0.0));
            milestoneLevels.put(milestone, levels);
        }
    }
    private void saveDefaultConfig(){
        plugin.saveResource("milestones.yml", false);
        milestoneConfig = YamlConfiguration.loadConfiguration(milestoneFile);
    }
    public Double getMilestoneValue(String milestone, int level){
        Map<Integer, Double> levels = milestoneLevels.get(milestone.toLowerCase());
        if(levels != null && levels.containsKey(level)){
            return  levels.get(level);
        }
        plugin.getLogger().warning("Milestone " + milestone+ " or level "+ level + " not found. Returning 0.0.");
        return  0.0;
    }
    public void reloadConfig() {
        milestoneLevels.clear();
        milestoneConfig = YamlConfiguration.loadConfiguration(milestoneFile);
        loadConfig();
    }
}
