package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Entity
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
    private Date postDate;

    public Comment() {
    }

    public Comment(int stepId, int userId, String comment) {
        this.stepId = stepId;
        this.userId = userId;
        this.comment = comment;
        this.postDate = new Date();
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
