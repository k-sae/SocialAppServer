package SocialAppGeneral;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Comment extends PostAttachment {
    private static final long serialVersionUID = 6529685098267757690L;
    private String commentContent;
    private  long CommentId;
    private Relations show;


    public Relations getShow() {
        return show;
    }

    public void setShow(Relations show) {
        this.show = show;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public long getCommentId() {
        return CommentId;
    }

    public void setCommentId(long commentId) {
        CommentId = commentId;
    }
}
