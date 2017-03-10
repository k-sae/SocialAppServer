
package FileManagment;







import java.io.*;
import java.util.ArrayList;

/**
 * Created by begad on 10/30/2016.
 */
public class FilesManager {

    public static void CreateFolder(String FolderName) {
        String[] nestedFolders = FolderName.split("\\\\");
        String nextFolderLocation = "";
        for (int i = 0; i < nestedFolders.length; i++) {
            File Folder = new File(nextFolderLocation + nestedFolders[i]);
            //noinspection ResultOfMethodCallIgnored
            Folder.mkdir();
            nextFolderLocation += nestedFolders[i] + "/";
        }
    }

    public static File CreateFolder(String ExistedFolderName, String NewFolderName) {
        File Folder = new File(ExistedFolderName, NewFolderName);
        return Folder.mkdir() ? Folder : null;
    }

    public static File CreateFile(String FileName) {
        FileName = toLinuxPath(FileName);
        File file = new File(FileName);
        return file;
    }

    public static File CreateFile(String FolderName, String FileName) {
        FileName = toLinuxPath(FileName);
        FolderName = toLinuxPath(FolderName);
        File file = new File(FolderName, FileName);
        return file;
    }

    public static BufferedWriter OpenToWrite(String FileName) {
        FileName = toLinuxPath(FileName);
        try {
            FileWriter fileWriter = new FileWriter(FileName);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            fileWriter.close();
            return bufferWriter;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BufferedWriter OpenToWrite(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            fileWriter.close();
            return bufferWriter;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BufferedWriter OpenToWrite(String FolderName, String FileName) {
        FileName = toLinuxPath(FileName);
        FolderName = toLinuxPath(FolderName);
        try {
            File file = new File(FolderName, FileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            fileWriter.close();
            return bufferWriter;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BufferedReader OpenToReade(String FileName) {
        try {
            FileReader fileReader = new FileReader(FileName);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            fileReader.close();
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
        FileName = toLinuxPath(FileName);
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
        FileName = toLinuxPath(FileName);
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
    public static Boolean AddLineWithoutAppend(ArrayList<String> a, String FileName) {
        FileName = toLinuxPath(FileName);
        try {
            BufferedWriter WT = new BufferedWriter(new FileWriter(FileName));
            for (String S:a){
                WT.write(String.valueOf(S));
                WT.newLine();
                WT.flush();
            }
            WT.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    public static boolean StringFinder(String FileName, String token) {
        FileName = toLinuxPath(FileName);
        try {
            BufferedReader RL = new BufferedReader(new FileReader(FileName));
            String line;
            while ((line = RL.readLine()) != null) {
                if (line.equals(token)) {
                    RL.close();
                    return true;
                }
            }
            RL.close();
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
    public static String ReadLine(String FileName, int lineNum) {
        FileName = toLinuxPath(FileName);
        try {
            BufferedReader RL = new BufferedReader(new FileReader(FileName));
            String line;
            int LineCounter = 1;
            while ((line = RL.readLine()) != null) {
                if (LineCounter == lineNum) {
                    RL.close();
                    break;

                }
                LineCounter++;
            }
            RL.close();
            return line;
        } catch (IOException ex) {
            return null;
        }
    }
    public static Boolean searcher(String FileName,String id) {
        FileName = toLinuxPath(FileName);
        try {
            BufferedReader RL = new BufferedReader(new FileReader(FileName));
            String line;
            while ((line = RL.readLine()) != null) {
                if (line.equals(id)) {
                    RL.close();
                    return true;

                }
            }
            RL.close();
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
    //Change name later
    public static boolean ReadBanned(String FileName, String token) {
        FileName = toLinuxPath(FileName);
        try {
            BufferedReader RL = new BufferedReader(new FileReader(FileName));
            String line ;
            while ((line = RL.readLine()) != null) {
                if(!line.isEmpty()) {
                    if (line.equals(token)) {
                        RL.close();
                        return true;
                    }
                }
            }
            RL.close();
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
    public static void DeleteFile(File file) {
        final boolean Temp = file.delete();
    }
    public static void DeleteFile(String FileName) {
        File file = new File(FileName);
        final boolean Temp = file.delete();
    }


    public static synchronized void RemoveLine(String FilePath,String token){
        BufferedReader RL = null;
        FilePath = toLinuxPath(FilePath);
      try {
       RL=new BufferedReader(new FileReader(FilePath));
      String Line;
          ArrayList <String> a =new ArrayList<String>();
          while((Line= RL.readLine())!=null) {
              if (!Line.contains(token)) {
                  a.add(Line);
              }
          }
          AddLineWithoutAppend(a,FilePath);

      }catch (IOException ignored){

      }
      if (RL != null) try {
          RL.close();
      } catch (IOException ignored) {
      }
    }
    public static ArrayList<String> ReadIntoArrayList(String FilePath){
        FilePath = toLinuxPath(FilePath);
        ArrayList <String> a =new ArrayList<String>();
        try {
            BufferedReader RL=new BufferedReader(new FileReader(FilePath));
            String Line;
            while((Line= RL.readLine())!=null) {
                Line=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
                a.add(Line);
            }
            return a;
        }catch (IOException ex){
        }
        return a;
    }
    public static ArrayList<String> ReadArrayList(String FilePath){
        FilePath = toLinuxPath(FilePath);
        ArrayList <String> a =new ArrayList<String>();
        try {
            BufferedReader RL=new BufferedReader(new FileReader(FilePath));
            String Line;
            while((Line= RL.readLine())!=null) {
                a.add(Line);
            }
            return a;
        }catch (IOException ex){
        }
        return a;
    }
    public static ArrayList<String > FoldderSearcher(String FilePath){
        FilePath = toLinuxPath(FilePath);
        ArrayList<String> a=new ArrayList<>();
        File folder = new File(FilePath);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
          a.add(file.getName());
        }
        return a;
    }
    public static synchronized void Removefile(String FilePath,String info){
        FilePath = toLinuxPath(FilePath);
        try {

            File inputFile = new File(FilePath);
            DeleteFile(inputFile);
            File tempFile = new File(FilePath);
            BufferedWriter WT = new BufferedWriter(new FileWriter(tempFile));
            WT.write(info);
            WT.newLine();
            WT.close();
        }catch (IOException ignored){
        }

          //File inputFile = new File(FilePath);
          //File tempFile = new File(FilePath+"2");
          //BufferedReader RL = new BufferedReader(new FileReader(FilePath));
          //String line;
          //BufferedWriter WT = new BufferedWriter(new FileWriter(FilePath+"2"));
          //while ((line = RL.readLine()) != null) {
           //   if (line.contains(token)) {
             // }else{
               //   WT.write(line);
                 // WT.newLine();
              //}
         // }
          //WT.close();
          //RL.close();
          //DeleteFile(inputFile);
          //tempFile.renameTo(inputFile);

        //}catch (IOException ex){

      //}
      }

    public static void DeleteFile(String FolderName, String FileName) {
        File file = new File(FolderName, FileName);
        final boolean Temp = file.delete();
    }

    public static boolean FileIsExist(String FolderName, String FileName) {
        FolderName = toLinuxPath(FolderName);
        FileName = toLinuxPath(FileName);
        File file = new File(FolderName, FileName);
        return file.exists();
    }

    public static boolean WriteOnTop(String FileName, String Line) {
        FileName = toLinuxPath(FileName);
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
        FileName = toLinuxPath(FileName);
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
        FileName = toLinuxPath(FileName);
        File file = new File(FileName);
        return file.exists();
    }
    public static void CreateFileBinary(Object object,String path){
        CreateFolder(path.substring(0,path.lastIndexOf("\\")));
        path = toLinuxPath(path);
        try{

            FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(object);

                oos.close();


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  static  Object ReadFromBinaryFile(String path){
        Object o = null;
        path = toLinuxPath(path);
        try{
            FileInputStream foss=new FileInputStream(path);
            ObjectInputStream ooss=new ObjectInputStream(foss);
            o = ooss.readObject();
            ooss.close();

        }
      catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;

    }
    public static ArrayList<String> readAllLines(String file) {
        ArrayList<String> strings = new ArrayList<>();
        file = toLinuxPath(file);
        BufferedReader bufferedReader = null;
        try {

             bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }

        } catch (IOException ignored) {
        }
        if (bufferedReader != null) try {
            bufferedReader.close();
        } catch (IOException e) {
        }
        return strings;
    }
    private   static  void deleteFolder(File file){
        if(file.isDirectory()){
            //directory is empty, then delete it
            if(file.list().length==0){
                file.delete();

            }else{
                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    temp = toLinuxPath(temp);
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    deleteFolder(fileDelete);
                }
                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();

                }
            }

        }else{
            //if file, then delete it
            file.delete();

        }
    }
    public static void delete(String path){
        path = toLinuxPath(path);
        File file =new File (path);
        deleteFolder(file);
    }
    public static String toLinuxPath(String path)
    {
       return path.replace("\\","/" );
    }
}

