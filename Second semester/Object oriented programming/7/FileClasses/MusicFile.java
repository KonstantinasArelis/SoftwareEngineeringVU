package FileClasses;

import java.io.IOException;
//import com.mpatric.mp3agic.*;
import com.mpatric.mp3agic.*;

public class MusicFile extends MyFile implements MusicFileFunctionality{
    public String track; // ID3v1 
    public String artist; // ID3v1
    public String title; // ID3v1
    public String album; // ID3v1
    public String year; // ID3v1
    public String genre; // ID3v1
    public String comment; // ID3v1
    public String lyrics; // ID3v2
    public String composer; // ID3v2
    public String publisher; // ID3v2
    public String originalArtist; // ID3v2
    public String albumArtist; // ID3v2
    public String copyright; // ID3v2
    public String URL; // ID3v2
    public String encoder; // ID3v2
    
    public MusicFile() {
        super();
    }

    public MusicFile(String path) throws NonExistantFileException{
        super(path);

        Mp3File mp3file = null;
        try {
            mp3file = new Mp3File(filePath);
        } catch (UnsupportedTagException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(mp3file.hasId3v2Tag()){
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            this.track = id3v2Tag.getTrack();
            this.artist = id3v2Tag.getArtist();
            this.title = id3v2Tag.getTitle();
            this.album = id3v2Tag.getAlbum();
            this.genre = id3v2Tag.getYear();
            this.comment = id3v2Tag.getComment();

        } else if (mp3file.hasId3v1Tag()){
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            this.track = id3v1Tag.getTrack();
            this.artist = id3v1Tag.getArtist();
        }
    }

    @Override
    public void setArtist(String newArtist){
        Mp3File mp3file = null;
        try {
            mp3file = new Mp3File(filePath);
        } catch (UnsupportedTagException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(mp3file.hasId3v2Tag()){
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            //System.out.println("TEST@: " + newArtist);
            id3v2Tag.setArtist(newArtist);
            try {
                mp3file.save("exampleMusicFile.mp3");
            } catch (NotSupportedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public void setTitle(String newTitle){
        Mp3File mp3file = null;
        try {
            mp3file = new Mp3File(filePath);
        } catch (UnsupportedTagException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(mp3file.hasId3v2Tag()){
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            id3v2Tag.setTitle(newTitle);
            try {
                mp3file.save("exampleMusicFile.mp3");
            } catch (NotSupportedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public void setAlbum(String newAlbum){
        Mp3File mp3file = null;
        try {
            mp3file = new Mp3File(filePath);
        } catch (UnsupportedTagException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(mp3file.hasId3v2Tag()){
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            id3v2Tag.setAlbum(newAlbum);
            try {
                mp3file.save("exampleMusicFile2.mp3");
            } catch (NotSupportedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void update(MusicFile newMusicFile){
        if(newMusicFile != null){
            filePath = newMusicFile.filePath;
            fileSize = newMusicFile.fileSize;
            isHidden = newMusicFile.isHidden;
            fileExtension = newMusicFile.fileExtension;
            artist = newMusicFile.artist;
            title = newMusicFile.title;
            album = newMusicFile.album;
        }
    }
}
