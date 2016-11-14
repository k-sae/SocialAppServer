package SocialAppGeneral;

/**
 * Created by begad on 10/30/2016.
 */
public class FormedLine {
    private String line;
    private int Cursor;
    private SocialAppGeneral.Partition Partition;

    //Constructor
    public FormedLine() {
        line = "";
        Cursor = 0;
        Partition = new Partition();
    }

    //set method for string line (don't use it unless the string you set is already formed)
    public void setLine(String line) {

        this.line = line;
    }

    //get method for string line
    public String getLine() {

        return line;
    }

    //add a partition to the line
    public void AddPartition(String Variable, String Value) {
        this.line += (Variable + '=' + '"' + Value + '"' + '\t');
    }

    //read a partition from line
    public Partition ReadPartition() {
        Partition.Clean();
        if (Cursor < line.length()) {
            while (Cursor < line.length() && line.charAt(Cursor) != '=') {
                Partition.VariableName += line.charAt(Cursor);
                Cursor++;
            }
            Cursor += 2;
            while (Cursor < line.length() && line.charAt(Cursor) != '"') {
                Partition.Value += line.charAt(Cursor);
                Cursor++;
            }
            Cursor += 2;
        }
        return Partition;
    }

    //Read a spicific Partition by its Variable Name
    public Partition ReadPartition(String VariableName) {
        Partition.Clean();
        Partition.VariableName = VariableName;
        int Counter = line.indexOf(VariableName) + VariableName.length() + 2;
        while (line.charAt(Counter) != '"') {
            Partition.Value += line.charAt(Counter);
            Counter++;
        }
        return Partition;
    }

    //set the cursor on the beginning of the partition which take its number
    public void SetCursorAt(int PartitionNumber) {
        int count = 1;
        int literNumber = 0;
        while (count < PartitionNumber) {
            if (line.charAt(literNumber) == '\t') {
                count++;
                literNumber++;
            } else {
                literNumber++;
            }
        }
        Cursor = literNumber;
    }
}
