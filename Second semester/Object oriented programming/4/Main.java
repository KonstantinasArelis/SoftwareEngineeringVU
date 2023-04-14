//Konstantinas Arelis 3gr

import FileClasses.FileFunctionality;
import FileClasses.InvalidFileFormatException;
import FileClasses.TxtFile;
// 4 etapas
//atskirus failus klasem, jokiu println, panaudoti interface kazkaip
public class Main{
    public static void main(String[] args){
        // sukurt interface objekta, iskviest jo metoda?
        char[] contents;
        String textFilePath = "/Users/kostasarelis/Desktop/lit4/text.txt";

        TxtFile textFile = new TxtFile(textFilePath);
        FileFunctionality vienas = new TxtFile("/Users/kostasarelis/Desktop/lit4/text.txt");

        //4 etapas - reikia padaryt kad catchintu ir bazini exception klase
        System.out.println("File size: " + vienas.getFileSize());
        contents = textFile.getTextContents();
        try{
            System.out.println("File encoding: " + textFile.getEncoding());
        }catch(InvalidFileFormatException e){
            System.out.println("wrong file type for getting encoding ");
        }
        
        System.out.println("File content: " + new String(contents));


    }
}
