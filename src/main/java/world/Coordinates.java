package world;


public record Coordinates(int x, int y) {
    public Coordinates sum(Coordinates direction) {
        return new Coordinates(direction.x() + x, direction.y() + y);
    }
}
