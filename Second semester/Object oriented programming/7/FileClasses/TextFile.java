package FileClasses;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class TextFile extends MyFile implements TextFileFunctionality {
    public String encoding = "Unknown";
    public String textContents = "";

    public TextFile() {
        super();
    }

    public TextFile(String path) throws NonExistantFileException {
        super(path);
        try {
            this.encoding = getEncoding();
            readTextContents();
        } catch (InvalidFileFormatException e) {
            System.out.println("getting encoding error ");
        }
    }

    @Override
    public String getEncoding() throws InvalidFileFormatException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        if (!this.filePath.toString().endsWith(".txt")) {
            throw new InvalidFileFormatException("Cant get encoding of a not .txt file", this.filePath.toString());
        } else {
            try {
                fis = new FileInputStream(filePath);
                isr = new InputStreamReader(fis);

                return isr.getEncoding();
            } catch (FileNotFoundException e) {
                System.out.println("No file found for getting encoding ");
                return "Unknown";
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        System.out.println("Could not close FileInputStream");
                    }
                }
                if (isr != null) {
                    try {
                        isr.close();
                    } catch (IOException e) {
                        System.out.println("Could not close InputStreamReader");
                    }
                }
            }
        }
    }
    
    private void readTextContents() {
        try (FileInputStream fis = new FileInputStream(filePath.toString());
             InputStreamReader isr = new InputStreamReader(fis)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = isr.read()) != -1) {
                sb.append((char) c);
            }
            textContents = sb.toString();
        } catch (IOException e) {
            System.out.println("Error reading file contents: " + e.getMessage());
        }
    }

    private void writeTextContents() {
        try (FileWriter fw = new FileWriter(filePath.toString())) {
            fw.write(textContents);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public void appendToTextContents(String text) {
        textContents += text;
        writeTextContents();
    }

    public void update(TextFile newTextFile) {
        filePath = newTextFile.filePath;
        fileSize = newTextFile.fileSize;
        isHidden = newTextFile.isHidden;
        fileExtension = newTextFile.fileExtension;
        encoding = newTextFile.encoding;
        textContents = newTextFile.textContents;
        //writeTextContents();
    }
}
