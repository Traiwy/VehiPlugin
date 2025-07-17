package util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class TaskConfigManager {
    private final JavaPlugin plugin;
    private File taskFile;
    private FileConfiguration taskConfig;

    public TaskConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.taskFile = new File(plugin.getDataFolder(), "tasks.yml");
        this.taskConfig = YamlConfiguration.loadConfiguration(taskFile);
        reloadConfig();
    }

    private void loadConfig(){
        if(!taskFile.exists()){
            saveDefaultConfig();
        }
    }
    private void saveDefaultConfig(){
        plugin.saveResource("tasks.yml", false);
        taskConfig = YamlConfiguration.loadConfiguration(taskFile);
    }
    public void reloadConfig(){
        taskConfig = YamlConfiguration.loadConfiguration(taskFile);
        loadConfig();
    }
    public String getIdTask(String mainSection, String section){
        ConfigurationSection taskSection = taskConfig.getConfigurationSection(mainSection);
        if(taskSection == null){
            plugin.getLogger().warning("Не найден 'taskSection'");
            return "Раздел задания не найден";
        }
        ConfigurationSection targetSection = taskSection.getConfigurationSection(section);
        if(targetSection == null){
            plugin.getLogger().warning("Не найдена секция " + section);
            return "Раздел " + section + " не найден";
        }
        String targetID = targetSection.getString("id");
        return targetID != null ? targetID : "ID задания не найдено";
    }
    public String getDescriptionTask(String mainSection, String section) {
        ConfigurationSection taskSection = taskConfig.getConfigurationSection(mainSection);
        if (taskSection == null) {
            plugin.getLogger().warning("Не найден 'taskSection'");
            return "Раздел задания не найден";
        }
        ConfigurationSection targetSection = taskSection.getConfigurationSection(section);
        if (targetSection == null) {
            plugin.getLogger().warning("Не найдена секция " + section);
            return "Раздел " + section + " не найден";
        }
        String targetDescription = targetSection.getString("description");
        return targetDescription != null ? targetDescription : "Описание заданяи не найдено";
    }
    public String getRarityTask(String mainSection, String section){
        String path = mainSection + "." + section;
        if(!taskConfig.contains(path+".rarity")){
            plugin.getLogger().warning("Не найден путь: " +  path+".rarity");
            return "Редскость не найдена";
        }
        return  taskConfig.getString(path+".rarity");
    }

}
