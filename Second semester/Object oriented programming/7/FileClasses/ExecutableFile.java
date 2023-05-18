package FileClasses;

import java.io.IOException;

// Regular class implementing the Text File interface
public class ExecutableFile extends MyFile implements ExecutableFileFunctionality{
    public boolean readPermission;
    public boolean writePermission;
    public boolean executePermission;
    public ExecutableFile(){
        super();
    }

    public ExecutableFile(String path) throws NonExistantFileException{
        super(path);
        try {
            this.updatePermissions();
        } catch (InvalidFileFormatException e) {
            System.out.println("getting permissions error ");
        }
    }

    @Override
    public void updatePermissions() throws InvalidFileFormatException{
        if(!this.filePath.toString().endsWith(".exe")){
            throw new InvalidFileFormatException("Cant get permissions of a not .exe file",this.filePath.toString());
        }else{
            if (filePath.exists()) {
                if(filePath.canExecute()){
                    readPermission = true;
                }else{
                    readPermission = false;
                }
                if(filePath.canRead()){
                    readPermission = true;
                }else{
                    readPermission = false;
                }
                if(filePath.canWrite()){
                    writePermission = true;
                }else{
                    writePermission = false;
                }
            }
            else {
                System.out.println("Executable does not exist");
            }
        }
    }

    public void execute() {
        try {
            // Construct the command to execute the file with the provided arguments
            String command = filePath.toString();

            // Execute the command using the operating system's command line
            Process process = Runtime.getRuntime().exec(command);

            // Wait for the process to finish
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(ExecutableFile newExeFile){
        if(newExeFile != null){
            filePath = newExeFile.filePath;
            fileSize = newExeFile.fileSize;
            isHidden = newExeFile.isHidden;
            fileExtension = newExeFile.fileExtension;
            readPermission = newExeFile.readPermission;
            writePermission = newExeFile.writePermission;
            executePermission = newExeFile.executePermission;
        }
    }
}
