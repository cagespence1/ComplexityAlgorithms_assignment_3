import java.util.ArrayList;

public class Main {

    private static final String NO_PATH = "No paths available from current state";
    private static final String FOUND_FINISH = "Finish state found";

    public enum Color {
        BLACK, PURPLE, GREEN, YELLOW, BLUE
    }

    ArrayList<Path> paths = new ArrayList<>();
    ArrayList<Path> availablePaths = new ArrayList<>();
    ArrayList<State> visitedStates = new ArrayList<>();
    ArrayList<Integer> branchPositions = new ArrayList<>();
    ArrayList<Integer> branchSizes = new ArrayList<>();
    ArrayList<State> allStates = new ArrayList<>();

    State currentState;

    boolean foundFinish = false;


    public static void main(String[] args) {
        new Main().findPath();
    }

    void findPath() {
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

        paths.add(new Path(Color.PURPLE, node1, node4));
        paths.add(new Path(Color.BLACK, node1, node5));
        paths.add(new Path(Color.GREEN, node2, node6));
        paths.add(new Path(Color.PURPLE, node2, node12));
        paths.add(new Path(Color.YELLOW, node3, node1));
        paths.add(new Path(Color.YELLOW, node3, node4));
        paths.add(new Path(Color.BLACK, node4, node13));
        paths.add(new Path(Color.YELLOW, node5, node9));
        paths.add(new Path(Color.GREEN, node6, node9));
        paths.add(new Path(Color.PURPLE, node6, node10));
        paths.add(new Path(Color.GREEN, node7, node2));
        paths.add(new Path(Color.PURPLE, node8, node3));
        paths.add(new Path(Color.GREEN, node9, node4));
        paths.add(new Path(Color.BLACK, node9, node14));
        paths.add(new Path(Color.GREEN, node10, node15));
        paths.add(new Path(Color.PURPLE, node11, node10));
        paths.add(new Path(Color.GREEN, node11, node12));
        paths.add(new Path(Color.GREEN, node12, node7));
        paths.add(new Path(Color.GREEN, node13, node18));
        paths.add(new Path(Color.GREEN, node13, node8));
        paths.add(new Path(Color.YELLOW, node14, node20));
        paths.add(new Path(Color.GREEN, node14, nodeFINISH));
        paths.add(new Path(Color.PURPLE, node15, nodeFINISH));
        paths.add(new Path(Color.GREEN, node15, node22));
        paths.add(new Path(Color.GREEN, node16, node15));
        paths.add(new Path(Color.BLACK, node17, node16));
        paths.add(new Path(Color.BLACK, node17, node11));
        paths.add(new Path(Color.PURPLE, node17, node12));
        paths.add(new Path(Color.YELLOW, node18, node9));
        paths.add(new Path(Color.YELLOW, node18, node20));
        paths.add(new Path(Color.GREEN, node19, node18));
        paths.add(new Path(Color.BLACK, node20, node19));
        paths.add(new Path(Color.YELLOW, node20, node21));
        paths.add(new Path(Color.BLACK, node21, nodeFINISH));
        paths.add(new Path(Color.YELLOW, node21, node22));
        paths.add(new Path(Color.YELLOW, node22, node17));

        // set up first state
        currentState = new State(node1, node2);

        // main loop
        while (!foundFinish) {
            try {
                goToNextState();
            } catch (Exception e) {
                if (e.getMessage().equals(NO_PATH)) {

                    System.out.println("\t" + visitedStates + " visited states");
                    System.out.println("\t" + branchPositions + " branch positions");
                    System.out.println("\t" + branchSizes + " branch sizes");

                    System.out.println("\t" + visitedStates.get(branchPositions.get(branchPositions.size() - 1))); // previous to branch position

                    // take the last added available path
                    currentState = visitedStates.get(branchPositions.get(branchPositions.size() - 1));

                    System.out.print("\t"); // the path from the branch to take

                    followPath(availablePaths, availablePaths.size() - 1);
                    cleanUpAvailablePaths();

                } else if (e.getMessage().equals(FOUND_FINISH)) {
                    foundFinish = true;
                }
            }
        }
        System.out.println(visitedStates);
        System.out.println(visitedStates.size());
        System.out.println("found exit");
    }

    private void cleanUpAvailablePaths() {
        // remove last path from available paths
        availablePaths.remove(availablePaths.size() - 1);

        System.out.println("\t\tremoving " + visitedStates.subList(branchPositions.get(branchPositions.size() - 1), visitedStates.size()));
        visitedStates.removeAll(visitedStates.subList(branchPositions.get(branchPositions.size() - 1)+1, visitedStates.size()));

        if (branchSizes.get(branchSizes.size() - 1) <= 2) {
            // remove visited nodes between

//             System.out.println("\t\tremoving " + visitedStates.subList(branchPositions.get(branchPositions.size() - 1), visitedStates.size()));
//            visitedStates.removeAll(visitedStates.subList(branchPositions.get(branchPositions.size() - 1)+1, visitedStates.size()));

            // remove branch position and branch size from list
            branchPositions.remove(branchPositions.size() - 1);
            branchSizes.remove(branchSizes.size() - 1);
        } else {

            // subtract 1 from the last index of the branchSizes
            int branchSize = branchSizes.get(branchSizes.size() - 1);
            branchSize--;
            branchSizes.set(branchSizes.size() - 1, branchSize);
        }
    }

