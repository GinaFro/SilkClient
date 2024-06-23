package silkclient.events.impl;

import silkclient.events.EventCancellable;

public class ClientChatEvent extends EventCancellable {

    private String text;

    public ClientChatEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
