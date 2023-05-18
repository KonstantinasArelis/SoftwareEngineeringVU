package FileClasses;



public class MediaFile extends MyFile{
    private int duration;
    private String resolution;
    private String director;
    
    public MediaFile() {
        super();
    }

    public MediaFile(String path) throws NonExistantFileException{
        super(path);
    }

}
