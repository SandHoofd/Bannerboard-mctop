package bbaddonmcmmo;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.nossr50.mcMMO;

import managers.ConfigManager;
import managers.DataBaseManager;
import me.bigteddy98.bannerboard.api.BannerBoardAPI;
import me.bigteddy98.bannerboard.api.BannerBoardManager;

public class BBAddonMCMMO extends JavaPlugin {
	
	private static BBAddonMCMMO instance;
	private mcMMO mcmmo;
	@Override
	public void onEnable() {
		instance = this;
		
		mcmmo = (mcMMO) getServer().getPluginManager().getPlugin("mcMMO");
		if (mcmmo == null || !mcmmo.isEnabled()) {
			System.out.println("mcMMO is not included or is not enabled!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		ConfigManager.getManager();
		DataBaseManager.getManager();
		System.out.println("BannerBoard-McMMO-Addon is enabled!");
	}

	public static BBAddonMCMMO getInstance() {
		return instance;
	}
	
	public BannerBoardAPI getBannerBoardAPI() {
		return BannerBoardManager.getAPI();
	}
	
	public mcMMO getmcMMO() {
		return this.mcmmo;
	}
}
