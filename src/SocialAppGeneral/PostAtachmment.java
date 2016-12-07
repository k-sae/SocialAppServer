package SocialAppGeneral;

import com.google.gson.Gson;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class PostAtachmment implements Shareable  {
    private long ownerID;
    private long postId;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }
    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static PostAtachmment fromJsonString (String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, PostAtachmment.class);
    }
}
