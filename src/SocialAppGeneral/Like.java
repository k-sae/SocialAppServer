package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Like extends PostAtachmment implements Serializable {
    private int like;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {//put 1 if put like put -1 if put dislike
        this.like = like;
    }
}
