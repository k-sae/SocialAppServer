package SocialAppGeneral;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kemo on 04/12/2016.
 */
public class SocialArrayList implements Shareable,Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String extra;
    private String target;
    private List<String> items;
    public SocialArrayList(List<String> items)
    {
        this.items = items;
    }
    public SocialArrayList()
    {
        items = new ArrayList<>();
    }

    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static SocialArrayList convertFromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, SocialArrayList.class);
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
