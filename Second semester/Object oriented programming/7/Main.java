import FileClasses.TxtFile;
import FileClasses.ExeFile;
import java.io.*;

//import FileClasses.*;

public class Main implements Runnable {
    
    TxtFile txtFile;
    ExeFile exeFile;

    public Main(TxtFile txtFile, ExeFile exeFile) {
        this.txtFile = txtFile;
        this.exeFile = exeFile;
    }

    public static void main(String[] args) {

        String textFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/text.txt";
        String exeFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exec.exe";

        TxtFile textFile = new TxtFile(textFilePath);
        ExeFile exeFile = new ExeFile(exeFilePath);

        System.out.println("Encoding: " + textFile.encoding);
        System.out.println("Permissions: " + exeFile.permissions);
        
        Main main = new Main(textFile, exeFile);
        Thread thread = new Thread(main);
        thread.start();
    }

    @Override
    public void run() {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(txtFile);
            objectOutputStream.writeObject(exeFile);
            objectOutputStream.flush();
            objectOutputStream.close();
            
            FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            TxtFile textFile2 = (TxtFile) objectInputStream.readObject();
            ExeFile exeFile2 = (ExeFile) objectInputStream.readObject();

            objectInputStream.close(); 

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
