/**

 The GameServer class represents a server that coordinates communication between two clients playing a game.
 The server listens for incoming connections, accepts two clients, and starts two threads to handle communication
 between the two clients. The server constantly updates the positions of the players and sends the updated positions
 to the clients.
 */

package org.example.server;
import java.io.*;
import java.net.*;

public class GameServer {
    private ServerSocket serverSocket; // The ServerSocket object used to listen for incoming connections.
    // The number of players currently connected to the server. | The maximum number of players that can connect to the server.
    private int numPlayers = 0, maxPlayers = 2;
    private Socket[] playerSockets = new Socket[2]; // An array of sockets representing the player connections.
    private double[][] playerPositions = {{100, 400}, {490, 400}}; // An array of arrays representing the positions of each player.

    /**
     * Constructs a new GameServer object and creates a ServerSocket to listen for incoming connections.
     */
    public GameServer() {
        System.out.println("===== GAME SERVER =====");
        try {
            serverSocket = new ServerSocket(4545);
        } catch (IOException e) {
            System.out.println("IOException from GameServer");
        }
    }

    /**
     * Listens for incoming connections and accepts two players.
     * Starts two threads to handle communication between the players.
     */
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

                    new Thread(() -> readFromClient(in1, 0)).start();
                    new Thread(() -> readFromClient(in2, 1)).start();
                    new Thread(() -> writeToClient(out1, 0)).start();
                    new Thread(() -> writeToClient(out2, 1)).start();
                }
            }
            System.out.println("No longer accepting connections");
        } catch (IOException e) {
            System.out.println("IOException from acceptConnection() in GameServer");
        }
    }

    /**
     * Reads the position of a player from the given DataInputStream and updates the corresponding position
     * in the playerPositions array.
     *
     * @param in The DataInputStream to read from.
     * @param playerID The ID of the player whose position is being read.
     */
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

    /**
     * Writes data to a client through a DataOutputStream object.
     * It sends the position of the other player in the game to the client in a continuous loop.
     *
     * @param out The output stream to write to the client.
     * @param  playerID The ID of the player whose position is not being sent.
     */
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
