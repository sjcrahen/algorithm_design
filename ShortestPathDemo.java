import java.util.*;

public class ShortestPathDemo {

    public static Node[] node; // array of nodes; supports O(1) access to Node objects
    public static Set<Node> S; // set of explored nodes; supports O(1) add() and contains()
    public static PriorityQueue<Node> pq; // priority queue of all nodes; supports O(log n) insert() and extractMin() 
    public static List<List<Edge>> adj; // adjacency list representation of the graph
    public static boolean pathExists = true; // flag non-existent paths
    
    public static void main(String[] args) {
        
        int numberOfNodes = 8;

        // initiate data structures
        node = new Node[numberOfNodes];
        S = new HashSet<Node>();
        pq = new PriorityQueue<>(numberOfNodes);
        adj = new ArrayList<List<Edge>>();
        
        for (int i = 0; i < numberOfNodes; i++) {
            node[i] = new Node(i);
            pq.insert(node[i]);
            adj.add(new LinkedList<Edge>());
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
        // implement Dijkstra's algorithm
        //
        /////////////////////////////////////////////////////////////
        
        int startNodeNumber = 0;
        int endNodeNumber = 7;
        
        // initially S = {s} and s.key = 0
        pq.delete(startNodeNumber);
        Node s = node[startNodeNumber];
        S.add(s);
        s.setKey(0);

        Node u = s, v;
        while (S.size() < numberOfNodes) {
            List<Edge> edges = adj.get(u.getNodeNumber());

            // explore and update keys of adjacent nodes not already explored
            for (Edge e : edges) {
                v = e.getInNode();
                if (S.contains(v)) // Node v already explored
                    continue;
                changeKey(u, v, e); // update distance
            }
            pq.heapify(); // reheap after changeKey()
            
            // add v with lowest key to S; v is at index 0 of pq
            v = pq.extractMin();
            S.add(v); // add next node to set S
            u = v;
        }
        
        /////////////////////////////////////////////////////////////
        //
        // end algorithm
        //
        ////////////////////////////////////////////////////////////

        // assemble list of Nodes on shortest path
        Node startNode = node[startNodeNumber];
        Node endNode = node[endNodeNumber];
        Stack<Node> shortestPath = new Stack<>();
        shortestPath.push(endNode);

        Node current = endNode, parent;
        while (current.getParent() != null) {
            parent = current.getParent();
            shortestPath.push(parent);
            current = parent;
        }
        if (current != startNode) // there was no path from startNode to endNode
            pathExists = false;

        // display distances of shortest path from starting node to each node
        for (int i = 0; i < numberOfNodes; i++) {
            if (Math.abs(node[i].getKey()) < 2000000000) // a path exists
                System.out.println("shortest path from node " + startNodeNumber
                        + " to node " + i + " is length " + node[i].getKey() + " ");
            else
                System.out.println("no path exists from node " + startNodeNumber
                        + " to node " + i);
        }
        
        // display shortest path from startNode to endNode if path exists
        if (pathExists) {
            System.out.println("\nshortest path from node " + startNodeNumber
                    + " to node " + endNodeNumber + " is:");
            System.out.print("[ ");
            while (!shortestPath.isEmpty())
                System.out.print(shortestPath.pop().getNodeNumber() + " ");
            System.out.println("]");
        } else
            System.out.println("\nno path exists from node " + startNodeNumber
                    + " to node " + endNodeNumber);
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
