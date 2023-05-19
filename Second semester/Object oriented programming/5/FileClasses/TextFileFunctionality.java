package FileClasses;

// Interface extending the File interface for text file
public interface TextFileFunctionality extends FileFunctionality{
    char[] getTextContents() throws NegativeSizeException;
    String getEncoding() throws InvalidFileFormatException;
}
