
public class Main {
    public static void main(String[] args) {
        MyFile file1 = new MyFile("kazkas.dat","rw");
        MyFile file2 = new MyFile("failas.dat","rw");
        byte b[] = {97,98,99};
        if(file1.AppendFile(97)==-1){
            System.out.println("Unsuccesful write to file");
        }
        if(file2.AppendFile(b)==-1){
            System.out.println("Unsuccesful write to file");
        }

        file1.printLn();
        //System.out.println("Usable space(B) = " + file1.UpdateFreeSpace());
        System.out.println("MyFile instances = " + MyFile.myFileInstances());

        file1.setFile(file2.getFile().toString());
    }
}
