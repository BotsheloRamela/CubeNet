package org.example.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

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

    @Override
    public void run() {
        try {
            out.writeInt(numPlayers);
            out.writeUTF("Welcome, player #" + playerId + "!");
            out.writeDouble(playerPositions[playerId-1][0]);
            out.writeDouble(playerPositions[playerId-1][1]);
            out.flush();

            while (numPlayers < maxPlayers) {
                Thread.sleep(1000);
            }

            while (true) {
                int otherPlayerId = (playerId == 1) ? 2 : 1;
                double otherPlayerX = playerPositions[otherPlayerId-1][0];
                double otherPlayerY = playerPositions[otherPlayerId-1][1];
                out.writeDouble(otherPlayerX);
                out.writeDouble(otherPlayerY);
                out.flush();

                double myPlayerX = in.readDouble();
                double myPlayerY = in.readDouble();
                playerPositions[playerId-1][0] = myPlayerX;
                playerPositions[playerId-1][1] = myPlayerY;
            }
        } catch (IOException e) {
            System.out.println("IOException from ClientHandler run()");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException from ClientHandler run()");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("IOException from ClientHandler finally block");
            }
        }
    }
}
