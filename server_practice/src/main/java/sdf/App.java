package sdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String dirPath = "data";
        String fileName = "cookie.txt";
        List<String> cookieList = new ArrayList<>();
        
        ServerSocket server = new ServerSocket(3000);
        System.out.println("pending connection...");
        Socket s = server.accept();
        System.out.println("connection has been made");

        FileHandling fh = new FileHandling();
        fh.checkFile(dirPath, fileName);
        cookieList = fh.readCookie(dirPath, fileName);
        // fh.pickRandomCookie(cookieList);
        

        CookieClientHandler cch = new CookieClientHandler(s,cookieList);
        cch.run();

        // try (InputStream is = s.getInputStream()) {
        //     BufferedInputStream bis = new BufferedInputStream(is);
        //     DataInputStream dis = new DataInputStream(bis);
        //     String msgReceived = "";

        //     try (OutputStream os = s.getOutputStream()) {
        //         BufferedOutputStream bos = new BufferedOutputStream(os);
        //         DataOutputStream dos = new DataOutputStream(bos);

        //         while (!msgReceived.equals("close")) {
        //             msgReceived = dis.readUTF();

        //             if (msgReceived.equalsIgnoreCase("get-cookie")) {
        //                 String cookieValue = FileHandling.pickRandomCookie(cookieList);
        //                 System.out.println(cookieValue);

        //                 dos.writeUTF(cookieValue);
        //                 dos.flush();
        //             }
        //         }

        //         dos.close();
        //         bos.close();
        //         os.close();
        //     } catch (EOFException ex) {
        //         ex.printStackTrace();
        //     }

        //     bis.close();
        //     dis.close();
        //     is.close();
        // } catch (EOFException ex) {
        //     s.close();
        //     server.close();
        // }
        
        server.close();
    }
}
