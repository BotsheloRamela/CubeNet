package org.example.cubenet.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommunicationsHandler {
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void startServerCommunications(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    public static DataInputStream getInputStream() {return in;}
    public static DataOutputStream getOutputStream() {return out;}
}
