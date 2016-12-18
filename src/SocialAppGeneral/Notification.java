package SocialAppGeneral;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by khaled hesham on 12/17/2016.
 */
public class Notification implements Shareable,Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private  Post post;
    private  Relations keyword;
    private  String  idSender;//id whose  send noftication
    public static final String LOAD_NOTI = "loadNoti";
    public  static final String NEW_NOTIFICATION = "new_notification";
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

    public Relations getKeyword() {
        return keyword;
    }

    public void setKeyword(Relations keyword) {
        this.keyword = keyword;
    }


    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static Notification fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, Notification.class);
    }
}
