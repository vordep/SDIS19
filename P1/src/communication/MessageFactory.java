package communication;

import communication.message.MessageSTORED;

public class MessageFactory {
    public static Message getMessage(MessageType type) {
        if (MessageType.STORED.equals(type)) {
            return new MessageSTORED();
        }
        return null;
    }
}
