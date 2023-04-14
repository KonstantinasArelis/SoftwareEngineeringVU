import FileClass.Exe;
import FileClass.Txt;
import FileClass.Png;
import FileClass.MyFile;
public class Main {
    public static void main(String[] args) {
        MyFile anObject = new MyFile("/Users/kostasarelis/Desktop/lit3/text.txt", "rw");
        String msg = "prasau veik";
        byte b[] = {65,66,67};
        
        Png image1 = new Png("/Users/kostasarelis/Desktop/lit3/vu-logo-3.png");//change this when switching to macOS
        Exe exe1 = new Exe("/Users/kostasarelis/Desktop/lit3/run.exe");
        Txt txt1 = new Txt("/Users/kostasarelis/Desktop/lit3/text.txt");
        System.out.println("Height of image: = " + image1.calculateHeight());
        System.out.println("Executable is Executable: = " + exe1.isExecutable());
        txt1.appendFile(msg.getBytes());
        System.out.println("to string: = " + image1.toString());

        //polimorfizmas
        msg = "Jau geriau";
        
        anObject.printLn();
        anObject.appendFile(b);
        anObject = new Txt("/Users/kostasarelis/Desktop/lit3/text.txt");
        anObject.appendFile(msg.getBytes());
    }
}
