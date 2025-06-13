package entities;

import mapetc.Coordinates;

public abstract class Creature extends Entity{
   public Creature(Coordinates coordinates, String emoji) {
      super(coordinates, emoji);
   }

   abstract void makeMove();
}
