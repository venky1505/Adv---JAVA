import java.io.*;
import java.net.*;
public class MyClient {
    public static void main(String[] args){
        try{
            Socket s = new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            System.out.println(s.getLocalAddress());
            System.out.println(s.getInetAddress());
            System.out.println(s.getPort());
            System.out.println(s.getLocalPort());
            System.out.println(s.getLocalSocketAddress());
            dout.writeUTF("bye Server");
            dout.flush();
            dout.close();
            s.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}