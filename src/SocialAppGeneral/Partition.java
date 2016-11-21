package SocialAppGeneral;

/**
 * Created by begad on 10/30/2016.
 */
public class Partition {
    public String VariableName;
    public String Value;

    //constructor
    public Partition() {
        VariableName = "";
        Value = "";
    }

    //make the stings clear
    public void Clean() {
        VariableName = "";
        Value = "";
    }

    public void Print() {
        System.out.print(VariableName + "\t" + Value + "\n");
    }
}
