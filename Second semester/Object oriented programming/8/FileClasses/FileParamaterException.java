package FileClasses;

public class FileParamaterException extends Exception{
    /**
     * this exception represents the incorrect paramaters of a file
     */
    public FileParamaterException(){}

    /**
     * this exception represents the incorrect paramaters of a file
     * @param message messege is a describtion of the error
     */
    public FileParamaterException(String message){
        super(message);
    }
}