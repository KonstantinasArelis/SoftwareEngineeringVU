package FileClasses;

// Interface extending the File interface for text file
public interface TextFileFunctionality extends FileFunctionality{
    /**
     * this method finds the encoding of the text file
     * @return the encoding of the text file
     * @throws InvalidFileFormatException when a file is not a .txt file type
     */
    String getEncoding() throws InvalidFileFormatException;
}
