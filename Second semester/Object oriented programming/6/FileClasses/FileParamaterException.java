package FileClasses;

public class FileParamaterException extends Exception{
    public FileParamaterException(){}
    public FileParamaterException(String message){
        super(message);
    }
    public FileParamaterException(Throwable cause){
        super(cause);
    }
    public FileParamaterException(String message, Throwable cause){
        super(message, cause);
    }
}