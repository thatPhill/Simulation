package mapetc;

public class Coordinates {
    private int horizontal;
    private int vertical;

    public Coordinates(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return getHorizontal() == that.getHorizontal() && getVertical() == that.getVertical();
    }

    @Override
    public int hashCode() {
        int result = getHorizontal();
        result = 31 * result + getVertical();
        return result;
    }
}
