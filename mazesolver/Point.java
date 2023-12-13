package mazesolver;

public record Point(int x, int y) {
    public String toString() {
        return "Point[x=" + this.x + ", y=" + this.y + "]";
    }
}
