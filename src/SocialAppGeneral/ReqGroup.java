package SocialAppGeneral;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by khaled hesham on 12/14/2016.
 */
public class ReqGroup   implements Shareable,Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private long owner;
    private long groupId;
    private long postion;

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getPostion() {
        return postion;
    }

    public void setPostion(long postion) {
        this.postion = postion;
    }

    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static ReqGroup fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, ReqGroup.class);
    }
}
