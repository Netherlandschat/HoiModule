package nl.thedutchmc.hoimodule;

import java.util.List;

import net.dv8tion.jda.api.requests.GatewayIntent;
import nl.thedutchmc.hoimodule.listeners.GuildMessageReceivedEventListener;
import nl.thedutchmc.hoimodule.listeners.GuildMessageUpdateEventListener;
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
		
		Object channelIdObj = config.get("channelId");
		long channelId = 0L;
		if(channelIdObj instanceof Long) {
			channelId = (long)  channelIdObj;
		} else if(channelIdObj instanceof Integer) {
			channelId = ((Integer) channelIdObj).longValue();
		} else {
			throw new RuntimeException("Invalid datatype for config option 'channelId'. Should be Long.");
		}
		
		@SuppressWarnings("unchecked")
		List<String> notHoiMessages = (List<String>) config.get("notHoiMessages");
		
		super.registerEventListener(new GuildMessageReceivedEventListener(channelId, notHoiMessages));
		super.registerEventListener(new GuildMessageUpdateEventListener(channelId, notHoiMessages));
	}
}
