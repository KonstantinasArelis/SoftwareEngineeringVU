package FileClasses;

// Interface extending the File interface for text file
public interface TextFileFunctionality extends FileFunctionality{
    String getEncoding() throws InvalidFileFormatException;
}
