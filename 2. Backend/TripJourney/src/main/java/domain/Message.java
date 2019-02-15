package domain;

public class Message {

    private String messageId;
    private int userId;
    private String comment;

    public Message(String messageId, int userId, String comment) {
        this.messageId = messageId;
        this.userId = userId;
        this.comment = comment;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
