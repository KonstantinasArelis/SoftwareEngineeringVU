import FileClasses.TextFile;
import FileClasses.ExecutableFile;
import FileClasses.Load;
import FileClasses.MusicFile;
import FileClasses.NonExistantFileException;
import FileClasses.Save;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // meniu punktas pakeisti txt faila
        TextFile textFile = null;
        ExecutableFile exeFile = null;
        MusicFile musicFile = null;
        
        String macOsTextFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/text.txt";
        String macOsExeFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exec.exe";
        String macOsMusicFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exampleMusicFile.mp3";
        
        String windowsTextFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\text.txt";
        String windowsExeFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\exec.exe";
        String windowsMusicFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\exampleMusicFile.mp3";

        String inputPath;
        String comment;
        

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Thread thread;
        Load Load;
        Save save;
        while(true){
            System.out.println("Meniu: ");
            System.out.println("\t1. open file ");
            System.out.println("\t2. exit ");
            System.out.println("\t3. load ");
            System.out.println("\t4. save ");
            System.out.println("\t5. edit file ");
            String option = myObj.nextLine();  // Read user input

            if(option.equals("5")){
                if(textFile!= null && textFile.filePath != null){
                    System.out.println("\t 1." + textFile.filePath.toString());
                }
                if(exeFile!= null && exeFile.filePath != null){
                    System.out.println("\t 2." + exeFile.filePath.toString());
                }
                if(musicFile!= null && musicFile.filePath != null){
                    System.out.println("\t 3." + musicFile.filePath.toString());
                }
                option = myObj.nextLine();  // Read user input
                if(option.equals("1")){
                    System.out.println("enter text to be appended to file");
                    comment = myObj.nextLine(); 
                    textFile.appendToTextContents(comment); // important line
                }
            }
            else if(option.equals("3")){

                textFile = new TextFile();
                exeFile = new ExecutableFile();
                musicFile = new MusicFile();

                Load = new Load(textFile, exeFile, musicFile);
                thread = new Thread(Load);
                thread.start();
                thread.join();
            }
            else if(option.equals("4")){
                save = new Save(textFile, exeFile, musicFile);
                thread = new Thread(save);
                thread.start();
            }
            else if(option.equals("2")){
                System.exit(0);
            }
            else if(option.equals("1")){
                System.out.println("Submeniu: ");
                System.out.println("\t1. open text file ");
                System.out.println("\t2. open executable file ");
                System.out.println("\t3. open music file ");
                option = myObj.nextLine();
                System.out.println("enter file path: ");
                inputPath = myObj.nextLine();  // Read user input
                if(option.equals("1")){
                    try {
                        textFile = new TextFile(inputPath);
                    } catch (NonExistantFileException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if(option.equals("2")){
                    
                    try {
                        exeFile = new ExecutableFile(inputPath); 
                    } catch (NonExistantFileException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if(option.equals("3")){
                    
                    try {
                        musicFile = new MusicFile(inputPath); 
                    } catch (NonExistantFileException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }


 
        /* 
        try{
            textFile = new TextFile(macOsTextFilePath);
            exeFile = new ExecutableFile(macOsExeFilePath); 
            musicFile = new MusicFile(macOsMusicFilePath); 
        } catch (NonExistantFileException e){
            System.out.println("No file found");
        }
        */

        /* 
        // printing values to be saved
        System.out.println("Before serialisation: ");
        if(textFile != null){
            System.out.println("Text file: ");
            System.out.println("\tSize: " + textFile.fileSize);
            System.out.println("\tIs file hidden: " + textFile.isHidden);
            System.out.println("\tFile extension: " + textFile.fileExtension);
            System.out.println("\tFile encoding: " + textFile.encoding);
        }
        if(exeFile != null){
            System.out.println("Exe file: ");
            System.out.println("\tSize: " + exeFile.fileSize);
            System.out.println("\tIs file hidden: " + exeFile.isHidden);
            System.out.println("\tFile extension: " + exeFile.fileExtension);
        }
        if(musicFile != null){
            System.out.println("Music file: ");
            System.out.println("\tSize: " + musicFile.fileSize);
            System.out.println("\tIs file hidden: " + musicFile.isHidden);
            System.out.println("\tFile extension: " + musicFile.fileExtension);
            System.out.println("\tArtist: " + musicFile.artist);
            System.out.println("\tTitle: " + musicFile.title);
            System.out.println("\talbum: " + musicFile.album);
        }
        
        
        // saving values
        

       

        // loading old values
        

        // printing the loaded old values
        System.out.println("");
        System.out.println("After serialisation: ");
        if(textFile != null){
            System.out.println("Text file: ");
            System.out.println("\tSize: " + textFile.fileSize);
            System.out.println("\tIs file hidden: " + textFile.isHidden);
            System.out.println("\tFile extension: " + textFile.fileExtension);
            System.out.println("\tFile encoding: " + textFile.encoding);
        }
        if(exeFile != null){
            System.out.println("Exe file: ");
            System.out.println("\tSize: " + exeFile.fileSize);
            System.out.println("\tIs file hidden: " + exeFile.isHidden);
            System.out.println("\tFile extension: " + exeFile.fileExtension);
        }
        if(musicFile != null){
            System.out.println("Music file: ");
            System.out.println("\tSize: " + musicFile.fileSize);
            System.out.println("\tIs file hidden: " + musicFile.isHidden);
            System.out.println("\tFile extension: " + musicFile.fileExtension);
            System.out.println("\tArtist: " + musicFile.artist);
            System.out.println("\tTitle: " + musicFile.title);
            System.out.println("\talbum: " + musicFile.album);
        }
        */
    }
}

//javac -d ClassFiles -cp ".;com" FileClasses/*.java Main.java com/mpatric/mp3agic/*.java

//java -cp ClassFiles Main 