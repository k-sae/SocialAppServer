package SocialAppGeneral;

import com.google.gson.Gson;

/**
 * Created by khaled hesham on 12/28/2016.
 */
public class PostSender implements Shareable {
    long numberPost;
    long id ;

    public PostSender(long numberPost, long id) {
        this.numberPost = numberPost;
        this.id = id;
    }

    public PostSender(long numberPost) {
        this.numberPost = numberPost;
    }

    public long getNumberPost() {
        return numberPost;
    }

    public void setNumberPost(long numberPost) {
        this.numberPost = numberPost;
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
    public static PostSender fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, PostSender.class);
    }

}
