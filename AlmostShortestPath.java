import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class AlmostShortestPath {

    public static AlmostShortestPathGraph<Integer> graph; // representation of the graph
    public static int M; // number of directed edges
    public static int S; // starting vertex
    public static int D; // destination vertex
    
    public static void main(String[] args) {
        
        // get input
        try (Scanner input = new Scanner(System.in)) {
            while (input.hasNextLine()) {
                /* 
                 * "The first line of a test case contains two integers 
                 * N (2 <= N <= 500) and M (1 <= M <= 104), separated by a single space,
                 * indicating respectively the number of points in the map and the 
                 * number of existing one-way routes connecting two points directly. 
                 * Each point is identified by an integer between 0 and N -1."
                 * 
                 * "The end of input is indicated by a line containing only two zeros 
                 * separated by a single space."
                 */
                String line = input.nextLine();
                if (line.equals("0 0")) break;
                String[] NM = line.split(" ");
                M = Integer.parseInt(NM[1]);
                
                /*
                 * "The second line contains two integers S and D, separated by a single space, 
                 * indicating respectively the starting and the destination points 
                 * (S != D; 0 <= S, D <= N)."
                 */
                line = input.nextLine();
                String[] SD = line.split(" ");
                S = Integer.parseInt(SD[0]);
                D = Integer.parseInt(SD[1]);
                
                /*
                 * "Each one of the following M lines contains three 
                 * integers U, V and P (U != V; 0 <= U, V < N; 1 <= P <= 103),
                 * separated by single spaces, indicating the existence of 
                 * a one-way route from U to V with distance P."
                 */
                graph = new AlmostShortestPathGraph<>();
                for (int i = 0; i < M; i++) {
                    line = input.nextLine();
                    String[] UVP = line.split(" ");
                    int U = Integer.parseInt(UVP[0]);
                    int V = Integer.parseInt(UVP[1]);
                    int P = Integer.parseInt(UVP[2]);
                    graph.addEdge(U, V, P);
                }
                
                /*
                 * Return the almost shortest path.
                 * "The almost shortest path as the shortest path that goes from a starting point 
                 * to a destination point such that no route between two consecutive points belongs 
                 * to any shortest path from the starting point to the destination."
                 */ 
                System.out.println(graph.getAlmostShortestPath(S, D));
            }
        }
    }
}

class AlmostShortestPathGraph<T extends Comparable<? super T>> {
    
    private Map<T, Vertex> graph;
    private Set<Edge> shortestPathEdges;
    
    public AlmostShortestPathGraph() {
        graph = new HashMap<>();
        shortestPathEdges = new HashSet<>();
    }

    public void addEdge(T u, T v, int weight) {
        if (!graph.containsKey(u)) graph.put(u, new Vertex(u));
        if (!graph.containsKey(v)) graph.put(v, new Vertex(v));
        
        graph.get(u).edges.put(graph.get(v), new Edge(graph.get(u), graph.get(v), weight));
    }
    
    public void addEdge(Edge e) {
        graph.get(e.u.label).edges.put(e.v, e);
    }
    
    public void removeEdge(Edge e) {
        graph.get(e.u.label).edges.remove(e.v);
    }
    
    public void resetVertices() {
        for (Vertex v : graph.values()) {
            v.parent.clear();
            v.cost = Integer.MAX_VALUE;
        }
    }
    
    // returns the length of the "almost shortest path"
    public int getAlmostShortestPath(T start, T dest) {

        getShortestPath(start, dest);

        // remove all edges part of a shortest path
        for (Edge e : shortestPathEdges)
            removeEdge(e);
        resetVertices();

        return getShortestPath(start, dest);
    }
    
    // implementation of Dijkstra's shortest path
    public int getShortestPath(T s, T d) {
        // get start and destination vertices
        Vertex start = graph.get(s), dest = graph.get(d);
        if (start == null || dest == null || start.equals(dest))
            return -1;
        start.cost = 0; // initialize cost of start

        // priority queue - locate lowest cost vertex in O(1)
        Queue<Vertex> pq = new PriorityQueue<>((u, v) -> ((Integer) u.cost).compareTo(v.cost));
        pq.addAll(graph.values());

        // set of explored vertices
        Set<Vertex> explored = new HashSet<>();
        
        while(explored.size() < graph.keySet().size()) {
            Vertex u = pq.poll();
            explored.add(u);

            // explore and update costs of unexplored vertices
            Iterator<Vertex> endVertices = u.edges.keySet().iterator();
            while (endVertices.hasNext()) {
                Vertex v = endVertices.next();
                int newCost = u.cost + u.edges.get(v).weight;
                if (!explored.contains(v) && newCost <= v.cost) {
                    pq.remove(v);
                    if (newCost < v.cost) {
                        v.parent.clear();
                        v.cost = newCost;
                    }
                    v.parent.add(u);
                    pq.add(v);
                }
            }
        }
        // build set of shortest-path edges
        Queue<Vertex> q = new LinkedList<>();
        Vertex v = dest;
        q.offer(v);
        while (!q.isEmpty() && v.parent != null) {
            v = q.poll();
            for (Vertex u : v.parent) {
                q.offer(u);
                shortestPathEdges.add(u.edges.get(v));
            }
        } 
        // return -1 if no path exists, else return shortest path cost
        return v != start ? -1 : dest.cost; 
    }
    
    class Vertex {
        T label;
        Map<Vertex, Edge> edges;
        List<Vertex> parent;
        int cost;
        
        Vertex(T label) {
            this.label = label;
            edges = new HashMap<>();
            parent = new ArrayList<>();
            cost = Integer.MAX_VALUE;
        }
    }
    
    class Edge {
        Vertex u;
        Vertex v;
        int weight;
        
        Edge(Vertex u, Vertex v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}