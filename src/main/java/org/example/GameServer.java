package org.example;
import java.io.*;
import java.net.*;

public class GameServer {
    private ServerSocket serverSocket;
    private int numPlayers = 0, maxPlayers = 2;
    private Socket[] playerSockets = new Socket[2];
    private double[][] playerPositions = {{100, 400}, {490, 400}};

    public GameServer() {
        System.out.println("===== GAME SERVER =====");
        try {
            serverSocket = new ServerSocket(4545);
        } catch (IOException e) {
            System.out.println("IOException from GameServer");
        }
    }
}
