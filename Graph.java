import java.util.*;

public class Graph {
    private int [] [] matrix;

    boolean [] visited;

    public Graph(int [] [] matrix) {
        this.matrix = matrix;
        this.visited = new boolean[matrix.length];
    }

    public boolean isVisited(int node) {
        return visited[node];
    }

    public void wasVisited(int node) {
        visited[node] = true;
    }
    public List<Integer> getNeighbors(int node) {
        String answer = "";
        ArrayList<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[node][i] == 1) {
                answer += i;
                answerList.add(i);
            }
        }
        //System.out.println(answer);
        return answerList;
    }

    public int getLowestUnvisitedNeighbor(int node) {
        List<Integer> list = getNeighbors(node);
        //System.out.println("node = " + node + " has neighbors = " + list);
        for (int i = 0; i < list.size(); i++) {
            if (!isVisited(list.get(i))) {
                //System.out.println("lowest unvisited neighbor is " + list.get(i));
                return list.get(i);
            } else {
                //System.out.println("neighbor: " + list.get(i) + " has already been visited.");
            }
        }
        return -1; // all neighbors have been visited
    }

    public boolean hasEdge(int from, int to) {
       //System.out.println(matrix[from][to] == 1);
        return matrix[from][to] == 1;
    }

    public List<Integer> dfs(int startingNode) {
        List<Integer> dfsSequence = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(startingNode);
        while (!stack.isEmpty()) {
            // printVisited();
            int node = stack.peek();
            if (!isVisited(node)) {
                System.out.print(node);
                dfsSequence.add(node);
                wasVisited(node);
            }
            int unvisited = getLowestUnvisitedNeighbor(node);
            if (unvisited == -1) {
                stack.pop();
            } else {
                stack.push(unvisited);
            }
        }
        System.out.println();
        return dfsSequence;
    }

    private void printVisited() {
        System.out.print("visited = ");
        for (int i = 0; i < visited.length; i++) {
            System.out.print(visited[i] + " ");
        }
        System.out.println();
    }

    private static void printMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //int graphCount = Integer.parseInt(scan.nextLine());
        int graphCount = 1;
        //System.out.println("graph count = " + graphCount);
        Graph[] graph = new Graph[graphCount];
        for (int i = 0; i < graphCount; i++) {
            String line = scan.nextLine();
            System.out.println("line = " + line);
            String [] tokens = line.split(" ");
            int nodes = Integer.parseInt(tokens[0]);
            int edges = Integer.parseInt(tokens[1]);
            System.out.println("nodes = " + nodes);
            System.out.println("edges = " + edges);
            int[][] matrix = new int[nodes][nodes];
            for (int j = 0; j < edges; j++) {
                line = scan.nextLine();
                tokens = line.split(" ");
                int from = Integer.parseInt(tokens[0]);
                int to = Integer.parseInt(tokens[1]);
                matrix[from][to] = 1;
                matrix[to][from] = 1;
            }
            //printMatrix(matrix);
            graph[i] = new Graph(matrix);
        }
        /*
        System.out.println("starting adjcency matrix test code");
        graph[0].getNeighbors(1); // should print 0245
        graph[0].getNeighbors(5); // should print 14
        graph[0].hasEdge(3,4); // should print true
        graph[0].hasEdge(4,2); // should print false

        graph[1].getNeighbors(1); // should print 245
        graph[1].getNeighbors(5); // should print 134
        graph[1].hasEdge(3,4); // should print false
        graph[1].hasEdge(4,2); // should print false

        graph[2].getNeighbors(1); // should print 456
        graph[2].getNeighbors(5); // should print 136
        graph[2].hasEdge(3,4); // should print false
        graph[2].hasEdge(4,2); // should print true
         */

        for (int j = 0; j < graph[0].matrix.length; j++) {
            graph[0].dfs(j);
        }
       // graph[0].dfs(0);

    }
}
