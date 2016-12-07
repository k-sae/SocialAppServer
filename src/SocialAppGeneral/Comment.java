package SocialAppGeneral;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Comment extends PostAtachmment {
    private String commentcontent;
    private  long CommentId;
    private boolean show;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public long getCommentId() {
        return CommentId;
    }

    public void setCommentId(long commentId) {
        CommentId = commentId;
    }
}
