127. Word Ladder

public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0) return 0;
        if(beginWord.equals(endWord)) return 0;
        wordList.add(beginWord);
        //wordList.add(endWord);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> pastWords = new HashSet<>();s
        pastWords.add(beginWord);
        
        int ladder = 1;
        while(!queue.isEmpty()) {
            ladder++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> nextWords = getNextWords(cur, wordList);
                for(String w : nextWords) {
                    if(pastWords.contains(w))
                        continue;
                    if(endWord.equals(w))
                        return ladder;
                    queue.add(w);
                    pastWords.add(w);
                }
            }
        }
        return 0;
    }
    
    private List<String> getNextWords(String word, List<String> wordList) {
        List<String> nextWords = new ArrayList<>();
        for(char c = 'a'; c <= 'z'; c++) {
            for(int i = 0; i < word.length(); i++) {
                String newWord = replaceWord(word, c, i);
                if(wordList.contains(newWord)) {
                    nextWords.add(newWord);
                }
            }
        }
        return nextWords;
    }
    
    private String replaceWord(String word, char c, int index) {
        char[] wordArray = word.toCharArray();
        wordArray[index] = c;
        return new String(wordArray);
    }
    //hit hot dot dog cog
}
------------------------------------

Word Ladder II:

public List<List<String>> findLadders(String start, String end, List<String> wordList) {
   HashSet<String> dict = new HashSet<String>(wordList);
   List<List<String>> res = new ArrayList<List<String>>();         
   HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
   HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
   ArrayList<String> solution = new ArrayList<String>();

   dict.add(start);          
   bfs(start, end, dict, nodeNeighbors, distance);                 
   dfs(start, end, dict, nodeNeighbors, distance, solution, res);   
   return res;
}

// BFS: Trace every node's distance from the start node (level by level).
private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
  for (String str : dict)
      nodeNeighbors.put(str, new ArrayList<String>());

  Queue<String> queue = new LinkedList<String>();
  queue.offer(start);
  distance.put(start, 0);

  while (!queue.isEmpty()) {
      int count = queue.size();
      boolean foundEnd = false;
      for (int i = 0; i < count; i++) {
          String cur = queue.poll();
          int curDistance = distance.get(cur);                
          ArrayList<String> neighbors = getNeighbors(cur, dict);

          for (String neighbor : neighbors) {
              nodeNeighbors.get(cur).add(neighbor);
              if (!distance.containsKey(neighbor)) {// Check if visited
                  distance.put(neighbor, curDistance + 1);
                  if (end.equals(neighbor))// Found the shortest path
                      foundEnd = true;
                  else
                      queue.offer(neighbor);
                  }
              }
          }

          if (foundEnd)
              break;
      }
  }

// Find all next level nodes.    
private ArrayList<String> getNeighbors(String node, Set<String> dict) {
  ArrayList<String> res = new ArrayList<String>();
  char chs[] = node.toCharArray();

  for (char ch ='a'; ch <= 'z'; ch++) {
      for (int i = 0; i < chs.length; i++) {
          if (chs[i] == ch) continue;
          char old_ch = chs[i];
          chs[i] = ch;
          if (dict.contains(String.valueOf(chs))) {
              res.add(String.valueOf(chs));
          }
          chs[i] = old_ch;
      }

  }
  return res;
}

// DFS: output all paths with the shortest distance.
private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
    solution.add(cur);
    if (end.equals(cur)) {
       res.add(new ArrayList<String>(solution));
    } else {
       for (String next : nodeNeighbors.get(cur)) {            
            if (distance.get(next) == distance.get(cur) + 1) {
                 dfs(next, end, dict, nodeNeighbors, distance, solution, res);
            }
        }
    }           
   solution.remove(solution.size() - 1);
}
------------------------------------

212. Word Search II
public class Solution {
    
    private class TrieNode {
        String word;
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
        public TrieNode() {}
    }
        
    private class TrieTree {
        public TrieNode root;
        
        public TrieTree() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode pointer = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(pointer.children[ c -'a'] == null)
                    pointer.children[ c -'a'] = new TrieNode();
                pointer = pointer.children[ c -'a'];
            }
            pointer.word = word;
            pointer.isWord = true;
        }
    
        public boolean findWord(String word) {
            TrieNode pointer = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(pointer.children[ c -'a'] != null)
                    pointer = pointer.children[ c -'a'];
                else
                    return false;
            }
            return pointer.isWord;
        }
        
    }
    
    public int[] dx = {1, -1, 0, 0};
    public int[] dy = {0, 0, 1, -1};
    
    private void search(char[][] board, int x, int y, TrieNode root, List<String> res) {
        char c = board[x][y];
        if(c == '#' || root.children[ c -'a'] == null) return;
        root = root.children[ c -'a'];
        
        if(root.isWord && !res.contains(root.word)) {
            res.add(root.word);
            root.word = null;
            root.isWord = false;
        }
        
        int row = board.length;
        int col = board[0].length;
        
        board[x][y] = '#';
        for(int i = 0; i < 4; i++){
            int nextX = x+dx[i];
            int nextY = y+dy[i];
            if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                search(board, nextX, nextY, root, res);
            }
        }
        board[x][y] = c;
        
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieTree trie = new TrieTree();
        for(String word : words) {
            trie.insert(word);
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                search(board, i, j, trie.root, res);
            }
        }
        return res;
    }
}
------------------------------------

329. Longest Increasing Path in a Matrix
public class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1}; 
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int maxVal = 1;
        int[][] cache = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int newLen = dfs(matrix, i, j, cache);
                maxVal = Math.max(maxVal, newLen);
            }
        }
        return maxVal;
    }
    
    private int dfs(int[][] matrix, int x, int y, int[][] cache) {
        if(cache[x][y] != 0) return cache[x][y];
        int row = matrix.length;
        int col = matrix[0].length;
        int curMax = 1;
        for(int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || matrix[nextX][nextY] <= matrix[x][y])
                continue;
            int len = 1 + dfs(matrix, nextX, nextY, cache);
            curMax = Math.max(curMax, len);
        }
        cache[x][y] = curMax;
        return curMax;
    }
}