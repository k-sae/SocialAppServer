package SocialAppGeneral;



import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
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
    public static final String LOAD_POST = "load_post";
    public static final String Add_COMMENT = "add_comment";
    private  String imageID ;
    private ArrayList <Like> like;
    private  ArrayList<Comment> comments;

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
    public  void deletelike(Like like){
        this.like.remove(like);
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
