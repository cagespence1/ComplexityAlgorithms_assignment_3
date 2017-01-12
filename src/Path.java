public class Path {
    private Enum color;
    private Node to;
    private Node from;

    public Path(Enum color, Node to, Node from) {
        this.color = color;
        this.to = to;
        this.from = from;
    }

    public Enum getColor() {
        return color;
    }

    public Node getTo() {
        return to;
    }

    public Node getFrom() {
        return from;
    }
}
