package nl.thedutchmc.hoimodule.listeners;

import java.util.List;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import nl.thedutchmc.hoimodule.Util;

public class EventCommon {

	List<String> notHoiMessages;
	
	public EventCommon(List<String> notHoiMessages) {
		this.notHoiMessages = notHoiMessages;
	}
	
	public void testHoi(Message m, TextChannel c, User u) {
		if(m.getAttachments().size() > 0) {
			sendMessageAndDelete(c, new MessageBuilder(String.format("Zeg %s, hoe vaak moet ik het nog zeggen. Alleen 'Hoi' hier! (en dus niet attachments godverdomme!)", u.getAsMention())).build());
			m.delete().queue();
			
			return;
		}
		
		String message = m.getContentDisplay()
				.replace(" ", "")
				.replace("\u200b", "")
				.strip();
		
		if(!message.equalsIgnoreCase("hoi")) {
			sendMessageAndDelete(c, new MessageBuilder(u.getAsMention() + " " + this.notHoiMessages.get(Util.getRandomInt(0, this.notHoiMessages.size() -1))).build());
			m.delete().queue();
		}
	}
	
	private void sendMessageAndDelete(TextChannel c, Message m) {
		c.sendMessage(m).queue(callbackMsg -> {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			callbackMsg.delete().queue();
		});
	}
}
