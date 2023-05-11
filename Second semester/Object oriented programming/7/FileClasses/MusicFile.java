package FileClasses;

//import com.mpatric.mp3agic.*;
import java.io.Serializable;

public class MusicFile extends MyFile implements Serializable{
    protected String track; // ID3v1 
    protected String artist; // ID3v1
    protected String title; // ID3v1
    protected String album; // ID3v1
    protected String year; // ID3v1
    protected String genre; // ID3v1
    protected String comment; // ID3v1
    protected String lyrics; // ID3v2
    protected String composer; // ID3v2
    protected String publisher; // ID3v2
    protected String originalArtist; // ID3v2
    protected String albumArtist; // ID3v2
    protected String copyright; // ID3v2
    protected String URL; // ID3v2
    protected String encoder; // ID3v2
    
    public MusicFile() {
        super();
    }

    public MusicFile(String path) throws NonExistantFileException{
        super(path);
    }

}
