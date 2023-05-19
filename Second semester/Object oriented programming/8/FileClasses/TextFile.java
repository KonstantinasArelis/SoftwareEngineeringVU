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

    /**
     * this constructor creates an object of a text file and initialises its encoding
     * @param path full path to the file
     * @throws NonExistantFileException occurs when an objects is to be created of an non existant file
     */
    public TextFile(String path) throws NonExistantFileException {
        super(path);
        try {
            this.encoding = getEncoding();
            readTextContents();
        } catch (InvalidFileFormatException e) {
            //System.out.println("getting encoding error ");
        }
    }

    /**
     * this method finds the encoding of the text file
     * @return the encoding of the text file
     * @throws InvalidFileFormatException when a file is not a .txt file type
     */
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

    /**
     * reads and saves the textContents field accoring to the text file
     */
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

    /**
     * writes the file with a string
     */
    private void writeTextContents() {
        try (FileWriter fw = new FileWriter(filePath.toString())) {
            fw.write(textContents);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * appends the text file with a string
     * @param text the text to be appended to file
     */
    public void appendToTextContents(String text) {
        textContents += text;
        writeTextContents();
    }

    /**
     * this method updates the passed object
     * @param newTextFile this variable is used to refer to an object which is to be updated
     */
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
