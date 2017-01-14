public class Path {
    private Enum color;
    private Node to;
    private Node from;

    public Path(Enum color, Node from, Node to) {
        this.color = color;
        this.from = from;
        this.to = to;
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

    public String toString() {
        return "(" + from.getId() + " -> " + to.getId() + ") : " + color;
    }

    @Override
    public boolean equals(Object o) {
        Path path = (Path) o;
        return (path.getColor().equals(this.getColor()) && path.getFrom().equals(this.from) && path.getTo().equals(this.to));
    }
}
