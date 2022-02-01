package org.jointheleague.modules;

import net.aksingh.owmjapis.api.APIException;
import org.javacord.api.event.message.MessageCreateEvent;


import java.util.Random;

public class NumberGuesser extends CustomMessageCreateListener {
    public NumberGuesser(String channelName) {
        super(channelName);
    }

    @Override
    public void handle(MessageCreateEvent event) throws APIException, InterruptedException {
        String zero = "0";
        String one = "1";
        String two = "2";
        String three = "3";
        Random r2 = new Random();
        int ran = r2.nextInt(3);

        String messageContent2 = event.getMessageContent();
        String messageContent3 = event.getMessageContent();
        if (messageContent2.startsWith(zero) || messageContent2.startsWith(one) || messageContent2.startsWith(two) || messageContent2.startsWith(three)) {
            event.getChannel().sendMessage("Answer: " + ran);

            if (messageContent3.startsWith("" + ran)){
                event.getChannel().sendMessage("Correct" + " Now resetting...");
            }

            else {
                event.getChannel().sendMessage("Nope!" + " Now resetting...");
            }

        }
    }
}
