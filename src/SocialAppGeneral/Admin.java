package SocialAppGeneral;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppServer.Generator;
import SocialAppServer.ServerLoggedUser;

/**
 * Created by mosta on 27-Nov-16.
 */
public abstract interface Admin {
    public static  final String KEYWORD="ADMIN_CHECK";
    public static  final String RetrieveData="RETRIEVE_DATA";
    public static final  String APPROVE="APPROVE";
    public static final  String APPROVEASADMIN="APPROVE_AS_ADMIN";
    public static final  String REJECT="REJECT";
    public abstract void approve(String Email);
    public abstract void reject(String Email);
    public abstract void approveAsAdmin(String Email);
}
