package FileClasses;



public class MediaFile extends MyFile{
    
    
    public MediaFile() {
        super();
    }

    public MediaFile(String path) throws NonExistantFileException{
        super(path);
    }

}
