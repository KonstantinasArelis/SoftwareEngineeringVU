package FileClasses;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

// Regular class implementing the Text File interface
public class TxtFile extends MyFile implements TextFileFunctionality{
    protected String encoding = "Unknown";

    public TxtFile(String path){
        super(path);
        try{
            this.encoding = getEncoding();
        }catch(InvalidFileFormatException e){
            System.out.println("wrong file type for getting encoding ");
        }
        
    }

    @Override
    public String getEncoding() throws InvalidFileFormatException{
        FileInputStream fis = null;
        InputStreamReader isr = null;
        if(!this.filePath.toString().endsWith(".txt")){
            throw new InvalidFileFormatException("Cant get encoding of a not .txt file",this.filePath.toString());
        }
        if(this.getFileSize()<0){
            throw new InvalidFileFormatException("Cant get encoding of a not .txt file",this.filePath.toString());
        }
        else{

        
        try{
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis);

            return isr.getEncoding();
        
        }catch(FileNotFoundException e){
            System.out.println("No file found for getting encoding ");
            return "Unknown";
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException e){
                    System.out.println("Could not close FileInputStream");
                }
            }
            if(isr != null){
                try{
                    isr.close();
                }catch(IOException e){
                    System.out.println("Could not close InputStreamReader");
                }
            }
        }
        }
    }

    @Override
    public char[] getTextContents(){
        Reader reader = null;
        char[] charBuffer = null;
        try{
            reader = new FileReader(filePath);
            charBuffer = new char[(int) fileSize];
            reader.read(charBuffer);
            return charBuffer;
        }catch(IOException e){
            System.out.println("IOException for reading a text file");
            return charBuffer;
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    System.out.println("Could not close reader stream");
                }
            }
        }
    }
}
