import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Mainn {

    public static void main(String[] args) {

        try (ServerSocket s = new ServerSocket(8198)){
            int i = 1;
            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning "+ i);
                Runnable r = new ThreadedHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

