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
    public static final String SUMPUP = "sumup";
    public static final String SUMPDOWN = "sumdown";
    public static final String COMMENT = "comment";
    public static final String LOAD_NOTI = "loadNoti";
    public Notification() {
        this.post=new Post();
        this.idSender="";
    }

    public String getIdSender() {
        return idSender;
    }

    public void setIdSender(String idSender) {
        this.idSender = idSender;
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
