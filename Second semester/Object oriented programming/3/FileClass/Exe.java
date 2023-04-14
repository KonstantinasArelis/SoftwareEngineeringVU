package FileClass;
//import MyFile;
public class Exe extends MyFile {
    boolean fileEmpty;

    public Exe(String fileName, String permission) {
        super(fileName, permission);
        fileEmpty = fileName.isEmpty();
    }

    public Exe(String fileName) {
        super(fileName);
        fileEmpty = fileName.isEmpty();
    }

    public Exe() {
        super();
        fileEmpty = true;
    }

    public boolean isExecutable() {
        return getFile().canExecute();
    }

    public String toString(){
        return getFile().toString()+" file is empty:"+fileEmpty;
    }

    void Print()
    {
        // Print statement
        System.out.println("Exe path: " + getFile().toString());
    }
    }
