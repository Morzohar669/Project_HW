import java.io.*;

public class kaki {
    public static void main(String[] args) throws IOException {
        try {
            File obj = new File("kaki.txt");
            FileOutputStream fOut = new FileOutputStream(obj);
            DataOutputStream dout = new DataOutputStream(fOut);
            dout.writeInt(2);
            dout.writeInt(3);
            dout.writeUTF("MAOR");
            fOut.close();
            dout.close();
            FileInputStream fin = new FileInputStream(obj);
            DataInputStream din = new DataInputStream(fin);
            int data = din.readInt();
            int data2 = din.readInt();
            String s = din.readUTF();
            System.out.print(data);
            System.out.print(data2);
            System.out.println(s);
            fin.close();
            din.close();

            char yuvi = 'O';

        } catch (Exception e) {
            System.out.println("MOR HACHAMOR");
        }
    }

}

