package SocialAppGeneral;

import com.google.gson.Gson;

/**
 * Created by khaled hesham on 12/27/2016.
 */
public class AttachmentSender implements Shareable{
    Like like ;
    Comment comment;
    long ownerID;
    long postId;
    public static final String ATTACHMENT_USER = "atachment_user";
    public static final String ATTACHMENT_GROUP = "atachment_group";
    public AttachmentSender(Like like, long ownerID, long postId) {
        this.like = like;
        this.ownerID = ownerID;
        this.postId = postId;
    }

    public AttachmentSender(Comment comment, long ownerID, long postId) {
        this.comment = comment;
        this.ownerID = ownerID;
        this.postId = postId;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static AttachmentSender fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, AttachmentSender.class);
    }
}
