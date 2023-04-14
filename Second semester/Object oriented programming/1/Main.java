import java.io.File;

//Konstantinas Arelis 3gr 1 kursas 

public class Main {
public static void main(String[] args){
    String path = args[0];
    String suffix = args[1];
    System.out.println("-------------------------------------------------------------------------------");
    System.out.println(path);
    System.out.println("-------------------------------------------------------------------------------");
    PrintFileTree(new File(path), suffix, new File(path), new File(path));
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
}