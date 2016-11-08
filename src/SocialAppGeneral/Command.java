package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by kemo on 25/10/2016.
 */
public class Command implements Serializable {
    private String keyWord;
    private Serializable serializable;
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Serializable getSerializable() {
        return serializable;
    }

    public void setSerializable(Serializable serializable) {
        this.serializable = serializable;
    }



}
