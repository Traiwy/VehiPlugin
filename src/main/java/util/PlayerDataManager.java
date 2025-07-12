package util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class PlayerDataManager {
    private final JavaPlugin plugin;
    private final File playerDataFolder;

    public PlayerDataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerDataFolder = new File(plugin.getDataFolder(), "playerdata");
        if(!playerDataFolder.exists()){
            playerDataFolder.mkdirs();
        }
    }
    public void savePlayerMilestone(Player player, String customId, int level){
        File playerFile = new File(playerDataFolder, player.getUniqueId() +".yml");
        FileConfiguration cofig = YamlConfiguration.loadConfiguration(playerFile);

        cofig.set("milestone.customId", customId);
        cofig.set("milestone.level", level);
        try{
            cofig.save(playerFile);
            plugin.getLogger().info("Сохраненена веха " + player.getName() + ": " +customId + ", level: " +level );
        } catch (IOException e) {
            plugin.getLogger().severe("Ошибка сохранения файла " + player.getName()+ ": " + e.getMessage());
        }
    }
    public MilestoneData loadPlayerMilestone(Player player){
        File playerFile = new File(playerDataFolder, player.getUniqueId()+".yml");
        if(!playerFile.exists()){
            return null;
        }
        FileConfiguration cofig = YamlConfiguration.loadConfiguration(playerFile);
        String customId = cofig.getString("milestone.customId");
        int level = cofig.getInt("milestone.level");
        if(customId == null || level == 0){
            return null;
        }
         plugin.getLogger().info("Загрузка веха " + player.getName() + ": " +customId + ", level: " +level );
        return new MilestoneData(customId, level);
    }
    public void clearPlayerMilestone(Player player){
        File playerFile = new File(playerDataFolder, player.getUniqueId()+".yml");
        if(playerFile.exists()){
            playerFile.delete();
            plugin.getLogger().info("Конфиг с вехой был удален для: " + player.getName());
        }
    }
    public boolean hasPlayerMilestone(Player player) {
        File playerFile = new File(playerDataFolder, player.getUniqueId() + ".yml");
        if (!playerFile.exists()) return false;

        FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
        String customId = config.getString("milestone.customId");
        int level = config.getInt("milestone.level");

        return customId != null && level > 0;
    }
    public void setPlayerLevel(Player player, int level) {
    MilestoneData data = loadPlayerMilestone(player);
    String customId = (data != null) ? data.getCustomId() : "default_id";
    savePlayerMilestone(player, customId, level);
}
}
