package FileClasses;

// Interface extending the File interface for text file
public interface ExeFileFunctionality extends FileFunctionality{
    String getPermissions() throws InvalidFileFormatException;
}
