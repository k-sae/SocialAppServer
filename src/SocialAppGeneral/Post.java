package SocialAppGeneral;



import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Post implements Shareable,Serializable{

    private long id;
    private String content;
    private Date date;
    private long ownerId;
    private long PostPos;
    public static final String SAVE_POST = "save_post";
    public String getContent() {
        return content;
    }

    public long getPostPos() {
        return PostPos;
    }

    public void setPostPos(long postPos) {
        PostPos = postPos;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static Post fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, Post.class);
    }
}
