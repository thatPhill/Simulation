package entities.creature;

import world.Coordinates;

public class Predator extends Creature {
    private int attack;

    public Predator(Coordinates coordinates, String emoji, int speed, int health, int attack) {
        super(coordinates, emoji, speed, health);
        this.attack = 1;
    }


    @Override
    public void makeMove() {

    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
