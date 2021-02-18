package nl.thedutchmc.hoimodule.listeners;

import java.util.List;

import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.thedutchmc.hoimodule.Util;

public class GuildMessageUpdateEventListener extends ListenerAdapter {

	private long channelId;
	private List<String> notHoiMessages;
	
	public GuildMessageUpdateEventListener(long channelId, List<String> notHoiMessages) {
		this.channelId = channelId;
		this.notHoiMessages = notHoiMessages;
	}
	
	@Override
	public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
		if(event.getChannel().getIdLong() != this.channelId) {
			return;
		}
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		String message = event.getMessage().getContentDisplay().replace(" ", "").strip();
		if(!message.equalsIgnoreCase("hoi")) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention() + " " + this.notHoiMessages.get(Util.getRandomInt(0, this.notHoiMessages.size() -1))).queue(callbackMsg -> {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				callbackMsg.delete().queue();
			});
			event.getMessage().delete().queue();
		}
	}
}
