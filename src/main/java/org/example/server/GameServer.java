/**

 The GameServer class represents a server that coordinates communication between two clients playing a game.
 The server listens for incoming connections, accepts two clients, and starts two threads to handle communication
 between the two clients. The server constantly updates the positions of the players and sends the updated positions
 to the clients.
 */

package org.example.server;
import org.example.client.ClientHandler;
import org.example.game.GameConfig;

import java.io.*;
import java.net.*;

public class GameServer {
    private int numPlayers = 0, maxPlayers = 2;
    private final int PORT = GameConfig.PORT;

    /**
     * Constructs a new GameServer object and creates a ServerSocket to listen for incoming connections.
     */
    public GameServer() {
        System.out.println("===== GAME SERVER =====");
        try {
            ServerSocketHandler.startServerSocket();
            System.out.println("Started server on IP Address: " + GameConfig.SERVER_IP_ADDRESS + " | PORT: " + PORT);
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
                ServerSocketHandler.acceptSocketConnections();
                numPlayers++;
                Socket socket = ServerSocketHandler.getSocket();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                ClientHandler handler = new ClientHandler(numPlayers, socket, in, out);
                handler.start();

                out.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");

                if (numPlayers == GameConfig.MAX_PLAYERS) {
                    System.out.println("Starting game!");
                }
            }
            System.out.println("No longer accepting connections");
        } catch (IOException e) {
            System.out.println("IOException from acceptConnection() in GameServer");
        }
    }


    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
    }
}
