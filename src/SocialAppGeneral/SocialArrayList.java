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
    private Object extra;
    private Object target;
    private List<Object> items;
    public SocialArrayList(List<Object> items)
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

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
