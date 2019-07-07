package org.jointheleague.modules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.javacord.api.event.message.MessageCreateEvent;

import com.google.gson.*;

public class FlagMessageListener extends CustomMessageCreateListener {
	
	EmojiArray data;
	
	public FlagMessageListener(String channelName) {
		super(channelName);
		data = parse();
	}

	@Override
	public void handle(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith("!flag")) {
			String country = event.getMessageContent().substring(5);
			Emoji em = null;
			
			for (Emoji e : data.array) {
				if (e.getCategory().equals("Flags")) {
					if (e.getAliases().contains(country.toLowerCase())) {
						em = e;
						break;
					}
				}
			}
			
			event.getChannel().sendMessage(""+em.getEmoji());
		}
	}
	
	public EmojiArray parse() {
		String jsonline = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("emoji.json")));
			while (br.ready()) {
				jsonline += br.readLine() + "\n";
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Gson gson = new GsonBuilder().create();
        EmojiArray data = gson.fromJson(jsonline, EmojiArray.class);
	    
	    return data;
	}

}
