package FileClasses;
import java.io.File;

// Abstract class implementing the general File interface
public abstract class MyFile implements FileFunctionality{
    protected File filePath;
    protected long fileSize = 0;

    public MyFile(){

    }

    public MyFile(String path){
        this.filePath = new File(path);
        this.fileSize = filePath.length();
    }

    @Override
    public long getFileSize(){//pakeist
        return fileSize;
        //System.out.println("File path: " + filePath.toString());
        //System.out.println("File size: " + fileSize + " bytes");
    }
}
