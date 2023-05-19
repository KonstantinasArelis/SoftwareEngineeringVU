package FileClasses;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.File;

class FileSize{
    long size;
    String measurment;

    public FileSize(){
            this.size = 0;
            this.measurment = "B";
    }

    public FileSize(long size1, String measurment1){
        this.size = size1;
        this.measurment = measurment1;
}
} 

// Regular class implementing the Text File interface
public class TxtFile extends MyFile implements TextFileFunctionality, Cloneable {
    protected String encoding = "Unknown";
    private String fileContents;
    public FileSize size = new FileSize();
    
    
    public TxtFile(String path) throws IOException {
        super(path);
        this.fileContents = readFileContents(filePath.toString());
        /*
        try{
            this.encoding = getEncoding();
        }catch(InvalidFileFormatException e){
            System.out.println("wrong file type for getting encoding ");
        }
         */
        
    }
    
    public void setFileSize(long sizeOfFile, String measurmentOfFile){
        size.size=sizeOfFile;
        size.measurment=measurmentOfFile;
    }
    
    public long getFileSize(){
        return size.size;
    }

    public String getFilemeasurment(){
        return size.measurment;
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

    @Override
    public char[] getTextContents() throws NegativeSizeException{
        Reader reader = null;
        char[] charBuffer = null;
        if(this.getFileSize()<0){
            throw new NegativeSizeException("Cant get contents of an invalid size file",this.filePath.toString());
        }else{
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

    private String readFileContents(String filePath) throws IOException {
        StringBuilder contents = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contents.append(line);
                contents.append(System.lineSeparator());
            }
        }
        return contents.toString();
    }

    public void appendStringToFile(String str) throws IOException {
        this.fileContents += str;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true))) {
            writer.write(str);
        }
    }

    public void createFile() {
        try {
            File file = new File(filePath.toString().substring(0, filePath.toString().indexOf(".", 0))+"Clone.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

     @Override
    public  TxtFile clone() throws CloneNotSupportedException{
        try {
            TxtFile t = (TxtFile)super.clone();

            
            //t.fileContents = new String(this.fileContents); // deep copy
           //t.encoding = new String(this.encoding);
           //t.filePath = new File(this.filePath.toString());
           //t.size = new FileSize(this.size.size, this.size.measurment);

           return t;
        }
        catch (CloneNotSupportedException exc) {
           throw new Error ("Cloning exception");
        }
     }
}
