//Konstantinas Arelis 3gr

import FileClasses.FileFunctionality;
import FileClasses.InvalidFileFormatException;
import FileClasses.NegativeSizeException;
import FileClasses.TxtFile;
import FileClasses.ExeFile;
import FileClasses.Load;

public class Main {
    public static void main(String[] args){

        String textFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/text.txt";
        String exeFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exec.exe";

        TxtFile textFile = new TxtFile(textFilePath);
        ExeFile exeFile = new ExeFile(exeFilePath);

        System.out.println("Encoding: " + textFile.encoding);
        System.out.println("Permissions: " + exeFile.permissions);
        
        Load myRunnable = new Load();
        Thread thread = new Thread(myRunnable);
        thread.start();
        

    }
}

// compile
// javac -d ClassFiles -cp . FileClasses/*.java Main.java

// run
// java -cp ClassFiles Main 