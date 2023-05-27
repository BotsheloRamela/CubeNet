package org.example.cubenet.server;

import org.example.cubenet.game.GameConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler {
    private static ServerSocket serverSocket;
    private final Socket[] playerSockets = new Socket[GameConfig.MAX_PLAYERS];
    private static final int PORT = GameConfig.PORT;
    private static Socket socket;

    public static void startServerSocket() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public static void acceptSocketConnections() throws IOException {
        socket = serverSocket.accept();
    }

    public static Socket getSocket() {
        return socket;
    }
}
