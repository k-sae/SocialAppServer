package SocialAppGeneral;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by khaled hesham on 12/17/2016.
 */
public class Notification implements Shareable,Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private  Post post;
    private  String keyword;
    private  String  id;//id whose  send noftication

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
