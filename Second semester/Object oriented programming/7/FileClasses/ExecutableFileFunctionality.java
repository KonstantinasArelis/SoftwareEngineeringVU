package FileClasses;

// Interface extending the File interface for text file

public interface ExecutableFileFunctionality extends FileFunctionality{
    /**
     * this method updates the permission field of the object
     * @throws InvalidFileFormatException is thrown when the file is not .exe type
     */
    void updatePermissions() throws InvalidFileFormatException;
}
