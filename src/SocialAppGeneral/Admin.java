package SocialAppGeneral;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppServer.Generator;

/**
 * Created by mosta on 27-Nov-16.
 */
public class Admin extends LoggedUser implements FilesPath{
    public void convertIntoPermnantUser(String Email){
        FilesManager.OpenToReade(UNREIGESTERDUSERS+ Generator.GenerateID(Email));
String line=FilesManager.FileSearcher(UNREIGESTERDUSERS+Generator.GenerateID(Email),Email);
        RegisterInfo re=new RegisterInfo();
        re.fromJsonString(line);

    }
}
