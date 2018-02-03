package managers;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import bbaddonmcmmo.BBAddonMCMMO;

public class ConfigManager {

	private static ConfigManager instance;
	private File configFile;
	private File playerDataFolder;
	
	public static ConfigManager getManager() {
		if (instance == null) {
			instance = new ConfigManager();
		}
		return instance;
	}
	
	private ConfigManager() {
		BBAddonMCMMO mainInstance = BBAddonMCMMO.getInstance();
		
		if (!mainInstance.getDataFolder().exists()) {
			mainInstance.getDataFolder().mkdirs();
		}
		
		configFile = new File(mainInstance.getDataFolder(), "Configuration.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setupConfigDefaults();
		}
		
		playerDataFolder = new File(mainInstance.getDataFolder(), "playerDatas");
		
		if (!playerDataFolder.exists()) {
			playerDataFolder.mkdirs();
		}
		
	}

	private void setupConfigDefaults() {
		FileConfiguration config = getConfig();
		//Begin creating config defaults
		
		ConfigurationSection Settings = config.createSection("Settings");
		
		ConfigurationSection DataBase = Settings.createSection("DataBase");
		
		DataBase.set("Name", "Host");
		
		//End creating config defaults
		saveConfig(config);
	}
	
	private void setupPlayerDataDefaults(Player p) {
		FileConfiguration playerData = getPlayerData(p);
		//Begin creating playerdata defaults
		
		ConfigurationSection Details = playerData.createSection("Details");
		
		Details.set("Name", p.getName());
		Details.set("UUID", p.getUniqueId());
		
		//End creating playerdata defaults
		savePlayerData(p, playerData);
	}
	
	public FileConfiguration getPlayerData(Player p) {
		File playerFile = getPlayerDataFile(p);
		return YamlConfiguration.loadConfiguration(playerFile);
	}
	
	public void savePlayerData(Player p, FileConfiguration playerData) {
		File playerFile = getPlayerDataFile(p);
		try {
			playerData.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private File getPlayerDataFile(Player p) {
		UUID uuid = p.getUniqueId();
		File playerFile = new File(playerDataFolder, uuid+"yml");
		if (!playerFile.exists()) {
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setupPlayerDataDefaults(p);
		}
		return playerFile;
	}

	public void saveConfig(FileConfiguration configuration) {
		try {
			configuration.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FileConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(configFile);
	}
}
