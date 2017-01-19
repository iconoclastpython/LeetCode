Number of Connected Components in an Undirected Graph:
// Union Find
public class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for(int i = 0; i < n; i++) roots[i] = i;

        for(int[] edge : edges) {
            int root1 = find(roots, edge[0]);
            int root2 = find(roots, edge[1]);
            if(root1 != root2) {
                roots[root1] = root2;
                n--;
            }
        }
        return n;
    }
    
    private int find(int[] roots, int id) {
        while(id != roots[id]) {
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }
}

// DFS
public class Solution {
    public int countComponents(int n, int[][] edges) {
        if(n <= 1) return n;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for(int i : map.keySet()) {
            if(visited.add(i)) {
                dfsVisited(i, map, visited);
                count++;
            }
        }
        return count;
    }
    
    private void dfsVisited(int i, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        for(int num : map.get(i)) {
            if(visited.add(num))
                dfsVisited(num, map, visited);
        }
    }
}
-------------------------------------------------

Number of Islands:
public class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int islands = 0, row = grid.length, col = grid[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    explore(grid, i, j);
                    islands++;
                }
            }
        }
        return islands;
    }
    
    private void explore(char[][] grid, int i, int j) {
        grid[i][j] = 'x';
        for(int d = 0; d < dx.length; d++)
            if(i+dy[d] < grid.length && i+dy[d] >= 0 && j+dx[d] < grid[0].length && j+dx[d] >= 0 && grid[i+dy[d]][j+dx[d]] == '1')
                explore(grid, i+dy[d], j+dx[d]);
    }
}
-------------------------------------------------

Number of Islands II:
// Union Find
public class Solution {
	private static int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if(m <= 0 || n <= 0) return res;
        int islands = 0;
        int[] roots = new int[m*n];
        Arrays.fill(roots, -1);

        for(int[] p : positions) {
            int root = n * p[0] + p[1]; // linear mapping
            roots[root] = root;
            islands++;

            for(int[] d : dirs) {
                int x = p[0] + d[0];
                int y = p[1] + d[1];
                int nb = n * x + y;
                if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;

                int rootNB = findIsland(roots, nb);
                if(root != rootNB) {
                    roots[rootNB] = root; // Union
                    islands--;
                }
            }
            res.add(islands);
        }
        return res;
    }
    
    private int findIsland(int[] roots, int id) {
        while(id != roots[id]) {
            roots[id] = roots[roots[id]]; // path compression
            id = roots[id];
        }
        return id;
    }
}
-------------------------------------------------

Graph Valid Tree:
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n-1) return false;
        int[] roots = new int[n];
        Arrays.fill(roots, -1);
        for(int[] edge : edges) {
            int root1 = find(roots, edge[0]);
            int root2 = find(roots, edge[1]);
            if(root1 == root2) return false;
            roots[root1] = root2;
        }
        return true;
    }
    
    private int find(int[] roots, int id) {
        if(roots[id] == -1) return id;
        return find(roots, roots[id]);
    }
}
-------------------------------------------------

