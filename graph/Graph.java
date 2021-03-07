package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<T extends Comparable<? super T>> {
    
    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>(true);

        g.addEdge(0, 1, 9);
        g.addEdge(0, 5, 14);
        g.addEdge(0, 6, 15);
        g.addEdge(1, 2, 24);
        g.addEdge(2, 4, 2);
        g.addEdge(2, 7, 19);
        g.addEdge(3, 2, 6);
        g.addEdge(3, 7, 6);
        g.addEdge(4, 3, 11);
        g.addEdge(4, 7, 16);
        g.addEdge(5, 2, 18);
        g.addEdge(5, 6, 5);
        g.addEdge(6, 4, 20);
        g.addEdge(6, 7, 44);

        System.out.println("graph:\n" + g.toString());
        g.displayShortestPath(0, 7);
        System.out.println("\nbreadth first traversal:\n" 
                + g.getBreadthFirstTraversal(0));
        System.out.println("\ndepth first traversal:\n"
                + g.getDepthFirstTraversal(0));
        Graph<Integer> mst = g.getMinimumSpanningTree(0);
        System.out.println("\nminimum spanning tree:\n"
                + mst.toString());
    }
    
    private Map<T, Vertex> graph;
    private boolean isDigraph;
    
    public Graph() {
        this(false);
    }
    
    public Graph(boolean directional) {
        graph = new HashMap<>();
        isDigraph = directional;
    }
    
    public void addEdge(T u, T v) {
        addEdge(u, v, 0.0);
    }

    public void addEdge(T u, T v, double weight) {
        if (!graph.containsKey(u)) graph.put(u, new Vertex(u));
        if (!graph.containsKey(v)) graph.put(v, new Vertex(v));
        
        graph.get(u).edges.put(v, weight);
        if (!isDigraph)
            graph.get(v).edges.put(u, weight);
    }
    
    // implementation of Dijkstra's shortest paths algorithm from vertex s to f
    public List<T> displayShortestPath(T s, T f) {
        Vertex start = graph.get(s), finish = graph.get(f);
        start.cost = 0; // initialize cost of start

        // priority queue - locate lowest cost vertex in O(1)
        Queue<Vertex> pq = new PriorityQueue<>((u, v) -> ((Double) u.cost).compareTo(v.cost));
        pq.addAll(graph.values());

        // set S stores explored vertices
        Set<Vertex> S = new HashSet<>();
        
        Vertex u = null, v = null;
        while(S.size() < graph.keySet().size()) {
            u = pq.poll();
            S.add(u);

            // explore and update costs of unexplored vertices
            Iterator<T> endVertices = u.edges.keySet().iterator();
            while (endVertices.hasNext()) {
                v = graph.get(endVertices.next());
                if (!S.contains(v) && u.cost + u.edges.get(v.label) < v.cost) {
                    pq.remove(v);
                    v.cost = u.cost + u.edges.get(v.label);
                    v.parent = u;
                    pq.add(v);
                }
            }
        }
        
        // build shortest path beginning with finish and working backward
        Stack<Vertex> path = new Stack<>();
        path.push(finish);
        
        Vertex current = finish, parent = null;
        while(current.parent != null) {
            parent = current.parent;
            path.push(parent);
            current = parent;
        }
        
        // display distance from origin to each vertex
        System.out.println("shortest distances: ");
        for (Vertex vert : graph.values()) {
            if (vert.cost < Double.MAX_VALUE) // a path exists to this vertex
                System.out.println(start.label + " to " + vert.label
                        + ": " + vert.cost);
            else
                System.out.println("no path exists");
        }
        
        // display shortest path
        List<T> shortestPath = new ArrayList<>();
        if (current == start) { // path exists
            System.out.println("\nshortest path:");
            System.out.print("[");
            while (path.size() > 1) {
                T vertex = path.pop().label;
                System.out.print(vertex + ", ");
                shortestPath.add(vertex);
            } 
            T vertex = path.pop().label;
            System.out.println(vertex + "]");
            shortestPath.add(vertex);
        } 
        
        return shortestPath;
    }
    
    public List<T> getBreadthFirstTraversal(T root) {
        List<T> traversal = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> q = new LinkedList<>();
        Vertex s = graph.get(root);
        
        visited.add(s);
        q.add(s);
        while (!q.isEmpty()) {
            Vertex u = q.poll();
            traversal.add(u.label);
            Iterator<T> endVertices = u.edges.keySet().iterator();
            while (endVertices.hasNext()) {
                Vertex v = graph.get(endVertices.next());
                if (!visited.contains(v)) {
                    visited.add(v);
                    q.add(v);
                }
            }
        }
        return traversal;
    }
    
    public List<T> getDepthFirstTraversal(T root) {
        List<T> traversal = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        Vertex s = graph.get(root);
        
        stack.push(s);
        while (!stack.isEmpty()) {
            Vertex u = stack.pop();
            if (!visited.contains(u)) {
                visited.add(u);
                traversal.add(u.label);
                Iterator<T> endVertices = u.edges.keySet().iterator();
                while (endVertices.hasNext()) {
                    Vertex v = graph.get(endVertices.next());
                    stack.push(v);
                }
            }
        }
        return traversal;
    }

    // Prim's algorithm to find the minimum spanning tree
    public Graph<T> getMinimumSpanningTree(T root) {
        Graph<T> mst = new Graph<>(true);
        Set<Vertex> explored = new HashSet<>();
        Queue<Vertex> pq = new PriorityQueue<>((u, v) -> ((Double) u.cost).compareTo(v.cost));
        Vertex u = graph.get(root);
        u.cost = 0;
        pq.addAll(graph.values());
        
        while (!pq.isEmpty()) {
            u = pq.poll();
            explored.add(u);
            if (u.parent != null)
                mst.addEdge(u.parent.label, u.label, u.parent.edges.get(u.label));
            Iterator<T> endVertices = u.edges.keySet().iterator();
            while (endVertices.hasNext()) {
                Vertex v = graph.get(endVertices.next());
                double newCost = u.cost + u.edges.get(v.label);
                if (!explored.contains(v) && newCost < v.cost) {
                    pq.remove(v);
                    v.cost = newCost;
                    v.parent = u;
                    pq.offer(v);
                }
            }
        }
        return mst;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("edges:\tweights:\n");
        for (Vertex u : graph.values()) {
            Iterator<T> endVertices = u.edges.keySet().iterator();
            while (endVertices.hasNext()) {
                T v = endVertices.next();
                sb.append("(" + u.label + ", " + v + ")\t" + u.edges.get(v) + "\n");
            }
        }
        return sb.toString();
    }
    
    protected class Vertex {
        T label;
        Map<T, Double> edges;
        Vertex parent;
        double cost;
        
        protected Vertex(T label) {
            this.label = label;
            edges = new HashMap<>();
            parent = null;
            cost = Double.MAX_VALUE;
        }
    }
}
