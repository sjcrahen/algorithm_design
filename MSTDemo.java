import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MSTDemo {

    public static Node[] node; // array of nodes; supports O(1) access to Node objects
    public static PriorityQueue<Node> pq; // priority queue of all nodes; supports O(log n) insert() and extractMin() 
    public static List<List<Edge>> adj; // adjacency list representation of the graph
    public static List<List<Edge>> MST; // graph to hold MST
    public static boolean pathExists = true; // flag non-existent paths
    
    public static void main(String[] args) {
        
        int numberOfNodes = 8;

        // initiate data structures
        node = new Node[numberOfNodes];
        pq = new PriorityQueue<>(numberOfNodes);
        adj = new ArrayList<List<Edge>>();
        MST = new ArrayList<List<Edge>>();
        
        for (int i = 0; i < numberOfNodes; i++) {
            node[i] = new Node(i);
            pq.insert(node[i]);
            adj.add(new LinkedList<Edge>());
            MST.add(new LinkedList<Edge>());
        }
        
        // add edges to adjacency list
        adj.get(0).add(new Edge(node[1], 9));
        adj.get(0).add(new Edge(node[5], 14));
        adj.get(0).add(new Edge(node[6], 15));
        adj.get(1).add(new Edge(node[2], 24));
        adj.get(2).add(new Edge(node[4], 2));
        adj.get(2).add(new Edge(node[7], 19));
        adj.get(3).add(new Edge(node[2], 6));
        adj.get(3).add(new Edge(node[7], 6));
        adj.get(4).add(new Edge(node[3], 11));
        adj.get(4).add(new Edge(node[7], 16));
        adj.get(5).add(new Edge(node[2], 18));
        adj.get(5).add(new Edge(node[4], 30));
        adj.get(5).add(new Edge(node[6], 5));
        adj.get(6).add(new Edge(node[4], 20));
        adj.get(6).add(new Edge(node[7], 44));
        
        /////////////////////////////////////////////////////////////
        //      
        // implement Prim's algorithm
        //
        /////////////////////////////////////////////////////////////
        
        int startNodeNumber = 0;
        
        Node s = node[startNodeNumber];
        s.setKey(0);
        pq.heapify();

        while (!pq.isEmpty()) {
            Node u = pq.extractMin();
            if (u.getParent() != null)
                MST.get(u.getParent().getNodeNumber()).add(new Edge(u, u.getKey()));

            List<Edge> edges = adj.get(u.getNodeNumber());

            // explore and update keys of adjacent nodes not already explored
            for (Edge e : edges) {
                Node v = e.getInNode();
                changeKey(u, v, e); // update distance
            }
            pq.heapify(); // reheap after changeKey()
        }
        
        /////////////////////////////////////////////////////////////
        //
        // end algorithm
        //
        ////////////////////////////////////////////////////////////

        // print MST
        for (int i = 0; i < numberOfNodes; i++) {
            for (Edge e : MST.get(i))
                System.out.println("(" + i + ", " + e.getInNode().getNodeNumber() + ")");
        }
    }
    
    private static void changeKey(Node outNode, Node inNode, Edge e) {
        int currentKey = inNode.getKey();
        int updatedDistance = outNode.getKey() + e.getCost();
        if (updatedDistance < currentKey) {
            inNode.setKey(updatedDistance);
            inNode.setParent(outNode);
        }
    }
    
    static class Node implements Comparable<Node> {
        
        private int nodeNumber;
        private int key; // distance to this node from origin 
        private Node shortestPathParent; // parent on the shortest path from origin
        
        public Node() {}
        public Node(int node) {
            this.nodeNumber = node;
            key = Integer.MAX_VALUE; // initialize each node with largest possible cost
            shortestPathParent = null; // parent initially unknown 
        }
        
        public int getNodeNumber() {
            return nodeNumber;
        }
        
        public int getKey() {
            return key;
        }

        public void setKey(int newKey) {
            key = newKey;
        }
        
        public Node getParent() {
            return shortestPathParent;
        }

        public void setParent(Node newParent) {
            shortestPathParent = newParent;
        }
        
        @Override
        public int compareTo(Node n) {
            if (this.key < n.key)
                return -1;
            else if (this.key == n.key)
                return 0;
            else
                return 1;
        }
    }
    
    static class Edge {
        
        private Node inNode; // inNode is v in edge (u, v)
        private int cost; // cost of path from node u to node v
        
        public Edge() {}
        public Edge(Node n, int c) {
            inNode = n;
            cost = c;
        }
        
        public Node getInNode() {
            return inNode;
        }

        public int getCost() {
            return cost;
        }
    }

}
