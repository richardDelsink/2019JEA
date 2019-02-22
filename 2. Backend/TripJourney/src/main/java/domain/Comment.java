package domain;

import javax.persistence.*;


//@Entity
//@Table(name = "Comment")
//@NamedQueries({@NamedQuery(name = "",query = "")})
public class Comment {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commentId;
    private int journeyId;
    private int userId;
    private String comment;

    public Comment(String messageId, int userId, String comment) {
        this.commentId = messageId;
        this.userId = userId;
        this.comment = comment;
    }

    public String getMessageId() {
        return commentId;
    }

    public void setMessageId(String messageId) {
        this.commentId = messageId;
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
