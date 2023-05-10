package FileClasses;

import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

// Regular class implementing the Text File interface
public class TxtFile extends MyFile implements TextFileFunctionality{
    public String encoding = "Unknown";
    
    public TxtFile(String path){
        super(path);
        try {
            this.encoding = getEncoding();
        } catch (InvalidFileFormatException e) {
            System.out.println("getting encoding error ");
        }
    }

    @Override
    public String getEncoding() throws InvalidFileFormatException{
        FileInputStream fis = null;
        InputStreamReader isr = null;
        //System.out.println("TEST " + filePath.toString());
        if(!this.filePath.toString().endsWith(".txt")){
            throw new InvalidFileFormatException("Cant get encoding of a not .txt file",this.filePath.toString());
        }else{
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
}
