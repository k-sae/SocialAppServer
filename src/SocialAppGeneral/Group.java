package SocialAppGeneral;

import FileManagment.FilesPath;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/24/2016.
 */
public class Group implements Shareable,Serializable, FilesPath{
    private ArrayList<Integer> member;
    private ArrayList<Integer> req;
    private ArrayList<Integer> post;
    private int adminId;
    private String name;
    private int imageId;
    private int Id;
    public static final String CREATE_GROUP = "Create_group";
    public Group(String name) {
        this.name = name;
        member = new ArrayList<>();
        req = new ArrayList<>();
        adminId = 0;
        imageId = 0;
        Id = 0;
        post=new ArrayList<>();
    }

    public Group(int id) {
        Id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public Boolean checkAdmin(int id){return (this.adminId==id);}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ArrayList<Integer> getMember() {
        return member;
    }
    public void setMember(ArrayList<Integer>  member) {
        this.member =member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getReq() {
        return req;
    }

    public void setReq(ArrayList<Integer> req) {
        this.req = req;
    }

    public ArrayList<Integer> getPost() {
        return post;
    }

    public void setPost(ArrayList<Integer> post) {
        this.post = post;
    }
    public void deleteMember(Integer id){
        this.member.remove(id);
    }
    public int checkMember(Integer id){
        return (this.member.indexOf(id));
    }
    public void addMember(Integer id){member.add(id);}
    public void addReq(Integer id){req.add(id);}
    public void addPost(Integer id){post.add(id);}
    public void deleteReq(Integer id){
        this.req.remove(id);
    }
    public void deletePost(Integer id){
        this.post.remove(id);
    }
    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static Group fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, Group.class);
    }
}