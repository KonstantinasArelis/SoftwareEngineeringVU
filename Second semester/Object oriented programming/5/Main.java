//Konstantinas Arelis 3gr

import FileClasses.FileFunctionality;
import FileClasses.InvalidFileFormatException;
import FileClasses.NegativeSizeException;
import FileClasses.TxtFile;

public class Main{
    public static void main(String[] args){
        char[] contents;
        String textFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU-1/Second semester/Object oriented programming/5/text.mp4";

        TxtFile textFile = new TxtFile(textFilePath);
        FileFunctionality vienas = new TxtFile(textFilePath);
        
        System.out.println("File size: " + vienas.getFileSize());

        try{
            contents = textFile.getTextContents();
        }catch(NegativeSizeException e){
            System.out.println(e.getMessage() + e.getter());
        }
        
        try{
            System.out.println("File encoding: " + textFile.getEncoding());
        }catch(InvalidFileFormatException e){
            System.out.println(e.getMessage() + e.getter());
        }
    }
}
