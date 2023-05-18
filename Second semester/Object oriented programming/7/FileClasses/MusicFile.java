package FileClasses;

import java.io.File;
import java.io.IOException;
//import com.mpatric.mp3agic.*;
import com.mpatric.mp3agic.*;

public class MusicFile extends MyFile implements MusicFileFunctionality{
    public String track; // ID3v1 
    public String artist; // ID3v1
    public String title; // ID3v1
    public String album; // ID3v1
    public String year; // ID3v1
    public String lyrics; // ID3v2
    public String composer; // ID3v2
    public String publisher; // ID3v2
    public String originalArtist; // ID3v2
    public String albumArtist; // ID3v2
    public String copyright; // ID3v2
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
            this.year = id3v2Tag.getYear();
            this.lyrics = id3v2Tag.getLyrics();
            this.composer = id3v2Tag.getComposer();
            this.originalArtist = id3v2Tag.getOriginalArtist();
            this.albumArtist = id3v2Tag.getAlbumArtist();
            this.copyright = id3v2Tag.getCopyright();
            this.encoder = id3v2Tag.getEncoder();
        } else if (mp3file.hasId3v1Tag()){
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            this.track = id3v1Tag.getTrack();
            this.artist = id3v1Tag.getArtist();
            this.title = id3v1Tag.getTitle();
            this.album = id3v1Tag.getAlbum();
            this.year = id3v1Tag.getYear();
        }
    }

    @Override
    public void setTrack(String newTrack){
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
            id3v2Tag.setTrack(newTrack);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
            //System.out.println("TEST@: " + newArtist);
            id3v2Tag.setTitle(newTitle);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
            //System.out.println("TEST@: " + newArtist);
            id3v2Tag.setAlbum(newAlbum);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setYear(String newYear){
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
            id3v2Tag.setYear(newYear);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setLyrics(String newLyrics){
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
            id3v2Tag.setLyrics(newLyrics);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setComposer(String newComposer){
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
            id3v2Tag.setComposer(newComposer);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setPublisher(String newPublisher){
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
            id3v2Tag.setPublisher(newPublisher);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setOriginalArtist(String newOriginalArtist){
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
            id3v2Tag.setOriginalArtist(newOriginalArtist);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setAlbumArtist(String newAlbumArtist){
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
            id3v2Tag.setAlbumArtist(newAlbumArtist);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setCopyright(String newCopyright){
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
            id3v2Tag.setCopyright(newCopyright);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
    public void setEncoder(String newEncoder){
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
            id3v2Tag.setEncoder(newEncoder);
            try {
                String fileName = this.filePath.getName();
                mp3file.save("temporary.mp3");
                this.deleteFile();
                filePath = new File(filePath.getParent(), "temporary.mp3");
                this.renameFile(fileName);
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