    private void goToNextState() throws Exception {
        // add current state to visited and allStates
        visitedStates.add(currentState);
        allStates.add(currentState);

        System.out.println(currentState); // print current state

        // check if we are at a finish state
        if (currentState.getNode1().getColor().equals(Color.BLUE) || currentState.getNode2().getColor().equals(Color.BLUE)) {
            throw new Exception(FOUND_FINISH);
        }

        // get possible paths
        ArrayList<Path> possiblePaths = new ArrayList<>();
        possiblePaths.addAll(findPaths(currentState));

        if (possiblePaths.size() == 0) {
            System.out.println("\tno paths");
            throw new Exception(NO_PATH);

            // if we have found a path
        } else if (possiblePaths.size() == 1) {

            // and that path doesnt take us somewhere we've been before
            if (!duplicateState(possiblePaths.get(0))) {

                // follow the path
                followPath(possiblePaths, 0);

                // if it DOES take us down a path we've already been
            } else {

                System.out.print("\t" + possiblePaths.get(0));
                System.out.println(" >> duplicate path");
                // todo set the currentstate back to the previous branch

                System.out.println("\t" + visitedStates + " visited states");
                System.out.println("\t" + branchPositions + " branch positions");
                System.out.println("\t" + branchSizes + " branch sizes");
                System.out.println("\t" + visitedStates.get(branchPositions.get(branchPositions.size() - 1))); // previous to branch position

                // take us back to the second most previous branch position
                currentState = visitedStates.get(branchPositions.get(branchPositions.size() - 1));

                //
                cleanUpAvailablePaths();
            }
        } else if (possiblePaths.size() > 1) {

            // todo only add a path if the result of the path is not a state that has already been achieved
            // add other paths to a list to be visited later
            int pathCount = 0;

            // starting from position 1, see if the path ends up in a state we've already been to or not. add the path accordingly.
            for (int i = 1; i < possiblePaths.size(); i++) {
                if (!duplicateState(possiblePaths.get(i))) {
                    pathCount++;

                    // add the path to possibly paths
                    availablePaths.add(possiblePaths.get(i));
                    System.out.println("\t" + possiblePaths.get(i));
                }
            }

            // store position in visitedStates that we branched from so we can go back to it
            if (pathCount > 0) {
                branchPositions.add(visitedStates.size() - 1);
                branchSizes.add(possiblePaths.size());
            }

            // if we haven't been to the first available state, follow the path to it
            if (!duplicateState(possiblePaths.get(0))) {
                followPath(possiblePaths, 0);
            } else {
                // todo we cant follow the duplicate path so we have to
                System.out.println("follow path");
                followPath(availablePaths, availablePaths.size() - 1);
            }
        }
    }

    boolean duplicateState(Path path) {
        // todo if the path we want to go on leads to a state we've already been to, skip that path and move to the previous one in line
        State tempState;
        // if we are moving from the first node
        if (path.getFrom().getId() == currentState.getNode1().getId()) {
            tempState = new State(path.getTo(), currentState.getNode2());
        } else {
            // if we are moving from the second node
            tempState = new State(currentState.getNode1(), path.getTo());
        }
        return allStates.contains(tempState);
    }

    private void followPath(ArrayList<Path> paths, int i) {
        System.out.println("\t" + paths.get(i) + "**"); // print the path we will be taking

        Path path = paths.get(i);
        State tempState;
        // if we are moving from the first node
        if (path.getFrom().getId() == currentState.getNode1().getId()) {
            tempState = new State(path.getTo(), currentState.getNode2());
        } else {
            // if we are moving from the second node
            tempState = new State(currentState.getNode1(), path.getTo());
        }
        currentState = tempState;
    }

    ArrayList<Path> findPaths(State state) {
        ArrayList<Path> possiblePaths = new ArrayList<>();

        Node node1 = state.getNode1();
        Node node2 = state.getNode2();

        // add paths available to node1
        for (Path path : paths) {
            if (path.getFrom().getId() == node1.getId() && node2.getColor().equals(path.getColor())) {
                if (!possiblePaths.contains(path)) {
                    possiblePaths.add(path);
                }
            }
        }

        // add paths available to node2
        for (Path path : paths) {
            if (path.getFrom().getId() == node2.getId() && node1.getColor().equals(path.getColor())) {
                if (!possiblePaths.contains(path)) {
                    possiblePaths.add(path);
                }
            }
        }

        return possiblePaths;
    }

}
