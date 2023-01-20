package sdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CookieClientHandler implements Runnable {

    private List<String> cookieList = new ArrayList<>();

    FileHandling fh = new FileHandling();

    private final Socket socket;

    public CookieClientHandler(Socket socket,List<String> cookieList) {
        this.socket = socket;
        this.cookieList = cookieList;
    }

    @Override
    public void run() {

        try (InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String msgReceived = "";

            try (OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                while (!msgReceived.equals("close")) {
                    msgReceived = dis.readUTF();

                    if (msgReceived.equalsIgnoreCase("get-cookie")) {
                        String cookieValue = fh.pickRandomCookie(cookieList);
                        System.out.println(cookieValue);

                        dos.writeUTF(cookieValue);
                        dos.flush();
                    }
                }

                dos.close();
                bos.close();
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            bis.close();
            dis.close();
            is.close();
        } catch (IOException ex) {
            
        } try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
    
}
