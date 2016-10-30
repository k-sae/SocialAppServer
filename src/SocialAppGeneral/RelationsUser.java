package SocialAppGeneral;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kemo on 30/10/2016.
 */
public class RelationsUser extends AppUser implements Serializable {
    //try to send arrayList as Serializable
    protected ArrayList<AppUser> members;
    protected ArrayList<AppUser> Requests;
}
