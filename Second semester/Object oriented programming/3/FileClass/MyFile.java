package FileClass;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//3 etapas

public class MyFile{
        
    private File file;
    final String permission; //default is const read-only for security purposes
    static int fileInstances = 0;

    public MyFile(String fileName, String permission) {
        this.file=new File(fileName);
        this.permission=permission;
        fileInstances++;
    }

    public MyFile(String fileName) {
        this.file=new File(fileName);
        this.permission="r";
        fileInstances++;
    }

    public MyFile() {
        this(null);
    }

    //getter
    public File getFile() {
        return file;
    }

    //Setter
    public void setFile(String newPath) {
        this.file = new File(newPath);
    }

    public int AppendFile(int b){
        RandomAccessFile stdOut = null;
        try {
            stdOut = new RandomAccessFile(file, permission);
            if(permission == "r"){
                return -1;
            }
                try {
                    stdOut.seek(stdOut.length());
                    stdOut.write(b);
                } catch (IOException e) {
                    System.out.println("IOException");
                    return -1;
                }
            System.out.println("");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            return -1;
        } finally {
            if (stdOut != null) {
                try {
                    stdOut.close();
                    return 0;
                } catch (IOException e) {
                    System.out.println("IOException");
                    return -1;
                }
            }
        }
        return -1;
    }

    public int appendFile(byte b[]){
        RandomAccessFile stdOut = null;
        try {
            stdOut = new RandomAccessFile(file, permission);
            if(permission == "r"){
                return -1;
            }
                try {
                    stdOut.seek(stdOut.length());
                    for(int i=0;i<b.length;i++){
                        stdOut.write(b[i]);
                    }
                } catch (IOException e) {
                    System.out.println("IOException");
                    return -1;
                }
            System.out.println("");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            return -1;
        } finally {
            if (stdOut != null) {
                try {
                    stdOut.close();
                    return 0;
                } catch (IOException e) {
                    System.out.println("IOException");
                    return -1;
                }
            }
        }
        return -1;
    }
// paketai
    public static int fileInstances(){
        return fileInstances;
    }

    final public void printLn(){
        System.out.println("File : " + file + " Permission: " + permission + " Instances: " + MyFile.fileInstances);
    }

    void Print()
    {
        // Print statement
        System.out.println("MyFile path: " + file.toString());
    }
}