import FileClasses.TxtFile;
import FileClasses.ExeFile;
import FileClasses.Load;
import FileClasses.Save;

import java.io.*;

//import FileClasses.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String textFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/text.txt";
        String exeFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exec.exe";

        TxtFile textFile = new TxtFile(textFilePath);
        ExeFile exeFile = new ExeFile(exeFilePath);

        System.out.println("Encoding: " + textFile.encoding);
        System.out.println("Permissions: " + exeFile.permissions);
        
        // kitoje klaseje
        Save save = new Save(textFile, exeFile);
        Thread thread = new Thread(save);
        thread.start();
        
        /* 
        while(thread.isAlive()){
            // wait for saving to complete
        }
        */
        
        thread.join();
        Load Load = new Load(textFile, exeFile);
        thread = new Thread(Load);
        thread.start();
    }
}
