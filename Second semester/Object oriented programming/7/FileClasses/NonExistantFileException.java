package FileClasses;

public class NonExistantFileException extends FileParamaterException{
    String fileName = "NoName";
    
    public NonExistantFileException(){}
    
    public NonExistantFileException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }
    public String getter(){
        return fileName;
    }
}