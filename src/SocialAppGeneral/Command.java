package SocialAppGeneral;

/**
 * Created by kemo on 25/10/2016.
 */
public class Command  {
    private String keyWord;
    final static String separator = "%&&&%";
    private String sharableObject;
    public String getObjectStr() {
        return sharableObject;
    }

    public void setSharableObject(Shareable objectstr) {

        this.sharableObject = objectstr.convertToJsonString();

    }
    public void setSharableObject(String  objectstr) {
        this.sharableObject = objectstr;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    @Override
    public String toString() {
        return keyWord + separator + sharableObject;
    }
    public static Command fromString(String s)
    {
        String[] commandStr = s.split(separator);
        if(commandStr.length != 2)
        {
            return  null;
        }
        Command newCommand = new Command();
        newCommand.setKeyWord(commandStr[0]);
        newCommand.setSharableObject(commandStr[1]);
        return newCommand;
    }
}
