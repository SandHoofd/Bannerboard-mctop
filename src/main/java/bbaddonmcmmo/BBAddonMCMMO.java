package bbaddonmcmmo;

import org.bukkit.plugin.java.JavaPlugin;

import managers.ConfigManager;
import managers.DataBaseManager;
import me.bigteddy98.bannerboard.api.BannerBoardAPI;
import me.bigteddy98.bannerboard.api.BannerBoardManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BBAddonMCMMO extends JavaPlugin {
	
	private static BBAddonMCMMO instance;
	private BannerBoardAPI bbm;
	
	@Override
	public void onEnable() {
		instance = this;
		this.bbm = BannerBoardManager.getAPI();
		ConfigManager.getManager();
		DataBaseManager.getManager();
		System.out.println("BannerBoard-McMMO-Addon is enabled!");
	}

	public static BBAddonMCMMO getInstance() {
		return instance;
	}
	
	public BannerBoardAPI getBannerBoardAPI() {
		return bbm;
	}
}
