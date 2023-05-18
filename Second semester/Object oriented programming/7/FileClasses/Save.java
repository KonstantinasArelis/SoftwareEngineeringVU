package FileClasses;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Save implements Serializable, Runnable{

    TextFile txtFile;
    ExecutableFile exeFile;
    MusicFile musicFile;

    public Save(TextFile txtFile, ExecutableFile exeFile, MusicFile musicFile) {
        this.txtFile = txtFile;
        this.exeFile = exeFile;
        this.musicFile = musicFile;
    }

    @Override
    public void run() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(txtFile);
            objectOutputStream.writeObject(exeFile);
            objectOutputStream.writeObject(musicFile);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("error: File not found exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("error: IOException: " + e.getMessage());
        }
    }
}
