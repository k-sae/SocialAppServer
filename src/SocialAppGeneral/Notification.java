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
    private  String  idSender;//id whose  send noftication
    private  long id;

    public Notification() {
        this.post=new Post();
    }

    public String getIdSender() {
        return idSender;
    }

    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getId() {
        return id;
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
