package nl.thedutchmc.hoimodule.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedEventListener extends ListenerAdapter {

	private long channelId;
	private String notHoiMessage;
	
	public MessageReceivedEventListener(long channelId, String notHoiMessage) {
		this.channelId = channelId;
		this.notHoiMessage = notHoiMessage;
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if(event.getChannel().getIdLong() != this.channelId) {
			return;
		}
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		String message = event.getMessage().getContentDisplay().toLowerCase();
		if(!message.equalsIgnoreCase("hoi")) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention() + " " + notHoiMessage).queue(callbackMsg -> {
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
