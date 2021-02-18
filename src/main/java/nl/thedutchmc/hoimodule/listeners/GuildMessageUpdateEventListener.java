package nl.thedutchmc.hoimodule.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageUpdateEventListener extends ListenerAdapter {

	private long channelId;
	private EventCommon common;
	
	public GuildMessageUpdateEventListener(long channelId, EventCommon common) {
		this.channelId = channelId;
		this.common = common;
	}
	
	@Override
	public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
		if(event.getChannel().getIdLong() != this.channelId) {
			return;
		}
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		this.common.testHoi(event.getMessage(), event.getChannel(), event.getAuthor());
	}
}
