package FileClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Load implements Serializable, Runnable{

    TextFile txtFile;
    ExecutableFile exeFile;
    MusicFile musicFile;

    public Load(TextFile txtFile1, ExecutableFile exeFile1, MusicFile musicFile1) {
        this.txtFile = txtFile1;
        this.exeFile = exeFile1;
        this.musicFile = musicFile1;
    }

    @Override
    public void run() {

        try {
            FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            //txtFile = (TextFile) objectInputStream.readObject();
            //exeFile = (ExecutableFile) objectInputStream.readObject();
            //musicFile = (MusicFile) objectInputStream.readObject();

            txtFile.update((TextFile) objectInputStream.readObject());
            exeFile.update((ExecutableFile) objectInputStream.readObject());
            musicFile.update((MusicFile) objectInputStream.readObject());

            objectInputStream.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("error: File not found exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("error: IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("error: ClassNotFoundException: " + e.getMessage());
        }
    }
}
