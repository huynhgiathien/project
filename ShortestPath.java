import java.util.*; 
public class ShortestPath { 
    private int dist[]; 
    private Set<Integer> settled; 
    private PriorityQueue<Node> pq; 
    private int V; // Number of vertices 
    List<List<Node> > ob; 
  
    public ShortestPath(int V) 
    { 
        this.V = V; 
        dist = new int[V]; 
        settled = new HashSet<Integer>(); 
        pq = new PriorityQueue<Node>(V, new Node()); 
    } 
  
    public void dijkstra(List<List<Node> > ob, int source) 
    { 
        this.ob = ob; 
  
        for (int i = 0; i < V; i++) 
            dist[i] = Integer.MAX_VALUE; 
  
        // Add source node to the priority queue 
        pq.add(new Node(source, 0)); 
  
        // Distance to the source is 0 
        dist[0] = 0; 
        while (settled.size() != V) { 
            int u = pq.remove().node; 
            settled.add(u); 
  
            e_Neighbours(u); 
        } 
    } 
  
    // Function to process all the neighbours  
    // of the passed node 
    private void e_Neighbours(int u) 
    { 
        int edgeDistance = -1; 
        int newDistance = -1; 
  
        for (int i = 0; i < ob.get(u).size(); i++) { 
            Node v = ob.get(u).get(i); 
  
            if (!settled.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 
  
                if (newDistance < dist[v.node]) 
                    dist[v.node] = newDistance; 
  
                pq.add(new Node(v.node, dist[v.node])); 
            } 
        } 
    } 
  

    public static void main(String arg[]) 
    { 
        int V = 5; 
        int source = 0; 

        List<List<Node> > ob = new ArrayList<List<Node> >(); 
  
        // Initialize list for every node 
        for (int i = 0; i < V; i++) { 
            List<Node> item = new ArrayList<Node>(); 
            ob.add(item); 
        } 
  
        // Inputs for the ShortestPath graph 
        ob.get(0).add(new Node(1, 9)); 
        ob.get(0).add(new Node(2, 6)); 
        ob.get(0).add(new Node(3, 5)); 
        ob.get(0).add(new Node(4, 3)); 
  
        ob.get(2).add(new Node(1, 2)); 
        ob.get(2).add(new Node(3, 4)); 
  
        // Calculate the single source shortest path 
        ShortestPath dpq = new ShortestPath(V); 
        dpq.dijkstra(ob, source); 
  
        // Print the shortest path to all the nodes 
        // from the source node 
        System.out.println("The shorted path from node :"); 
        for (int i = 0; i < dpq.dist.length; i++) 
            System.out.println("From point " + source + " to point " + i + " is "
                               + dpq.dist[i]); 
    } 
} 
  

class Node implements Comparator<Node> { 
    public int node; 
    public int cost; 
  
    public Node() 
    { 
    } 
  
    public Node(int node, int cost) 
    { 
        this.node = node; 
        this.cost = cost; 
    } 
  
    @Override
    public int compare(Node node1, Node node2) 
    { 
        if (node1.cost < node2.cost) 
            return -1; 
        if (node1.cost > node2.cost) 
            return 1; 
        return 0; 
    } 
} 