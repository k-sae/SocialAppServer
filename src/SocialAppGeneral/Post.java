package SocialAppGeneral;



import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Post implements Shareable,Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    private long id;
    private String content;
    private Date date;
    private long ownerId;
    private long PostPos;
    public static final String SAVE_POST_USER = "save_post_user";
    public static final String LOAD_POST_GROUPS = "load_post_groups";
    public static final String LOAD_POST_USERS = "load_post_user";
    public static final String EDITE_POST_USERS = "save_post_without_id";
    public static final String SAVE_POST_GROUP = "save_post_group";
    private  String imageID ;
    private ArrayList<Like> like;
    private  ArrayList<Comment> comments;
    public Post()
    {
        like = new ArrayList<>();
        comments = new ArrayList<>();
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    public void addcomment(Comment comment) {
        this.comments.add(comment);
    }
    public  void deletecomment(Comment comment){
        this.comments.remove(comment);
    }


    public void setLike(ArrayList<Like> like) {
        this.like = like;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public ArrayList<Like> getLike() {
        return like;
    }

    public void addlike(Like like) {
        this.like.add(like);
    }
    public  void deletelike(int index){
        this.like.remove(index);
    }

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
