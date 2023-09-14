package scratch;

import java.util.Map;

public class Chat {
}

class User {
    int id;
    Map<Integer, Message> userMessage;

}

class Message {
    private int fromId;
    private int toId;
    private String message;

}
