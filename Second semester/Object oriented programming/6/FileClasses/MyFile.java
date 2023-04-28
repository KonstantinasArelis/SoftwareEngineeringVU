package FileClasses;
import java.io.File;
import java.io.File;
import java.io.IOException;


// Abstract class implementing the general File interface
public abstract class MyFile implements FileFunctionality{
    protected File filePath;
    protected long fileSize = 0;

    
    public MyFile(String path){
        this.filePath = new File(path);
        this.fileSize = filePath.length();
    }

    public void setFileSize(long size){//pakeist
        this.fileSize=size;
        //return fileSize;
        //System.out.println("File path: " + filePath.toString());
        //System.out.println("File size: " + fileSize + " bytes");
    }

    @Override
    public long getFileSize(){//pakeist
        return fileSize;
        //System.out.println("File path: " + filePath.toString());
        //System.out.println("File size: " + fileSize + " bytes");
    }

    public void changePath(String newPath){
        File newNewFile = new File(newPath);
        filePath.renameTo(newNewFile);
    }
}
