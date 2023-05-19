package FileClasses;

public class InvalidFileFormatException extends FileParamaterException{
    String fileName = "NoName";
    public InvalidFileFormatException(){}
    public InvalidFileFormatException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }
    public String getter(){
        return fileName;
    }
}