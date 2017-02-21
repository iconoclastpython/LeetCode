46. Permutations
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        backtracking(res, curList, nums);
        return res;
    }
    
    private void backtracking(List<List<Integer>> res, List<Integer> curList, int[] nums) {
        if(curList.size() == nums.length)
            res.add(new ArrayList<>(curList));
        else {
            for(int i = 0; i < nums.length; i++) {
                if(curList.contains(nums[i])) continue;
                curList.add(nums[i]);
                backtracking(res, curList, nums);
                curList.remove(curList.size()-1);
            }
        }
    }
}
-------------------------------------------------

47. Permutations II
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums); // Important
        backtracking(res, curList, used, nums);
        return res;
    }
    
    private void backtracking(List<List<Integer>> res, List<Integer> curList, boolean[] used, int[] nums) {
        if(curList.size() == nums.length) 
            res.add(new ArrayList<>(curList));
        else {
            for(int i = 0; i < nums.length; i++) {
                if(used[i] == true || (i > 0 && nums[i] == nums[i-1] && used[i-1] == false)) continue;
                used[i] = true;
                curList.add(nums[i]);
                backtracking(res, curList, used, nums);
                used[i] = false;
                curList.remove(curList.size()-1);
            }
        }
    }
}
-------------------------------------------------

78. Subsets
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrackingS(res, list, nums, 0);
        return res;
    }
    
    private void backtrackingS(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtrackingS(res, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}
-------------------------------------------------

90. Subsets II
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackingS(res, list, nums, 0);
        return res;
    }
    
    private void backtrackingS(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            backtrackingS(res, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}
-------------------------------------------------

39. Combination Sum
public class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackingS(res, list, nums, 0, target, 0);
        return res;
    }
    
    private void backtrackingS(List<List<Integer>> res, List<Integer> list, int[] nums, int start, int target, int sum) {
        if(sum > target) return;
        else if(sum == target) {
            Collections.sort(list);
            if(!res.contains(list))
                res.add(new ArrayList<>(list));
        }
        else {
            for(int i = start; i < nums.length; i++) {
                sum += nums[i];
                list.add(nums[i]);
                backtrackingS(res, list, nums, i, target, sum);
                list.remove(list.size()-1);
                sum -= nums[i];
            }
        }
    }
}
-------------------------------------------------

40. Combination Sum II
public class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackingS(res, list, nums, 0, target);
        return res;
    }
    
    private void backtrackingS(List<List<Integer>> res, List<Integer> list, int[] nums, int start, int target) {
        int sum = 0;
        for(int n : list) sum += n;
        if(sum > target) return;
        else if(sum == target) {
            Collections.sort(list);
            if(!res.contains(list))
                res.add(new ArrayList<>(list));
        }
        else {
            for(int i = start; i < nums.length; i++) {
                list.add(nums[i]);
                backtrackingS(res, list, nums, i+1, target);
                list.remove(list.size()-1);
            }
        }
    }
}
-------------------------------------------------

131. Palindrome Partitioning
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() == 0) return res;
        List<String> partition = new ArrayList<>();
        helper(0, s, partition, res);
        return res;
    }
    
    private void helper(int pos, String s, List<String> partition, List<List<String>> res) {
        if(pos == s.length()) {
            res.add(new ArrayList<>(partition));
            return ;
        }
        for(int i = pos+1; i <= s.length(); i++) {
            String curStr = s.substring(pos, i);
            if(!isPalindrome(curStr)) continue;
            partition.add(curStr);
            helper(i, s, partition, res);
            partition.remove(partition.size()-1);
        }
    }
    
    private boolean isPalindrome(String str) {
        int p = 0, q = str.length()-1;
        if(p == q) return true;
        while(p < q) {
            if(str.charAt(p) != str.charAt(q)) return false;
            p++;
            q--;
        }
        return true;
    }
}
-------------------------------------------------

17. Letter Combinations of a Phone Number
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder();
        helper(sb, digits, mapping, res);
        
        if(res.get(0).length() == 0)
            res.remove(0);
            
        return res;
    }
    
    private void helper(StringBuilder sb, String digits, String[] mapping, List<String> res) {
        if(sb.length() == digits.length()) {
            res.add(sb.toString());
            return ;
        }
        
        for(char c : mapping[digits.charAt(sb.length()) - '0'].toCharArray()) {
            sb.append(c);
            helper(sb, digits, mapping, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
-------------------------------------------------

291. Word Pattern II
public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return isMatch(map, set, 0, 0, pattern, str);
    }
    
    private boolean isMatch(Map<Character, String> map, 
                            Set<String> set, 
                            int pIndex, int sIndex,
                            String pattern, String str) {
        // Base case
        if(sIndex == str.length() && pIndex == pattern.length()) return true;
        if(sIndex == str.length() || pIndex == pattern.length()) return false;
        
        // Get current pattern char
        char p = pattern.charAt(pIndex);
        
        // Get the match if existed in map
        if(map.containsKey(p)) {
            String curMat = map.get(p);
            
            // Check if match on current index
            if(!str.startsWith(curMat, sIndex)) {
                return false;
            }
            
            // Continue to recursive check next part
            return isMatch(map, set, pIndex+1, sIndex+curMat.length(), pattern, str);
        }
        
        // Case for current pattern's match not existed in map
        for(int i = sIndex; i < str.length(); i++) {
            String mat = str.substring(sIndex, i+1);
            if(set.contains(mat)) continue;
            
            map.put(p, mat);
            set.add(mat);
            
            if(isMatch(map, set, pIndex+1, i+1, pattern, str))
                return true;
                
            map.remove(p);
            set.remove(mat);
        }
        return false;
    }
}
-------------------------------------------------

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
