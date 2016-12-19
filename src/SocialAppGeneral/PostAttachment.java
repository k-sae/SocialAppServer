package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class PostAttachment implements Serializable {
    private long ownerID;
    private static final long serialVersionUID = 6529685098267757690L;
    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }
}
