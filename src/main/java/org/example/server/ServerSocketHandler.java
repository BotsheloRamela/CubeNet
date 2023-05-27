package org.example.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler {
    private ServerSocket serverSocket;
    private final Socket[] playerSockets = new Socket[2];
    private final int PORT = 4545;
}
