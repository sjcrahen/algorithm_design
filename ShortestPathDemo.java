import java.util.*;

public class ShortestPathDemo {

    public static Node[] node; // array of nodes
    public static Set<Node> S; // set of explored nodes
    public static PriorityQueue<Node> pq; // priority queue of all nodes; supports O(log n) extractMin() operation
    public static List<List<Edge>> adj; // adjacency list representation of the graph
    
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
        
        // add edges to adj
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
        
//        for (int i = 0; i < numberOfNodes; i++) {
//            System.out.println("edges from node " + i);
//            for (Edge e : adj.get(i))
//                System.out.print(e.inNode + " ");
//            System.out.println();
//        }
        
        /////////////////////////////////////////////////////////////
        //      
        // implement Dijkstra's algorithm
        //
        /////////////////////////////////////////////////////////////
        
        // initially S = {s} and s.key = 0
        Node s = pq.extractMin();
        S.add(s);
        s.setKey(0);

        Node u = s, v;
        while (S.size() < numberOfNodes) {
            // select the node not yet in S with at least one edge from S
            // which has the shortest distance
            for (Edge e : adj.get(u.getNodeNumber())) {
                v = e.getInNode();
                changeKey(u, v, e); // update distance
            }
            pq.heapify(); // reheap after changeKey()
            v = pq.extractMin();
            S.add(v); // add next node to set S
            u = v;
        }
        
        /////////////////////////////////////////////////////////////
        //
        // end algorithm
        //
        ////////////////////////////////////////////////////////////
        
        // display distances of shortest path from starting node to each node
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println("shortest path to node " + node[i].getNodeNumber()
                    + " is length " + node[i].getKey() + " ");
        }
        
        // display shortest path from node 0 to node 7
        Node startNode = node[0];
        Node finishNode = node[7];
        Stack<Node> shortestPath = new Stack<>();
        shortestPath.push(finishNode);

        Node current = finishNode, parent = current;
        while (parent != startNode) {
            parent = current.getParent();
            shortestPath.push(parent);
            current = parent;
        }
        System.out.println("\nshortest path from node " + startNode.getNodeNumber()
                + " to node " + finishNode.getNodeNumber() + " is:");
        System.out.print("[ ");
        while (!shortestPath.isEmpty())
            System.out.print(shortestPath.pop().getNodeNumber() + " ");
        System.out.println("]");
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
