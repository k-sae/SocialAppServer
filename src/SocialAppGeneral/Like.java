package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Like extends PostAttachment implements Serializable {
    private Relations like;
    private static final long serialVersionUID = 6529685098267757690L;
    public Relations getLike() {
        return like;
    }
    public void setLike(Relations like) {//put 1 if put like put -1 if put dislike
        this.like = like;
    }
}
