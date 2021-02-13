import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    // array of lists represents an adjacency list
    private LinkedList<Integer>[] adj;
    
    private int numberOfVertices;
    
    @SuppressWarnings("unchecked")
    public Graph(int size) {
        adj = (LinkedList<Integer>[]) new LinkedList[size];
        numberOfVertices = size;
        for (int i = 0; i < numberOfVertices; i++)
            adj[i] = new LinkedList<>();
    }
    
    public void addEdge(int u, int v) {
        adj[u].add(v);
    }
    
    public void print() {
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.println("Edges from vertex " + i + ":");
            for (Integer v : adj[i])
                System.out.println("(" + i + ", " + v + ")");
        }
    }
    
    public void BFS(int s) {
        // keep list of discovered vertices
        boolean[] discovered = new boolean[numberOfVertices];
        
        // queue to implement BFS
        Queue<Integer> q = new LinkedList<>();
        
        discovered[s] = true;
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (Integer v : adj[u])
                if (!discovered[v]) {
                    discovered[v] = true;
                    q.add(v);
                }
        }
        System.out.println();
    }
    
    public void DFS(int s) {
        // keep list of explored vertices
        boolean[] explored = new boolean[numberOfVertices];
        
        // stack to implement DFS
        Stack<Integer> stack = new Stack<>();
        
        stack.push(s);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (!explored[u]) {
                explored[u] = true;
                System.out.print(u + " ");
                for (Integer v : adj[u]) {
                    stack.push(v);
                }
            }
        }
        System.out.println();
    }
}
