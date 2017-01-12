import java.util.ArrayList;

public class Main {

    public enum Color {
        BLACK, PURPLE, GREEN, YELLOW, BLUE
    }

    ArrayList<State> availableStates;
    ArrayList<State> visitedStates;

    public static void main(String[] args) {
        Node node1 = new Node(Color.PURPLE, 1);
        Node node2 = new Node(Color.BLACK, 2);
        Node node3 = new Node(Color.GREEN, 3);
        Node node4 = new Node(Color.GREEN, 4);
        Node node5 = new Node(Color.GREEN, 5);
        Node node6 = new Node(Color.YELLOW, 6);
        Node node7 = new Node(Color.YELLOW, 7);
        Node node8 = new Node(Color.PURPLE, 8);
        Node node9 = new Node(Color.PURPLE, 9);
        Node node10 = new Node(Color.BLACK, 10);
        Node node11 = new Node(Color.YELLOW, 11);
        Node node12 = new Node(Color.PURPLE, 12);
        Node node13 = new Node(Color.YELLOW, 13);
        Node node14 = new Node(Color.GREEN, 14);
        Node node15 = new Node(Color.YELLOW, 15);
        Node node16 = new Node(Color.GREEN, 16);
        Node node17 = new Node(Color.GREEN, 17);
        Node node18 = new Node(Color.BLACK, 18);
        Node node19 = new Node(Color.YELLOW, 19);
        Node node20 = new Node(Color.GREEN, 20);
        Node node21 = new Node(Color.BLACK, 21);
        Node node22 = new Node(Color.BLACK, 22);
        Node nodeFINISH = new Node(Color.BLUE, 0);

        Path path1 = new Path(Color.PURPLE, node1, node4);
        Path path2 = new Path(Color.BLACK, node1, node5);
        Path path3 = new Path(Color.GREEN, node2, node6);
        Path path4 = new Path(Color.PURPLE, node2, node12);
        Path path5 = new Path(Color.YELLOW, node3, node1);
        Path path6 = new Path(Color.YELLOW, node3, node4);
        Path path7 = new Path(Color.BLACK, node4, node13);
        Path path8 = new Path(Color.YELLOW, node5, node9);
        Path path9 = new Path(Color.GREEN, node6, node9);
        Path path10 = new Path(Color.PURPLE, node6, node10);
        Path path11 = new Path(Color.GREEN, node7, node2);
        Path path12 = new Path(Color.PURPLE, node8, node3);
        Path path13 = new Path(Color.GREEN, node9, node4);
        Path path14 = new Path(Color.GREEN, node9, node14);
        Path path15 = new Path(Color.GREEN, node10, node15);
        Path path16 = new Path(Color.PURPLE, node11, node10);
        Path path17 = new Path(Color.GREEN, node11, node12);
        Path path18 = new Path(Color.GREEN, node12, node7);
        Path path19 = new Path(Color.GREEN, node13, node18);
        Path path20 = new Path(Color.GREEN, node13, node8);
        Path path21 = new Path(Color.YELLOW, node14, node20);
        Path path22 = new Path(Color.GREEN, node14, nodeFINISH);
        Path path23 = new Path(Color.PURPLE, node15, nodeFINISH);
        Path path24 = new Path(Color.GREEN, node15, node22);
        Path path25 = new Path(Color.GREEN, node16, node15);
        Path path26 = new Path(Color.BLACK, node17, node16);
        Path path27 = new Path(Color.BLACK, node17, node11);
        Path path28 = new Path(Color.PURPLE, node17, node12);
        Path path29 = new Path(Color.YELLOW, node18, node9);
        Path path30 = new Path(Color.YELLOW, node18, node20);
        Path path31 = new Path(Color.GREEN, node19, node18);
        Path path32 = new Path(Color.BLACK, node20, node19);
        Path path33 = new Path(Color.YELLOW, node20, node21);
        Path path34 = new Path(Color.BLACK, node21, nodeFINISH);
        Path path35 = new Path(Color.YELLOW, node21, node22);
        Path path36 = new Path(Color.YELLOW, node22, node17);


    }



}
