//Konstantinas Arelis 3gr

import java.io.IOException;

import FileClasses.FileFunctionality;
import FileClasses.InvalidFileFormatException;
import FileClasses.NegativeSizeException;
import FileClasses.TxtFile;

public class Main{
    public static void main(String[] args) throws CloneNotSupportedException, NegativeSizeException, IOException, InvalidFileFormatException{
        String textFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/6/text.txt";
        //String textFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\6\\text.txt";
        //shallow ir deep clone

        TxtFile textFile = new TxtFile(textFilePath);
        TxtFile t3 = (TxtFile)textFile.clone();
        //textFile.changePath("/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/6/text2.txt");
        //t3.changePath("/Users/kostasarelis/Desktop/SoftwareEngineeringVU/Second semester/Object oriented programming/6/text3.jpeg");
        //t3.getEncoding();
        
        textFile.setFileSize(20,"B");
        t3.setFileSize(10,"B");

        
        System.out.println("textfile size: "+ textFile.getFileSize());
        System.out.println("t3 size: "+ t3.getFileSize());
    }
}
// javac Main.java FileClasses/TxtFile.java FileClasses/MyFile.java FileClasses/TextFileFunctionality.java FileClasses/FileParamaterException.java FileClasses/InvalidFileFormatException.java FileClasses/NegativeSizeException.java FileClasses/TextFileFunctionality.java 