package xin.rainyn.consumefly.util;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtil{
	private static ConfigUtil config = null;
	
	private FileConfiguration fileConfiguration = null;
	 
	private boolean debug;
	private boolean playerJoinMessageEnable;
	private List<String> playerJoinMessage;
	private boolean levelEnable;
	private String levelMessage;
	private Long levelTime;
	private boolean vaultEnable;
	private String vaultMessage;
	private Long vaultTime;
	//配置的key
	private String playerJoinMessageEnableKey = "setting.playerJoinMessageEnable";
	private String debugKey = "setting.debug";
	private String playerJoinMessageKey = "setting.playerJoinMessage";
	private String levelEnableKey = "setting.level.enable";
	private String levelMessageKey = "setting.level.message";
	private String levelTimeKey = "setting.level.time";
	private String vaultEnableKey = "setting.vault.enable";
	private String vaultMessageKey = "setting.vault.message";
	private String vaultTimeKey = "setting.vault.time";
	
	private void initProperties() {
		debug = fileConfiguration.getBoolean(debugKey);
		playerJoinMessageEnable = fileConfiguration.getBoolean(playerJoinMessageEnableKey);
		playerJoinMessage = fileConfiguration.getStringList(playerJoinMessageKey);
		levelEnable = fileConfiguration.getBoolean(levelEnableKey);
		levelMessage = fileConfiguration.getString(levelMessageKey);
		levelTime = fileConfiguration.getLong(levelTimeKey);
		vaultEnable = fileConfiguration.getBoolean(vaultEnableKey);
		vaultMessage = fileConfiguration.getString(vaultMessageKey);
		vaultTime = fileConfiguration.getLong(vaultTimeKey);
	}
	
	
	public boolean isDebug() {
		return debug;
	}


	public boolean isPlayerJoinMessageEnable() {
		return playerJoinMessageEnable;
	}


	public void setPlayerJoinMessageEnable(Boolean playerJoinMessageEnable) {
		this.playerJoinMessageEnable = playerJoinMessageEnable;
		fileConfiguration.set(playerJoinMessageEnableKey, playerJoinMessageEnable);
	}


	public List<String> getPlayerJoinMessage() {
		return playerJoinMessage;
	}


	public void setPlayerJoinMessage(List<String> playerJoinMessage) {
		this.playerJoinMessage = playerJoinMessage;
		fileConfiguration.set(playerJoinMessageKey, playerJoinMessage);
	}


	public boolean isLevelEnable() {
		return levelEnable;
	}


	public void setLevelEnable(Boolean levelEnable) {
		this.levelEnable = levelEnable;
		fileConfiguration.set(levelEnableKey, levelEnable);
	}


	public String getLevelMessage() {
		return levelMessage;
	}


	public void setLevelMessage(String levelMessage) {
		this.levelMessage = levelMessage;
		fileConfiguration.set(levelMessageKey, levelMessage);
	}


	public Long getLevelTime() {
		return levelTime;
	}


	public void setLevelTime(Long levelTime) {
		this.levelTime = levelTime;
		fileConfiguration.set(levelTimeKey, levelTime);
	}


	public boolean isVaultEnable() {
		return vaultEnable;
	}


	public void setVaultEnable(Boolean vaultEnable) {
		this.vaultEnable = vaultEnable;
		fileConfiguration.set(vaultEnableKey, vaultEnable);
	}


	public String getVaultMessage() {
		return vaultMessage;
	}


	public void setVaultMessage(String vaultMessage) {
		this.vaultMessage = vaultMessage;
		fileConfiguration.set(vaultMessageKey, vaultMessage);
	}


	public Long getVaultTime() {
		return vaultTime;
	}


	public void setVaultTime(Long vaultTime) {
		this.vaultTime = vaultTime;
		fileConfiguration.set(vaultTimeKey, vaultTime);
	}


	public static ConfigUtil getConfig() {
		if(config == null) {
			config = new ConfigUtil();
		}
		return config;
	}


	public FileConfiguration getFileConfiguration() {
		return fileConfiguration;
	}


	public void setFileConfiguration(FileConfiguration fileConfiguration) {
		this.fileConfiguration = fileConfiguration;
		initProperties();
	}
	
	
}