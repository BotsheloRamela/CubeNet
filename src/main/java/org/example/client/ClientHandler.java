package org.example.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler {

    private int numPlayers = 0, maxPlayers = 2;
    private double[][] playerPositions = {{100, 400}, {490, 400}};
    private int playerId;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(int playerId, Socket socket, DataInputStream in, DataOutputStream out) {
        this.playerId = playerId;
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
}
