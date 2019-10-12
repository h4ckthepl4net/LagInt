package main.body.footer;

class footer {

    public enum MessageType {
        ERROR,
        WARNING,
        TEXT
    }

    String lastMessage = null;
    MessageType lastMessageType = null;
}
