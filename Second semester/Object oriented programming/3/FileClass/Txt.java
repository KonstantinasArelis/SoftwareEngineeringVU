package FileClass;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Txt extends MyFile {
    String encoding;

    public Txt(String fileName, String permission) {
        super(fileName, permission);
        encoding = getEncodingInfo();
        fileInstances++;
    }

    public Txt(String fileName) {
        super(fileName);
        encoding = getEncodingInfo();
        fileInstances++;
    }

    public Txt() {
        super();
        encoding = "Unknown encoding";
        fileInstances++;
    }

    String getEncodingInfo(){
        
        InputStreamReader isr = null;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(getFile());
            isr = new InputStreamReader(fis);
            //System.out.println("Encoding: "+ isr.getEncoding());
            try{
                fis.close();
                isr.close(); 
            }catch(IOException e){
                System.out.println("IOException");
                return "IOException";
            }
            return isr.getEncoding(); 
        }catch(FileNotFoundException e){
            System.out.println("FileNotFoundException");
            return "FileNotFoundException";
        }
    }

    public int appendFile(byte b[]){ // overriden method
        String str = new String(b, StandardCharsets.UTF_8); // for UTF-8 encoding
        //System.out.println("msg: "+ str);
        try {
            FileWriter myWriter = new FileWriter(getFile(),true);
            myWriter.write(str);
            myWriter.close();
            return 0;
          } catch (IOException e) {
            System.out.println("An error occurred.");
            return -1;
          }
    }
    
    public String toString(){
        return getFile().toString()+" file encoding" + encoding;
    }

    void Print()
    {
        // Print statement
        System.out.println("Txt path: " + getFile().toString());
    }
}