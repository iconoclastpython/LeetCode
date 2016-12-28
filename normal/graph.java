Number of Connected Components in an Undirected Graph:
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

