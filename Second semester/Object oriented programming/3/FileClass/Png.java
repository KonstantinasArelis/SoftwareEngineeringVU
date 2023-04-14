package FileClass;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import java.util.*;
import java.io.IOException;


public class Png extends MyFile {
    float aspectRatio = this.aspectRatio();
    

    public Png(String fileName, String permission) {
        super(fileName, permission);
    }

    public Png(String fileName) {
        super(fileName);
    }

    public Png() {
        super();
    }

    public float aspectRatio(){
        ImageInputStream input = null;
        try {
            input = ImageIO.createImageInputStream(getFile());
            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                reader.setInput(input);
                return reader.getAspectRatio(0);
            } else {
                throw new IOException("No image reader found for file: " + getFile().getName());
            }
        } catch (IOException e) {
            System.out.println("IOException");
            return -1;
        } finally {
            try{
                if (input != null) {
                input.close();
                }
            } catch(IOException e){
                System.out.println("IOException");
                return -1;
            }
        }

    }

    public int calculateHeight(){//calculateHeight
        ImageInputStream input = null;
        try {
            input = ImageIO.createImageInputStream(getFile());
            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                reader.setInput(input);
                return reader.getHeight(0);
            } else {
                throw new IOException("No image reader found for file: " + getFile().getName());
            }
        } catch (IOException e) {
            System.out.println("IOException");
            return -1;
        } finally {
            try{
                if (input != null) {
                input.close();
                }
            } catch(IOException e){
                System.out.println("IOException");
                return -1;
            }
        }
    }

    public static int fileInstances(){
        return fileInstances;
    }

    public String toString(){
        return getFile().toString()+" file aspect ratio: "+aspectRatio;
    }

    void Print()
    {
        // Print statement
        System.out.println("png path: " + getFile().toString());
    }
}