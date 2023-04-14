package FileClasses;

public class NegativeSizeException extends FileParamaterException{
    public NegativeSizeException(){}
    public NegativeSizeException(String message){
        super(message);
    }
    public NegativeSizeException(Throwable cause){
        super(cause);
    }
    public NegativeSizeException(String message, Throwable cause){
        super(message, cause);
    }
}