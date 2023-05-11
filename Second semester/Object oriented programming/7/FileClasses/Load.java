package FileClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Load implements Serializable, Runnable{

    TxtFile txtFile;
    ExeFile exeFile;

    public Load(TxtFile txtFile, ExeFile exeFile) {
        this.txtFile = txtFile;
        this.exeFile = exeFile;
    }

    @Override
    public void run() {

        try {
            FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            TxtFile textFile2 = (TxtFile) objectInputStream.readObject();
            ExeFile exeFile2 = (ExeFile) objectInputStream.readObject();

            objectInputStream.close(); 

            System.out.println("");
            System.out.println("After serialisation: ");
            System.out.println("Text file encoding: " + textFile2.encoding);
            System.out.println("Exe file permissions: " + exeFile2.permissions);
            
        } catch (FileNotFoundException e) {
            System.out.println("error: File not found exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("error: IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("error: ClassNotFoundException: " + e.getMessage());
        }
    }
}
