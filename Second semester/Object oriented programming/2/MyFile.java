import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//3 etapas
//myfile isvestines klases .txt .raw .exec

class Txt extends MyFile{
    public void checkForWord(){
        
    }

    public void AppendFile(String word){
        super.AppendFile(word.getBytes());
    }
}

class jpeg extends MyFile{
    public void blur(){
        //imanoma
    }
}

class Exec extends MyFile{
    public void execute(){

    }
}

public class MyFile{
    
private File file;
final String permission; //default is const read-only for security purposes
static int myFileInstances = 0;

public MyFile(String fileName, String permission) {
    this.file=new File(fileName);
    this.permission=permission;
    myFileInstances++;
}

public MyFile(String fileName) {
    this.file=new File(fileName);
    this.permission="r";
    myFileInstances++;
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

public int AppendFile(byte b[]){
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

public static int myFileInstances(){
    return myFileInstances;
}

public void printLn(){
    System.out.println("File : " + file + " Permission: " + permission + " Instances: " + MyFile.myFileInstances);
}

}