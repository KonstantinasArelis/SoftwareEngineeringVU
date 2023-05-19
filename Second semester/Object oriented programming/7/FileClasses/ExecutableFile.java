package FileClasses;


// Regular class implementing the Text File interface
public class ExecutableFile extends MyFile implements ExecutableFileFunctionality{
    public String permissions = "Unknown";
    
    public ExecutableFile(){
        super();
    }

    public ExecutableFile(String path) throws NonExistantFileException{
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

    public void update(ExecutableFile newExeFile){
        if(newExeFile != null){
            filePath = newExeFile.filePath;
            fileSize = newExeFile.fileSize;
            isHidden = newExeFile.isHidden;
            fileExtension = newExeFile.fileExtension;
            permissions = newExeFile.permissions;
        }
    }
}
