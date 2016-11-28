
package FileManagment;

import javax.sound.sampled.Line;
import java.io.*;

/**
 * Created by begad on 10/30/2016.
 */
public class FilesManager {

    public static File CreateFolder(String FolderName) {
        File Folder = new File(FolderName);
        return Folder.mkdir() ? Folder : null;
    }

    public static File CreateFolder(String ExistedFolderName, String NewFolderName) {
        File Folder = new File(ExistedFolderName,NewFolderName);
        return Folder.mkdir() ? Folder : null;
    }

    public static File CreateFile(String FileName) {
        File file = new File(FileName);
        return file;
    }

    public static File CreateFile(String FolderName, String FileName) {
        File file = new File(FolderName, FileName);
        return file;
    }

    public static BufferedWriter OpenToWrite(String FileName) {
        try {
            FileWriter fileWriter = new FileWriter(FileName);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            return bufferWriter;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BufferedWriter OpenToWrite(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            return bufferWriter;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BufferedWriter OpenToWrite(String FolderName, String FileName) {
        try {
            File file = new File(FolderName,FileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            return bufferWriter;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BufferedReader OpenToReade(String FileName) {
        try {
            FileReader fileReader = new FileReader(FileName);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            return bufferReader;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BufferedReader OpenToReade(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            return bufferReader;
        } catch (IOException ex) {
            return null;
        }
    }

    //belal
    public static Boolean AddLine(String FileName, String text) {
        try {
            BufferedWriter WT = new BufferedWriter(new FileWriter(FileName,true));
            WT.write(text);
           WT.newLine();
            WT.flush();
            WT.close();
          return true;
       } catch (IOException ex) {
           return false;
       }
    }
    public static Boolean AddLineWithoutAppend(String FileName, String text) {
        try {
            BufferedWriter WT = new BufferedWriter(new FileWriter(FileName));
            WT.write(text);
            WT.newLine();
            WT.flush();
            WT.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }


    public static String ReadLine(String FileName, int lineNum) {
        try {
            BufferedReader RL = new BufferedReader(new FileReader(FileName));
            String line;
            int LineCounter = 1;
            while ((line = RL.readLine()) != null) {
                if (LineCounter == lineNum) {
                    break;
                }
                LineCounter++;
            }
            return line;
        } catch (IOException ex) {
            return null;
        }
    }
    public static boolean ReadLine(String FileName, String token) {
        try {
            BufferedReader RL = new BufferedReader(new FileReader(FileName));
            String line;
            while ((line = RL.readLine()) != null) {
                if (line.contains(token)) {
                   return true;
                }
            }
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
    public static String FileSearcher(String FileName, String token) {
        try {
            BufferedReader RL = new BufferedReader(new FileReader(FileName));
            String line;
            while ((line = RL.readLine()) != null) {
                if (line.contains(token)) {
                    return line;
                }
            }
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    public static void DeleteFile(File file) {
        final boolean Temp = file.delete();
    }

    public static void DeleteFile(String FileName) {
        File file = new File(FileName);
        final boolean Temp = file.delete();
    }

    public static void DeleteFile(String FolderName, String FileName) {
        File file = new File(FolderName, FileName);
        final boolean Temp = file.delete();
    }

    public static boolean FileIsExist(String FolderName, String FileName) {
        File file = new File(FolderName,FileName);
        return file.exists();
    }

    public static boolean WriteOnTop(String FileName, String Line) {
        try {
            // el function el gahza bel list

            /*
            Path path = Paths.get(FileName);
            List<String> lines = java.nio.file.Files.readAllLines(path);

            lines.add(0, Line);
            java.nio.file.Files.write(path,lines);
            */

            BufferedReader BR = new BufferedReader(new FileReader(FileName));

            String line;
            String result = "";
            while ((line = BR.readLine()) != null) {
                result += line + "\r\n";
            }
            BufferedWriter TextTop = new BufferedWriter(new FileWriter(FileName));
            result = Line + "\r\n" + result;
            TextTop.write(result);

            BR.close();
            TextTop.close();

            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public static boolean WriteOnTop(String FileName, String Line, int lineNum) {
        try {
            BufferedReader BR = new BufferedReader(new FileReader(FileName));

            String line;
            String UpperLines = "";
            String result = "";
            int LineCounter = 1;

            while ((line = BR.readLine()) != null) {
                if (LineCounter >= lineNum) {
                    result += line + "\r\n";
                } else {
                    UpperLines += line + "\r\n";
                }
                LineCounter++;
            }
            BufferedWriter TextTop = new BufferedWriter(new FileWriter(FileName));
            result = UpperLines + Line + "\r\n" + result;
            TextTop.write(result);

            BR.close();
            TextTop.close();

            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public static boolean FileIsExist(String FileName) {
        File file = new File(FileName);
        return file.exists();
    }
}
