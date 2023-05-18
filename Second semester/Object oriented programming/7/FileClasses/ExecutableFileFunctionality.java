package FileClasses;

// Interface extending the File interface for text file
public interface ExecutableFileFunctionality extends FileFunctionality{
    void updatePermissions() throws InvalidFileFormatException;
}
