package SocialAppGeneral;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by khaled hesham on 12/3/2016.
 */
public class ArraylistPost implements Shareable,Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private ArrayList<Post> posts=new ArrayList<>();
    private long OwnerPosts;
    private long numberpost;

    public long getNumberpost() {
        return numberpost;
    }

    public void setNumberpost(long numberpost) {
        this.numberpost = numberpost;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public long getOwnerPosts() {
        return OwnerPosts;
    }

    public void setOwnerPosts(long ownerPosts) {
        OwnerPosts = ownerPosts;
    }

    @Override

    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static ArraylistPost fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr,  ArraylistPost.class);
    }
}
