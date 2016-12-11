package SocialAppGeneral;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by khaled hesham on 12/11/2016.
 */
public class ArraylistGroup implements Shareable,Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private ArrayList<Long> groups=new ArrayList<>();
    private long OwnerGroups;

    public ArrayList<Long> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Long> groups) {
        this.groups = groups;
    }

    public long getOwnerGroups() {
        return OwnerGroups;
    }

    public void setOwnerGroups(long ownerGroups) {
        OwnerGroups = ownerGroups;
    }

    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static ArraylistGroup fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr,  ArraylistGroup.class);
    }
}
