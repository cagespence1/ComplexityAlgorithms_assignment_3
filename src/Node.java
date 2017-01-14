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

    public String toString(){
        return "node " + id + " : " + color;
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node)o;
        return (node.getColor().equals(this.color) && node.getId() == this.getId());
    }
}
