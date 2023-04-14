package FileClasses;

// Interface extending the File interface for text file
public interface TextFileFunctionality extends FileFunctionality{
    char[] getTextContents();
    String getEncoding() throws InvalidFileFormatException;
}
