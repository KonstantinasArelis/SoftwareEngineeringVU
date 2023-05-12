package FileClasses;
import java.io.File;
import java.io.Serializable;

// Abstract class implementing the general File interface
public abstract class MyFile implements FileFunctionality, Serializable{
    public File filePath;
    public long fileSize = 0;
    public boolean isHidden;
    public String fileExtension;


    public MyFile(){

    }

    public MyFile(String path) throws NonExistantFileException{
        this.filePath = new File(path);

        if(filePath.exists()){
            int i = filePath.getName().lastIndexOf('.');
            if (i > 0) {
                this.fileExtension = filePath.getName().substring(i+1);
            }

            this.isHidden = filePath.isHidden();
            this.fileSize = filePath.length();

        } else {
            throw new NonExistantFileException("The file does not exist",this.filePath.toString());
        }
    }
    
}
