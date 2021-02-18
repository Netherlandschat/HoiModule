package nl.thedutchmc.hoimodule.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReceivedEventListener extends ListenerAdapter {

	private long channelId;
	private EventCommon common;
	
	public GuildMessageReceivedEventListener(long channelId, EventCommon common) {
		this.channelId = channelId;
		this.common = common;
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if(event.getChannel().getIdLong() != this.channelId) {
			return;
		}
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		this.common.testHoi(event.getMessage(), event.getChannel(), event.getAuthor());
	}
}
