package FileClasses;

import java.io.Serializable;

public class MediaFile extends MyFile implements Serializable{
    
    
    public MediaFile() {
        super();
    }

    public MediaFile(String path) throws NonExistantFileException{
        super(path);
    }

}
