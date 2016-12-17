package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.*;

import java.util.ArrayList;

/**
 * Created by mosta on 30-Nov-16.
 */
class UserFinder implements FilesPath {
    //TODO
public static String  validate(String email, String pass){
    Command command = new Command();
    command.setKeyWord(RegisterInfo.KEYWORD);
    String id="-1";
    if(FilesManager.LoginValidatior(USERS+EMAILS+ Generator.GenerateID(email)+".txt",email,pass)){
        String Line=FilesManager.FileSearcherForID(USERS+EMAILS+ Generator.GenerateID(email)+".txt",email);
        if (Line != null) {
            id=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        }

    }
    return id;
}
//takes email or name
public ArrayList<String> Search(String email){

    ArrayList<String> Emails=FilesManager.FoldderSearcher(USERS+EMAILS);
    ArrayList<String> Names=FilesManager.FoldderSearcher(USERS+NAMES);
    ArrayList<String> finalsearch=new ArrayList();
    //ArrayList<String> EmailSearch=new ArrayList<>();
    ArrayList<String> IDs=new ArrayList<>();
    String ID=Generator.GenerateID(email);
    ID = (ID.length() > 3 )?  ID.substring(0, ID.length()-3) : ID;
    for(int i=Names.size()-1;i>=0;i--){
          if(!Names.get(i).startsWith(ID)){
              Names.remove(Names.get(i));
          }
         }
    for(int i=Emails.size()-1;i>=0;i--){
        if(!Emails.get(i).startsWith(ID)){
            Emails.remove(Emails.get(i));
        }
    }
    for(int i=Names.size()-1;i>=0;i--){
        IDs=FilesManager.ReadIntoArrayList(USERS+NAMES+Names.get(i));
        for(int j=IDs.size()-1;j>=0;j--){
            finalsearch.add(IDs.get(j));
        }
    }
    for(int i=Emails.size()-1;i>=0;i--){
        IDs=FilesManager.ReadIntoArrayList(USERS+EMAILS+Emails.get(i));
        for(int j=IDs.size()-1;j>=0;j--){
            finalsearch.add(IDs.get(j));
        }
    }
    //finalsearch.addAll(EmailSearch);
    //finalsearch.addAll(NamesSearch);
   // FilesManager.readAllLines()
return finalsearch;
}
    public ArrayList<String> SearchInFreinds(String email,String Id){
        ArrayList<String> Names=FilesManager.FoldderSearcher(USERS+NAMES);
        ArrayList<String> IDs=new ArrayList<>();
        ArrayList<String> Friend=new ArrayList<>();
        ArrayList<String> Search=new ArrayList<>();
        String ID=Generator.GenerateID(email);
        ID = (ID.length() > 3 )?  ID.substring(0, ID.length()-3) : ID;
        for(int i=Names.size()-1;i>=0;i--){
            if(!Names.get(i).startsWith(ID)){
                Names.remove(Names.get(i));
            }
        }
        for(int i=Names.size()-1;i>=0;i--){
            IDs=FilesManager.ReadIntoArrayList(USERS+NAMES+Names.get(i));
            for(int j=IDs.size()-1;j>=0;j--){
                Search.add(IDs.get(j));
            }
        }
        Friend=FilesManager.ReadIntoArrayList(USERS+Id+FRIENDS);
        for(int i=Search.size()-1;i>=0;i--){
            for(int j =Friend.size()-1;j>=0;j--){
            if(!Search.get(i).equals(Friend.get(j))){
            Search.remove(Search.get(i));
            }
            }
        }
        return Search;
    }
    public ArrayList<String> SearchInGroups(String email){
        ArrayList<String> Names=FilesManager.FoldderSearcher(USERS+NAMES);
        ArrayList<String> finalsearch=new ArrayList();
        ArrayList<String> IDs=new ArrayList<>();
        String ID=Generator.GenerateID(email);
        ID = (ID.length() > 3 )?  ID.substring(0, ID.length()-3) : ID;
        for(int i=Names.size()-1;i>=0;i--){
            if(Names.get(i).endsWith(".txt")){
                Names.remove(Names.get(i));
            }
        }
        for(int i=Names.size()-1;i>=0;i--){
            if(!Names.get(i).startsWith(ID)){
                Names.remove(Names.get(i));
            }
        }

        for(int i=Names.size()-1;i>=0;i--){
            IDs=FilesManager.ReadIntoArrayList(USERS+NAMES+Names.get(i));
            for(int j=IDs.size()-1;j>=0;j--){
                finalsearch.add(IDs.get(j));
            }
        }
    return finalsearch;
    }
}
