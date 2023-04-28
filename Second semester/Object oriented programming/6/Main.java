//Konstantinas Arelis 3gr

import java.io.IOException;

import FileClasses.FileFunctionality;
import FileClasses.InvalidFileFormatException;
import FileClasses.NegativeSizeException;
import FileClasses.TxtFile;

public class Main{
    public static void main(String[] args) throws CloneNotSupportedException, NegativeSizeException, IOException{
        char[] contents1=null;
        //String textFilePath = "/Users/kostasarelis/Desktop/SoftwareEngineeringVU-1/Second semester/Object oriented programming/6/text.txt";
        String textFilePath = "C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\6\\text.txt";


        TxtFile textFile = new TxtFile(textFilePath);
        TxtFile t3 = (TxtFile)textFile.clone();

        //t3.changePath("C:\\Users\\rx580\\Desktop\\SoftwareEngineeringVU-1\\Second semester\\Object oriented programming\\6\\textClone.txt");
        //t3.createFile();
        

        //System.out.println(contents1);
    }
}
// javac Main.java FileClasses\TxtFile.java FileClasses\MyFile.java FileClasses\TextFileFunctionality.java FileClasses\FileParamaterException.java FileClasses\InvalidFileFormatException.java FileClasses\NegativeSizeException.java FileClasses\TextFileFunctionality.java 