import FileClasses.TextFile;
import FileClasses.ExecutableFile;
import FileClasses.Load;
import FileClasses.NonExistantFileException;
import FileClasses.Save;
//import com.mpatric.mp3agic.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String macOsTextFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/text.txt";
        String macOsExeFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exec.exe";
        String windowsTextFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\text.txt";
        String windowsExeFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\exec.exe";
        TextFile textFile = null;
        ExecutableFile exeFile = null;
        try{
            textFile = new TextFile(windowsTextFilePath);
            exeFile = new ExecutableFile(windowsExeFilePath); 
        } catch (NonExistantFileException e){
            System.out.println("No file found");
        }

        System.out.println("Before serialisation: ");
        System.out.println("Text file: ");
        System.out.println("Size: " + textFile.fileSize);
        System.out.println("Is file hidden: " + textFile.isHidden);
        System.out.println("File extension: " + textFile.fileExtension);
        System.out.println("Before serialisation: ");
        System.out.println("Exe file: ");
        System.out.println("Size: " + exeFile.fileSize);
        System.out.println("Is file hidden: " + exeFile.isHidden);
        System.out.println("File extension: " + exeFile.fileExtension);


        Save save = new Save(textFile, exeFile);
        Thread thread = new Thread(save);
        thread.start();

        
        thread.join();
        Load Load = new Load(textFile, exeFile);
        thread = new Thread(Load);
        thread.start();

        
        thread.join();
        System.out.println("");
            System.out.println("After serialisation: ");
            System.out.println("Text file: ");
            System.out.println("Size: " + textFile.fileSize);
            System.out.println("Is file hidden: " + textFile.isHidden);
            System.out.println("File extension: " + textFile.fileExtension);
            System.out.println("Before serialisation: ");
            System.out.println("Exe file: ");
            System.out.println("Size: " + exeFile.fileSize);
            System.out.println("Is file hidden: " + exeFile.isHidden);
            System.out.println("File extension: " + exeFile.fileExtension);
    }
}
