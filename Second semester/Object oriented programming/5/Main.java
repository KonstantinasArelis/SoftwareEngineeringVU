//Konstantinas Arelis 3gr

import FileClasses.FileFunctionality;
import FileClasses.InvalidFileFormatException;
import FileClasses.NegativeSizeException;
import FileClasses.TxtFile;

public class Main{
    public static void main(String[] args){
        char[] contents;
        String textFilePath = "/Users/kostasarelis/Desktop/lit4/text.txt";

        TxtFile textFile = new TxtFile(textFilePath);
        FileFunctionality vienas = new TxtFile("/Users/kostasarelis/Desktop/lit4/text.txt");

        System.out.println("File size: " + vienas.getFileSize());

        try{
            contents = textFile.getTextContents();
        }catch(NegativeSizeException e){
            System.out.println("Negative file size error in file ");
        }
        
        try{
            System.out.println("File encoding: " + textFile.getEncoding());
        }catch(InvalidFileFormatException e){
            System.out.println("wrong file type for getting encoding error");
        }

    }
}
