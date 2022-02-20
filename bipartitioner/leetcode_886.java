class Graph {
    public enum Group {A, B}

    private final int[][] adjacencyList;
    
    /**
     * Creates an instance of this class representing some concrete graph.
     *
     * @param adjacencyList the 2D array encoding the adjacency information.
     */
    public Graph(int[][] adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    /**
     * Creates an instance of this class representing some concrete graph.
     *
     * @param n the number of nodes.
     * @param edges the array of pairs (i, j), where each pair represents an undirected edge between nodes i and j. 
     */
    public Graph(int n, int[][] edges) {
        this(n, edges, 0);
    }

    /**
     * Creates an instance of this class representing some concrete graph.
     *
     * @param n the number of nodes.
     * @param edges the array of pairs (i, j), where each pair represents an undirected edge between nodes i and j. 
     * @param offset the shift factor in numbering nodes. For example, if nodes are labeled from 1 to n, then this is set to 1.
     */
    public Graph(int n, int[][] edges, int offset) {
        var dynamicAdjacencyList = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++)
            dynamicAdjacencyList.add(new ArrayList<>());
        for (var edge: edges) {
            dynamicAdjacencyList.get(edge[0] - offset).add(edge[1] - offset);
            dynamicAdjacencyList.get(edge[1] - offset).add(edge[0] - offset);
        }
        
        adjacencyList = new int[n][];
        for (int u = 0; u < n; u++) {
            var neighbors = dynamicAdjacencyList.get(u);
            adjacencyList[u] = new int[neighbors.size()];
            var j = 0;
            for (var v: neighbors)
                adjacencyList[u][j++] = v;    
        }
    }
    
    private boolean bipartition(int u, Group current, Group[] groups) {
        if (groups[u] == null) {
            groups[u] = current;
            var opposite = (current == Group.A ? Group.B : Group.A);
            for (var v: adjacencyList[u])
                if (!bipartition(v, opposite, groups))
                    return false;
        }
        return groups[u] == current;
    }
    
    /**
     * Tries to segregate nodes into two groups such that no two nodes of the same group are connected.
     *
     * @return the assignmement of nodes to groups or <pre>null</pre> if the graph is not bipartite.
     */
    // TODO: Implement caching by considering both success and failure scenarios.
    public Group[] bipartition() {
        var n = adjacencyList.length;
        var groups = new Group[n];
        
        for (int u = 0; u < n; u++)
            if (groups[u] == null && !bipartition(u, Group.A, groups))
                return null;
        return groups;
    }
}


class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        return new Graph(n, dislikes, 1).bipartition() != null;    
    }
}