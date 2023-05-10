package FileClasses;

// Regular class implementing the Text File interface
public class ExeFile extends MyFile implements ExeFileFunctionality{
    public String permissions = "Unknown";
    
    public ExeFile(String path){
        super(path);
        try {
            this.permissions = getPermissions();
        } catch (InvalidFileFormatException e) {
            System.out.println("getting permissions error ");
        }
    }

    @Override
    public String getPermissions() throws InvalidFileFormatException{
        String permissions = "None Set";
        if(!this.filePath.toString().endsWith(".exe")){
            throw new InvalidFileFormatException("Cant get permissions of a not .exe file",this.filePath.toString());
        }else{
            if (filePath.exists()) {
                permissions="";
                if(filePath.canExecute()){
                    permissions = permissions + "e";
                }else{
                    permissions = permissions + "-";
                }
                if(filePath.canRead()){
                    permissions = permissions + "r";
                }else{
                    permissions = permissions + "-";
                }
                if(filePath.canWrite()){
                    permissions = permissions + "w";
                }else{
                    permissions = permissions + "-";
                }
            }
            else {
                System.out.println("Executable does not exist");
            }
        }
        return permissions;
    }
}
