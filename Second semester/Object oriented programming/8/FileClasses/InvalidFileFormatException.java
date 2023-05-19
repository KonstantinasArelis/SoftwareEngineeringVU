package FileClasses;

public class InvalidFileFormatException extends FileParamaterException{
    String fileName = "NoName";
    public InvalidFileFormatException(){}

    /**
     * is thrown when the file format of a file is incorrect
     * @param message describes the error of exception
     * @param fileName describes the filename of file which caused the exception
     */
    public InvalidFileFormatException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }

    /**
     * getter for filename
     * @return filename, the name of the file
     */
    public String getter(){
        return fileName;
    }
}