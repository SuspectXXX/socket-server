import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer {

    public static void main(String[] args) {

        int port = 7000;
        ServerSocket serverSocket = null;
        Socket socket = null;
        boolean isRunning = true;

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while(isRunning) {
                String[] strs = dataInputStream.readUTF().split("--");
                dataOutputStream.writeUTF(String.format("让我们欢迎来自%s的%s", strs[1], strs[0]));
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
