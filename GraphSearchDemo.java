
public class GraphSearchDemo {
    
    public static void main(String[] args) {

        // create graph
        Graph g = new Graph(8);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(2, 6);
        g.addEdge(2, 7);
        g.addEdge(3, 1);
        g.addEdge(3, 4);
        g.addEdge(4, 1);
        g.addEdge(4, 2);
        g.addEdge(4, 3);
        g.addEdge(4, 5);
        g.addEdge(5, 4);
        g.addEdge(6, 2);
        g.addEdge(6, 7);
        g.addEdge(7, 2);
        g.addEdge(7, 6);
        
        System.out.println("Graph:");
        g.print();
        System.out.println();
        
        // demo breadth first search
        System.out.println("Breadth first traversal:");
        g.BFS(0);
        System.out.println();
        
        // demo depth first search
        System.out.println("Depth first traversal:");
        g.DFS(0);
        System.out.println();
        
    }

}
