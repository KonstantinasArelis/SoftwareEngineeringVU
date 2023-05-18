import FileClasses.TextFile;
import FileClasses.ExecutableFile;
import FileClasses.MusicFile;
import FileClasses.NonExistantFileException;
import FileClasses.MyFile;
//import FileClasses.Save;
//import FileClasses.Load;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // meniu punktas pakeisti txt faila
        //TextFile textFile = null;
        //ExecutableFile exeFile = null;
        //MusicFile musicFile = null;
        
        String macOsTextFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/text.txt";
        String macOsExeFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exec.exe";
        String macOsMusicFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/7/exampleMusicFile.mp3";
        
        String windowsTextFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\text.txt";
        String windowsExeFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\exec.exe";
        String windowsMusicFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\7\\exampleMusicFile.mp3";

        String inputPath;
        String comment;
        String newTagName;

        Scanner myObj = new Scanner(System.in);
        //Thread thread;
        //Load Load;
        //Save save;
        while(true){
            System.out.println("Meniu: ");
            System.out.println("\t1. printf general directory tree");
            System.out.println("\t2. printf directory tree of suffix");
            System.out.println("\t3. copy file ");
            System.out.println("\t4. append text file");
            System.out.println("\t5. display text file ");
            System.out.println("\t6. edit mp3 file tags");
            System.out.println("\t7. display mp3 tags ");
            System.out.println("\t8. run executable file ");
            System.out.println("\t9. exit ");
            //System.out.println("\t3. load ");
            //System.out.println("\t4. save ");
            String option = myObj.nextLine();
            if(option.equals("1")){
                System.out.println("Enter directory name: ");
                inputPath = myObj.nextLine();
                MyFile.printDirectorytree(inputPath);
            }
            else if(option.equals("2")){
                System.out.println("Enter directory name: ");
                inputPath = myObj.nextLine();
                System.out.println("Enter suffix: ");
                option = myObj.nextLine();
                MyFile.printDirectorytree(inputPath,option);
            }
            else if(option.equals("3")){
                System.out.println("enter  file path");
                inputPath = myObj.nextLine();
                try {
                    TextFile txtfile = new TextFile(inputPath);
                    try {
                        txtfile.copyFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (NonExistantFileException e) {
                    System.out.println("file cannot be opened");
                }
            }
            else if(option.equals("4")){
                System.out.println("enter text file path");
                inputPath = myObj.nextLine();
                try {
                    TextFile txtfile = new TextFile(inputPath);
                    System.out.println("enter text to be appended to file");
                    comment = myObj.nextLine();
                    txtfile.appendToTextContents(comment);
                } catch (NonExistantFileException e) {
                    System.out.println("text file cannot be opened");
                }
            }
            else if(option.equals("5")){
                System.out.println("Enter directory name: ");
                inputPath = myObj.nextLine();
                try {
                    TextFile txtfile = new TextFile(inputPath);
                    System.out.println(txtfile.textContents);
                } catch (NonExistantFileException e) {
                    System.out.println("file cannot be opened");
                }
            }
            else if(option.equals("6")){
                System.out.println("Enter mp3 file name: ");
                inputPath = myObj.nextLine();
                try {
                    MusicFile mp3Test = new MusicFile(inputPath);
                    System.out.println("enter tag number you would like to change: ");
                    System.out.println("1. track ");
                    System.out.println("2. artist ");
                    System.out.println("3. title ");
                    System.out.println("4. album ");
                    System.out.println("5. year ");
                    System.out.println("6. lyrics ");
                    System.out.println("7. composer ");
                    System.out.println("8. publisher ");
                    System.out.println("9. originalArtist ");
                    System.out.println("10. albumArtist ");
                    System.out.println("11. copyright ");
                    System.out.println("12. encoder ");
                    option = myObj.nextLine();
                    System.out.println("Enter the new tag");
                    newTagName = myObj.nextLine();
                    if(option.equals("1")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setTrack(newTagName);
                    } else if(option.equals("2")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setArtist(newTagName);
                    } else if(option.equals("3")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setTitle(newTagName);
                    } else if(option.equals("4")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setAlbum(newTagName);
                    } else if(option.equals("5")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setYear(newTagName);
                    } else if(option.equals("6")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setLyrics(newTagName);
                    } else if(option.equals("7")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setComposer(newTagName);
                    } else if(option.equals("8")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setPublisher(newTagName);
                    } else if(option.equals("9")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setOriginalArtist(newTagName);
                    } else if(option.equals("10")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setAlbumArtist(newTagName);
                    } else if(option.equals("11")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setCopyright(newTagName);
                    } else if(option.equals("12")){
                        System.out.println("mp3 tag has been changed");
                        mp3Test.setEncoder(newTagName);
                    }
                } catch (NonExistantFileException e) {
                    System.out.println("No file found for object creation ");
                }
            }
            else if(option.equals("7")){
                System.out.println("Enter mp3 file name: ");
                inputPath = myObj.nextLine();
                try {
                    MusicFile mp3Test = new MusicFile(inputPath);
                    System.out.println("track: " + mp3Test.track);
                    System.out.println("artist: " + mp3Test.artist);
                    System.out.println("title: " + mp3Test.title);
                    System.out.println("album: " + mp3Test.album);
                    System.out.println("year: " + mp3Test.year);
                    System.out.println("lyrics: " + mp3Test.lyrics);
                    System.out.println("composer: " + mp3Test.composer);
                    System.out.println("publisher: " + mp3Test.publisher);
                    System.out.println("originalArtist: " + mp3Test.originalArtist);
                    System.out.println("albumArtist: " + mp3Test.albumArtist);
                    System.out.println("copyright: " + mp3Test.copyright);
                    System.out.println("encoder: " + mp3Test.encoder);
                } catch (NonExistantFileException e) {
                    System.out.println("No file found for object creation ");
                }
            }
            else if(option.equals("8")){
                System.out.println("Enter file path: ");
                inputPath = myObj.nextLine();
                try {
                    ExecutableFile exefile = new ExecutableFile(inputPath);
                    if(exefile.executePermission == true){
                        exefile.execute();
                    } else{
                        System.out.println("No executable permission to execute file");
                        System.out.println("read: " + exefile.readPermission);
                        System.out.println("write: " + exefile.writePermission); 
                        System.out.println("execute: " + exefile.executePermission);  
                    }
                } catch (NonExistantFileException e) {
                    System.out.println("file cannot be opened");
                }
            }
            else if(option.equals("9")){
                System.exit(0);
            }
        }
    }
}

//javac -d ClassFiles -cp ".;com" FileClasses/*.java Main.java com/mpatric/mp3agic/*.java

//java -cp ClassFiles Main 