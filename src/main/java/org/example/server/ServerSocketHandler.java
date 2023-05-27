package org.example.server;

import org.example.game.GameConfig;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler {
    private ServerSocket serverSocket;
    private final Socket[] playerSockets = new Socket[GameConfig.MAX_PLAYERS];
    private final int PORT = GameConfig.PORT;
}
