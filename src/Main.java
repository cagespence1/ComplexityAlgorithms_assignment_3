import java.util.ArrayList;

public class Main {

    private static final String NO_PATH = "No paths available from current state";
    private static final String FOUND_FINISH = "Finish state found";

    /**
     * Colors for paths.
     */
    private enum Color {
        BLACK, PURPLE, GREEN, YELLOW, BLUE
    }

    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Path> availablePaths = new ArrayList<>();
    private ArrayList<State> visitedStates = new ArrayList<>();
    private ArrayList<State> allStates = new ArrayList<>();
    private ArrayList<Integer> branchPositions = new ArrayList<>();
    private ArrayList<Integer> branchSizes = new ArrayList<>();

    private State currentState;

    private boolean foundFinish = false;

    public static void main(String[] args) {
        new Main().findPath();
    }

    /**
     * The main method run in the Main() class.
     *
     * All nodes and paths are added to lists.
     *
     * While the finish node isn't the method tries to run the goToNextState() method.
     * Exceptions from that method are caught and dealt with. if the exception contains
     * the NO_PATH String, the currentState is reverted back to the last branch and the
     * next available path is followed. If FINISH_FOUND is caught, the program prints
     * some data and closes.
     */
    private void findPath() {
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
                    System.out.println("\t" + visitedStates.get(branchPositions.get(branchPositions.size() - 1))); // previous to branch position

                    // take the last added available path
                    currentState = visitedStates.get(branchPositions.get(branchPositions.size() - 1));

                    System.out.print("\t"); // the path from the branch to take

                    // follow the last added available path
                    followPath(availablePaths.get(availablePaths.size() - 1));
                    cleanUpAvailablePaths();
                } else if (e.getMessage().equals(FOUND_FINISH)) {
                    foundFinish = true;
                }
            }
        }
        System.out.println("\tFinish node found\n\tFinal state list:");
        System.out.println(visitedStates);
        System.out.println("\tThe final State was found in " + visitedStates.size() + " moves. " + allStates.size() + " states visited");
    }

    /**
     * Called after a path has been followed. Effectively backtracking to a previous branch point. This method removes
     * paths from availablePaths that have been followed, removes all visitedStates between the previous branch
     * point and the end, and updates branch positions and sizes (removing branch positions if there are no more elements
     * to branch to, or decrementing the branch size if there are still more paths available to branch to at the current
     * branch position).
     */
    private void cleanUpAvailablePaths() {
        // remove last path from available paths
        availablePaths.remove(availablePaths.size() - 1);

        // remove visited nodes between
        System.out.println("\t\tremoving " + visitedStates.subList(branchPositions.get(branchPositions.size() - 1), visitedStates.size()));
        visitedStates.removeAll(visitedStates.subList(branchPositions.get(branchPositions.size() - 1) + 1, visitedStates.size()));

        if (branchSizes.get(branchSizes.size() - 1) <= 2) {
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

    /**
     * Finds available Paths to follow and tries to follow them. There are several conditions that
     * can affect what behavior this method displays.
     *
     * (1) If there are no available paths to take from a node, an Exception is thrown that the Main()
     * class will catch and deal with.
     *
     * (2) If there is only one path available for the pawn to move on, it is checked that the result
     * of following it won't be a state that has already been achieved (this ensures that the program
     * will not enter a loop). If the resulting State is not a state that has been visited yet, the
     * pawn follows the path.
     *
     * (3) If there are multiple paths that the pawn could take (aka. a branch position), the pawn
     * takes a path in the same way that the (1st) condition does (avoiding loops), the extra
     * possible paths are added to a list along with the position of the branch and amount of extra
     * paths there are. Storing these allows the program to go back to the branch positions and visit
     * the other paths at a later time.
     *
     * @throws Exception containing either a FOUND_FINISH message, or a NO_PATH exception. these are
     * to be dealt with by the method calling them.
     */
    private void goToNextState() throws Exception {
        // add current state to visited and allStates
        visitedStates.add(currentState);
        if (!allStates.contains(currentState)) {
            allStates.add(currentState);
        }

        System.out.println(currentState); // print current state

        // check if we are at a finish state
        if (currentState.getNode1().getColor().equals(Color.BLUE) || currentState.getNode2().getColor().equals(Color.BLUE)) {
            throw new Exception(FOUND_FINISH);
        }

        // get possible paths
        ArrayList<Path> possiblePaths = new ArrayList<>();
        possiblePaths.addAll(findPaths(currentState));

        // if there are no paths
        if (possiblePaths.size() == 0) {
            System.out.println("\tno paths");

            // throw an exception to be handled by Main()
            throw new Exception(NO_PATH);

            // if we have found a path
        } else if (possiblePaths.size() == 1) {

            // and that path doesn't take us somewhere we've been before
            if (!duplicateState(possiblePaths.get(0))) {

                System.out.println("\t" + possiblePaths.get(0) + " **");

                // follow the path
                followPath(possiblePaths.get(0));

                // if the path does take us down a path we've already been
            } else {

                System.out.println("\t" + possiblePaths.get(0) + " >> duplicate path");

                // take us back to the second most previous branch position
                currentState = visitedStates.get(branchPositions.get(branchPositions.size() - 1));

                // clean up old paths and branches
                cleanUpAvailablePaths();
            }
        } else if (possiblePaths.size() > 1) {
            // add other paths to a list to be visited later
            int pathCount = 0;

            System.out.println("\t" + possiblePaths.get(0) + " **");
            // starting from position 1, see if the path ends up in a state we've already been to or not. add the path accordingly.
            for (int i = 1; i < possiblePaths.size(); i++) {

                // if the result of the path doesn't land in a state that's been achieved before
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
                followPath(possiblePaths.get(0));
            } else {

                // we cant follow the duplicate path so we have to follow the previous path in available paths
                followPath(availablePaths.get(availablePaths.size() - 1));
            }
        }
    }

    /**
     * Finds whether following a given path from the current State will result in a
     * State that's already been achieved.
     * @param path the path to test.
     * @return Whether the path has been followed or not
     */
    private boolean duplicateState(Path path) {
        // store a temporary state
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

    /**
     * Follows a given path from the currentState and updates the currentState's position
     * @param path for the currentState to follow
     */
    private void followPath(Path path){
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

    /**
     * Finda all valid Paths from a given State.
     * @param state the state from which to search for valid paths.
     * @return a list of valid Paths.
     */
    private ArrayList<Path> findPaths(State state) {
        ArrayList<Path> validPaths = new ArrayList<>();

        Node node1 = state.getNode1();
        Node node2 = state.getNode2();

        // add paths available to node1
        for (Path path : paths) {
            if (validPath(path, node1, node2)) {
                if (!validPaths.contains(path)) {
                    validPaths.add(path);
                }
            }
        }

        // add paths available to node2
        for (Path path : paths) {
            if (validPath(path, node2, node1)) {
                if (!validPaths.contains(path)) {
                    validPaths.add(path);
                }
            }
        }

        return validPaths;
    }

    /**
     * Finds if a given path is valid from a node.
     *
     * A valid path is one that starts from node1 and has the same color as node2.
     *
     * @param path the path that is checked
     * @param node1 the node that the path will be starting from
     * @param node2 the other node
     * @return whether it is valid to move along the given path from node1
     */
    private boolean validPath(Path path, Node node1, Node node2){
        return path.getFrom().getId() == node1.getId() && node2.getColor().equals(path.getColor());
    }

}
