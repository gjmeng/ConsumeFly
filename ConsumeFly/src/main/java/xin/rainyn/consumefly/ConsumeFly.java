package xin.rainyn.consumefly;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import xin.rainyn.consumefly.command.ConsumflyCommandExecutor;
import xin.rainyn.consumefly.listener.PlayerListener;
import xin.rainyn.consumefly.logger.Logger;
import xin.rainyn.consumefly.util.ColorUtil;
import xin.rainyn.consumefly.util.ConfigUtil;
import xin.rainyn.consumefly.util.EconomyUtil;

/**
 * 消费飞行
 * @author Rainyner
 *
 */
public class ConsumeFly extends JavaPlugin{
	
	@Override
	public void onDisable() {
		super.onDisable();
		Logger.debug("消费飞行插件已卸载！！！  作者：Rainyner");
	}

	@Override
	public void onEnable() {
		super.onEnable();
		//保存默认配置文件，并获取到配置文件
		saveDefaultConfig();
		ConfigUtil.getConfig().setFileConfiguration(getConfig());
		//设置命令对象
		getCommand("consumefly").setExecutor(new ConsumflyCommandExecutor());
		//加载vault插件
		if(ConfigUtil.getConfig().isVaultEnable() && !loadVault()) {
			Logger.consoleError("初始化Vault支持失败,请检测是否已经安装Vault插件，已禁用消费飞行插件！！！");
		}
		//注册玩家的事件监听
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		//保存当前主类
		Logger.CONSUMEFLY = this;
		Logger.info("消费飞行插件已启用！！！  作者：Rainyner","调试输出："+(ConfigUtil.getConfig().isDebug()?"是":"否"));
		
		//配置文件调试输出
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "玩家加入显示提示信息："+(ConfigUtil.getConfig().isPlayerJoinMessageEnable()?"是":"否"));
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "玩家加入提示消息：");
		for(String str : ConfigUtil.getConfig().getPlayerJoinMessage()) {
			Logger.consoleDebug(ColorUtil.GREEN_DAN_A + str);
		}
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "消费经验飞行："+(ConfigUtil.getConfig().isLevelEnable()?"是":"否"));
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "未启用消费经验飞行的提示语："+(ConfigUtil.getConfig().getLevelMessage()));
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "1级经验可飞行时间(秒)："+(ConfigUtil.getConfig().getLevelTime()));
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "消费金币飞行："+(ConfigUtil.getConfig().isVaultEnable()?"是":"否"));
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "未启用消费金币飞行的提示语："+(ConfigUtil.getConfig().getVaultMessage()));
		Logger.consoleDebug(ColorUtil.GREEN_DAN_A + "1金币可飞行时间(秒)："+(ConfigUtil.getConfig().getVaultTime()));
		
	}
	
	private boolean loadVault() {
		boolean hasNull = false;
		//初始化经济系统实例
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            if ((EconomyUtil.economy = economyProvider.getProvider()) == null) hasNull = true;
        }
        return !hasNull;
	}

	@Override
	public void onLoad() {
		super.onLoad();
		Logger.setLogger(getLogger());
		Logger.info("消费飞行插件已加载！！！  作者：Rainyner");
	}
	
}
