package FileClasses;

public class InvalidFileFormatException extends FileParamaterException{
    String fileName;
    public InvalidFileFormatException(){}
    public InvalidFileFormatException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }
    /* 
    public InvalidFileFormatException(Throwable cause){
        super(cause);
    }
    public InvalidFileFormatException(String message, Throwable cause){
        super(message, cause);
    }
     */
}