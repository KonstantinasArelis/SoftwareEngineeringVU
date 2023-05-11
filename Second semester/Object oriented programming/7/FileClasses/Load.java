package FileClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Load implements Serializable, Runnable{

    TextFile txtFile;
    ExecutableFile exeFile;

    public Load(TextFile txtFile, ExecutableFile exeFile) {
        this.txtFile = txtFile;
        this.exeFile = exeFile;
    }

    @Override
    public void run() {

        try {
            FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            TextFile textFile2 = (TextFile) objectInputStream.readObject();
            ExecutableFile exeFile2 = (ExecutableFile) objectInputStream.readObject();

            objectInputStream.close(); 

            
        } catch (FileNotFoundException e) {
            System.out.println("error: File not found exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("error: IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("error: ClassNotFoundException: " + e.getMessage());
        }
    }
}
