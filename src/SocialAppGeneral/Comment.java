package SocialAppGeneral;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Comment extends PostAtachmment {
    private String commentcontent;
    private  long CommentId;
    private int show;


    public int getShow() {
        return show;
    }

    public void setShow(int show) {
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
