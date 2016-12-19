package SocialAppGeneral;


import SocialAppServer.GroupRelations;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/24/2016.
 */
public class Group implements Shareable,Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private ArrayList<Long> member;
    private ArrayList<Long> req;
    private long adminId;
    private String name;
    private long imageId;
    private long Id;
    public static final String CREATE_GROUP = "CreateGroup";
    public static final String GROUP_CANCEL_REQ = "CANCEL_REQUEST";
    public static final String FETCH_DATA="GROUP_DATA";
    public static final String Group_Leave = "LEAVE_GROUP";
    public static final String Group_relation = "Get_Relations";
    public static final String EDITE_GROUP = "EditGroup";
    public static final String Group_Accept = "Group_Accept";
    public static final String Group_ADD = "Group_ADD";
    public static final String DELETE_GROUP = "DeleteGroup";
    public static final String LOAD_GROUPS = "LoadGroups";
    public static final String LOAD_GROUP = "LoadGroup";
    private GroupRelations groupRelations;
    public Group(String name) {
        this.name = name;
        member = new ArrayList<>();
        req = new ArrayList<>();

    }
    public Group() {

        member = new ArrayList<>();
        req = new ArrayList<>();

    }
     public Group(Long id){
    groupRelations = new GroupRelations(Long.toString(id));
    }
    public GroupRelations getGroupRelation(){
        return groupRelations;}
    public ArrayList<Long> getMember() {
        return member;
    }

    public void setMember(ArrayList<Long> member) {
        this.member = member;
    }

    public ArrayList<Long> getReq() {
        return req;
    }

    public void setReq(ArrayList<Long> req) {
        this.req = req;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void deleteMember(Long id){
        this.member.remove(id);
    }
    public int checkMember(Long id){
        return (this.member.indexOf(id));
    }
    public void addMember(Long id){member.add(id);}
    public void addReq(Long id){req.add(id);}
    public void deleteReq(Long id){
        this.req.remove(id);
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

