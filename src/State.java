public class State {
    private Node node1;
    private Node node2;

    public State(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public String toString(){
        return "(" + node1.getId() + ", " + node2.getId() + ")";
    }

    @Override
    public boolean equals(Object o) {
        State state = (State) o;
        if (state.getNode1().equals(this.node1) && state.getNode2().equals(this.node2)){
            return true;
        } else if (state.getNode1().equals(this.node2) && state.getNode2().equals(this.node1)){
            return true;
        }
        return false;
    }
}
