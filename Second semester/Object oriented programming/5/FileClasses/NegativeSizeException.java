package FileClasses;

public class NegativeSizeException extends FileParamaterException{
    String fileName;
    public NegativeSizeException(){}
    public NegativeSizeException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }
}