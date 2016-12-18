package SocialAppGeneral;

import com.google.gson.Gson;

/**
 * Created by billy on 2016-12-18.
 */
public class Log implements Shareable{
    private Relations keyword;
    private  String senderId;//id whose  send noftication
    private String ownerId;
    public static final String LOAD_LOG = "loadLog";
    public Log() {
        this.senderId ="";
        this.ownerId = "";
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Relations getKeyword() {
        return keyword;
    }

    public void setKeyword(Relations keyword) {
        this.keyword = keyword;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static Log fromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, Log.class);
    }
}
