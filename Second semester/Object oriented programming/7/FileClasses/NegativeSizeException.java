package FileClasses;

public class NegativeSizeException extends FileParamaterException{
    String fileName = "NoName";

    /**
     * empty constructor
     */
    public NegativeSizeException(){}

    /**
     * this exception represents when there is a file with a negative size
     * @param message error messege to be displayed
     * @param fileName file name of the file associated with error
     */
    public NegativeSizeException(String message, String fileName){
        super(message);
        this.fileName=fileName;
    }

    /**
     * getter for the file associated with error
     * @return file name associated with error
     */
    public String getter(){
        return fileName;
    }
}