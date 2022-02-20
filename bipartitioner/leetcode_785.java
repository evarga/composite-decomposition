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
    public boolean isBipartite(int[][] adjacencyList) {
        return new Graph(adjacencyList).bipartition() != null;
    }
}