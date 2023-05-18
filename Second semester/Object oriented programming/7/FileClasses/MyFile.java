package FileClasses;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;

// Abstract class implementing the general File interface
public abstract class MyFile implements FileFunctionality{
    public File filePath;
    public long fileSize = 0;
    public boolean isHidden;
    public String fileExtension;


    public MyFile(){

    }

    public MyFile(String path) throws NonExistantFileException{
        this.filePath = new File(path);

        if(filePath.exists()){
            int i = filePath.getName().lastIndexOf('.');
            if (i > 0) {
                this.fileExtension = filePath.getName().substring(i+1);
            }

            this.isHidden = filePath.isHidden();
            this.fileSize = filePath.length();

        } else {
            throw new NonExistantFileException("The file does not exist",this.filePath.toString());
        }
    }

    public void copyFile() throws IOException {
        String destinationPath = filePath.getParent() + File.separator + "copy_" + filePath.getName();
        File destinationFile = new File(destinationPath);
        if (destinationFile.exists()) {
            throw new FileAlreadyExistsException("Destination file already exists");
        }
        try (InputStream inputStream = new FileInputStream(filePath);
             OutputStream outputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException("Failed to copy the file", e);
        }
    }

    public static void printDirectorytree(String path, String suffix){
        PrintFileTree(new File(path), suffix, new File(path), new File(path));
    }
    
    public static void printDirectorytree(String path){
        PrintFileTree(new File(path), new File(path), new File(path));
    }

    public static void PrintFileTree(File dir, String suffix, File homeDir, File lastDir){
        File listFile[] = dir.listFiles();
        if(listFile != null){
            for(int i=0; i<listFile.length; i++){
                if(listFile[i].isDirectory()){
                    //PrintDirectory(listFile[i], homeDir);
                    PrintFileTree(listFile[i], suffix, homeDir, lastDir);
                }
            }
    
            for(int i=0; i<listFile.length; i++){
                if(listFile[i].isFile() && listFile[i].toString().endsWith(suffix)){
                    //prints directory if it hasnt been printed before
                    if(!lastDir.equals(dir)){
                        PrintDirectory(dir, homeDir);
                        lastDir=dir;
                    }
                    //prints file name
                    PrintFile(listFile[i], homeDir);
                }
            }
        }
    }

    public static void PrintFileTree(File dir, File homeDir, File lastDir){
        File listFile[] = dir.listFiles();
        if(listFile != null){
            for(int i=0; i<listFile.length; i++){
                if(listFile[i].isDirectory()){
                    //PrintDirectory(listFile[i], homeDir);
                    PrintFileTree(listFile[i], homeDir, lastDir);
                }
            }
    
            for(int i=0; i<listFile.length; i++){
                if(listFile[i].isFile()){
                    //prints directory if it hasnt been printed before
                    if(!lastDir.equals(dir)){
                        PrintDirectory(dir, homeDir);
                        lastDir=dir;
                    }
                    //prints file name
                    PrintFile(listFile[i], homeDir);
                }
            }
        }
    }

    public static void PrintFile(File localDir, File startingDir){
        String fileName = localDir.getName();
        while(!localDir.equals(startingDir)){
            localDir = localDir.getParentFile();
            System.out.print("\t");
        }
        //print file name
        System.out.println(fileName);
    }
    
    public static void PrintDirectory(File localDir, File startingDir){
        //print subdirectory
        File temp1 = localDir;
        //String DirName = localDir.getName();
        while(!localDir.equals(startingDir)){
            localDir = localDir.getParentFile();
            System.out.print("\t");
        }
        System.out.println(temp1.toString().replace(startingDir.toString(), ""));
    }

    public void deleteFile() throws IOException {
        if (!filePath.exists()) {
            throw new FileNotFoundException("The file does not exist");
        }
        if (!filePath.delete()) {
            throw new IOException("Failed to delete the file");
        }
    }

    public void renameFile(String newName) throws IOException {
        String newPath = filePath.getParent() + File.separator + newName;
        File newFile = new File(newPath);
        if (newFile.exists()) {
            throw new FileAlreadyExistsException("A file with the new name already exists");
        }
        if (!filePath.renameTo(newFile)) {
            throw new IOException("Failed to rename the file");
        }
        filePath = newFile;
    }
}
