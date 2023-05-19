package FileClasses;

public class NonExistantFileException extends FileParamaterException{
    String fileName = "NoName";

    /**
     * empty constructor
     */
    public NonExistantFileException(){}

    /**
     * this exception occurs when a object is created of a non existant file
     * @param message error messege to be displayed
     * @param fileName file name associated with error
     */
    public NonExistantFileException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }

    /**
     * getter of the file name associated with error
     * @return file name associated with error
     */
    public String getter(){
        return fileName;
    }
}