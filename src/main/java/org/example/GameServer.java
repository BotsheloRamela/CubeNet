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

    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");
            while (numPlayers < maxPlayers) {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                playerSockets[numPlayers] = socket;
                numPlayers++;

                out.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");

                if (numPlayers == 2) {
                    DataInputStream in1 = new DataInputStream(playerSockets[0].getInputStream());
                    DataOutputStream out1 = new DataOutputStream(playerSockets[0].getOutputStream());
                    DataInputStream in2 = new DataInputStream(playerSockets[1].getInputStream());
                    DataOutputStream out2 = new DataOutputStream(playerSockets[1].getOutputStream());
                }
            }
            System.out.println("No longer accepting connections");
        } catch (IOException e) {
            System.out.println("IOException from acceptConnection() in GameServer");
        }
    }

    private void readFromClient(DataInputStream in, int playerID) {
        try {
            while (true) {
                playerPositions[playerID][0] = in.readDouble();
                playerPositions[playerID][1] = in.readDouble();
            }
        } catch (IOException e) {
            System.out.println("IOException from RFC run() in GameServer");
        }
    }

    private void writeToClient(DataOutputStream out, int playerID) {
        try {
            out.writeUTF("We now have 2 players. GO!");
            while (true) {
                int otherPlayerID = (playerID + 1) % 2;
                out.writeDouble(playerPositions[otherPlayerID][0]);
                out.writeDouble(playerPositions[otherPlayerID][1]);
                out.flush(); // Ensures that the data is sent immediately.
                Thread.sleep(25); // Prevents the loop from running too quickly and using up too much CPU time.
            }
        } catch (IOException e) {
            System.out.println("IOException from WTC run() in GameServer");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException from WTC run() in GameServer");
        }
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
    }
}
