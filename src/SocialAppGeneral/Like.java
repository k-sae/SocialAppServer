package SocialAppGeneral;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Like extends PostAtachmment {
    private short like;

    public short getLike() {
        return like;
    }

    public void setLike(short like) {//put 1 if put like put -1 if put dislike
        this.like = like;
    }
}
