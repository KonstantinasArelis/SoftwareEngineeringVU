import FileClasses.TextFile;
import FileClasses.ExecutableFile;
import FileClasses.Load;
import FileClasses.MusicFile;
import FileClasses.NonExistantFileException;
import FileClasses.Save;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String macOsTextFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/text.txt";
        String macOsExeFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exec.exe";
        String macOsMusicFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exampleMusicFile.mp3";

        String windowsTextFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\text.txt";
        String windowsExeFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\exec.exe";
        String windowsMusicFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\exampleMusicFile.mp3";

        TextFile textFile = null;
        ExecutableFile exeFile = null;
        MusicFile musicFile = null;
        try{
            textFile = new TextFile(windowsTextFilePath);
            exeFile = new ExecutableFile(windowsExeFilePath); 
            musicFile = new MusicFile(windowsMusicFilePath); 
        } catch (NonExistantFileException e){
            System.out.println("No file found");
        }


        //printing values to be saved
        System.out.println("Before serialisation: ");
        System.out.println("Text file: ");
        System.out.println("\tSize: " + textFile.fileSize);
        System.out.println("\tIs file hidden: " + textFile.isHidden);
        System.out.println("\tFile extension: " + textFile.fileExtension);
        System.out.println("\tFile encoding: " + textFile.encoding);
        System.out.println("Exe file: ");
        System.out.println("\tSize: " + exeFile.fileSize);
        System.out.println("\tIs file hidden: " + exeFile.isHidden);
        System.out.println("\tFile extension: " + exeFile.fileExtension);
        System.out.println("Music file: ");
        System.out.println("\tSize: " + musicFile.fileSize);
        System.out.println("\tIs file hidden: " + musicFile.isHidden);
        System.out.println("\tFile extension: " + musicFile.fileExtension);
        System.out.println("\tArtist: " + musicFile.artist);
        System.out.println("\tTitle: " + musicFile.title);
        System.out.println("\talbum: " + musicFile.album);
        
        // saving values
        Save save = new Save(textFile, exeFile, musicFile);
        Thread thread = new Thread(save);
        thread.start();
        thread.join();

        // changing values
        musicFile.artist = "new artist";
        musicFile.album = "new album";
        musicFile.title = "new title";
        musicFile.fileSize = 100;

        // printing changed values
        System.out.println("Intermediary: ");
        System.out.println("\tArtist: " + musicFile.artist);
        System.out.println("\tTitle: " + musicFile.title);
        System.out.println("\talbum: " + musicFile.album);
        System.out.println("\tsize: " + musicFile.fileSize);

        // loading old values
        Load Load = new Load(textFile, exeFile, musicFile);
        thread = new Thread(Load);
        thread.start();
        thread.join();

        // printing the loaded old values
        System.out.println("");
            System.out.println("After serialisation: ");
            System.out.println("Text file: ");
            System.out.println("\tSize: " + textFile.fileSize);
            System.out.println("\tIs file hidden: " + textFile.isHidden);
            System.out.println("\tFile extension: " + textFile.fileExtension);
            System.out.println("\tFile encoding: " + textFile.encoding);
            System.out.println("Exe file: ");
            System.out.println("\tSize: " + exeFile.fileSize);
            System.out.println("\tIs file hidden: " + exeFile.isHidden);
            System.out.println("\tFile extension: " + exeFile.fileExtension);
            System.out.println("Music file: ");
            System.out.println("\tSize: " + musicFile.fileSize);
            System.out.println("\tIs file hidden: " + musicFile.isHidden);
            System.out.println("\tFile extension: " + musicFile.fileExtension);
            System.out.println("\tArtist: " + musicFile.artist);
            System.out.println("\tTitle: " + musicFile.title);
            System.out.println("\talbum: " + musicFile.album);
    }
}
