package nl.thedutchmc.hoimodule;

import net.dv8tion.jda.api.requests.GatewayIntent;
import nl.thedutchmc.hoimodule.listeners.MessageReceivedEventListener;
import nl.thedutchmc.netherlandsbot.annotations.RegisterBotModule;
import nl.thedutchmc.netherlandsbot.modules.BotModule;
import nl.thedutchmc.netherlandsbot.modules.io.FileType;
import nl.thedutchmc.netherlandsbot.modules.io.ModuleConfig;
import nl.thedutchmc.netherlandsbot.modules.io.ModuleFileHandler;

@RegisterBotModule(name = "HoiModule", version="0.0.1", intents = { GatewayIntent.GUILD_MESSAGES })
public class HoiModule extends BotModule {

	@Override
	public void onLoad() {
		
		super.logInfo("Initializing...");
		
		//Config file
		ModuleFileHandler fileHandler = super.getModuleFileHandler();
		
		if(!fileHandler.getConfigFile().exists()) {
			System.out.println(fileHandler.getConfigFile().getAbsolutePath());
			fileHandler.saveDefault(FileType.CONFIG, "hoimodule-config.yml");
		}
		
		ModuleConfig config = fileHandler.getModuleConfig();
		
		super.registerEventListener(new MessageReceivedEventListener(
				(Long) config.get("channelId"),
				(String) config.get("notHoiMessage")));
	}
}
