public class Node {
    private Enum color;
    private int id;

    public Node(Enum color, int id) {
        this.color = color;
        this.id = id;
    }

    public Enum getColor() {
        return color;
    }

    public int getId() {
        return id;
    }
}
