package SocialAppGeneral;

import java.util.Objects;

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

    //done
    //add a partition to the line
    public void AddPartition(String Variable, String Value) {
        if (FormedLine.IsFormed(Value) && !Objects.equals(Value, "")) {
            this.line += (Variable + '=' + '"' + '~' + Value + '~' + '"' + '\t');
        } else {
            this.line += (Variable + '=' + '"' + Value + '"' + '\t');
        }
    }

    //done
    //read a partition from line
    public Partition ReadPartition() {
        Partition.Clean();
        if (Cursor < line.length()) {
            while (Cursor < line.length() && line.charAt(Cursor) != '=') {
                Partition.VariableName += line.charAt(Cursor);
                Cursor++;
            }
            if ((Cursor + 2) < line.length()) {
                if (line.charAt(Cursor + 1) == '"' && line.charAt(Cursor + 2) == '~') {
                    Cursor += 3;
                    while (Cursor < line.length() && line.charAt(Cursor) != '~') {
                        Partition.Value += line.charAt(Cursor);
                        Cursor++;
                    }
                    Cursor += 3;
                } else {
                    Cursor += 2;
                    while (Cursor < line.length() && line.charAt(Cursor) != '"') {
                        Partition.Value += line.charAt(Cursor);
                        Cursor++;
                    }
                    Cursor += 2;
                }
            }
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

    //done
    //see if the value is FormedLine or not
    public static Boolean IsFormed(String Text) {
        FormedLine formedLine = new FormedLine();
        FormedLine TempFormedLine = new FormedLine();
        Partition partition = new Partition();

        formedLine.setLine(Text);

        partition = formedLine.ReadPartition();
        while (!Objects.equals(partition.VariableName, "")) {
            TempFormedLine.AddPartition(partition.VariableName,partition.Value);
            partition = formedLine.ReadPartition();
        }

        return (formedLine.getLine().equals(TempFormedLine.getLine()));
    }
}
