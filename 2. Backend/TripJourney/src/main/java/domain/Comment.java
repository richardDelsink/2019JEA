package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@Table(name = "Comment")
@NamedQueries({
        @NamedQuery(name = "comment.getCommentsByStep", query = "SELECT c FROM Comment c WHERE c.stepId = :stepId"),
        @NamedQuery(name = "comment.getCommentsByUser", query = "SELECT c FROM Comment c WHERE c.userId = :userId"),
        @NamedQuery(name = "comment.findByName", query = "SELECT c FROM Comment c WHERE c.comment = :name")})

@XmlRootElement
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commentId;
    private int stepId;
    private int userId;
    private String comment;

    public Comment() {
    }

    public Comment(String messageId, int stepId, int userId, String comment) {
        this.commentId = messageId;
        this.stepId = stepId;
        this.userId = userId;
        this.comment = comment;
    }


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
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
