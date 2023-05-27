/**
 * The PlayerManager class is responsible for managing player positions, angles, usernames, health, and ammo.
 * It provides methods for updating and retrieving various attributes of players.
 */

package org.example.cubenet.player;

public class PlayerManager {

    private static class Player {
        private int id;
        private Position position;
        private double angle;
        private String username;
        private int health;
        private int ammo;

        public Player(int id) {
            this.id = id;
            this.health = 100;
            this.ammo = 30;
        }

        public int getId() {
            return id;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        public String getUsername() {return username;}

        public void setUsername(String username) {
            this.username = username;
        }

        public void setHealth(int health) {this.health = health;}

        public int getHealth() {
            return health;
        }

        public void setAmmo(int ammo) {this.ammo = ammo;}

        public int getAmmo() {
            return ammo;
        }
    }
}
