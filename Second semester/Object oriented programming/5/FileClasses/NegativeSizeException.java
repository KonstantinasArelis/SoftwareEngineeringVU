package FileClasses;

public class NegativeSizeException extends FileParamaterException{
    String fileName = "NoName";
    public NegativeSizeException(){}
    public NegativeSizeException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }
    public String getter(){
        return fileName;
    }
}