42. Trapping Rain Water
public class Solution {
    public int trap(int[] height) {
        int res = 0, left = 0, right = height.length-1;
        if(left >= right) return 0;
        int leftHeight = height[left], rightHeight = height[right];
            
        while(left < right) {
            if(leftHeight < rightHeight) {
                left++;
                if(leftHeight < height[left]) {
                    leftHeight = height[left];
                }
                else {
                    res += (leftHeight - height[left]);
                }
            }
            else {
                right--;
                if(rightHeight < height[right]) {
                    rightHeight = height[right];
                }
                else {
                    res += (rightHeight - height[right]);
                }
            }
        }
        return res;
    }
}
------------------------------------

407. Trapping Rain Water II
public class Solution {
    class Cell {
        int x, y, h;
        public Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    } 
    
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int trapRainWater(int[][] heights) {
        if(heights.length == 0) return 0;
        Queue<Cell> pq = new PriorityQueue<>(1, new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2) {
                return c1.h - c2.h;
            }
        });
        
        int row = heights.length;
        int col = heights[0].length;
        int[][] visit = new int[row][col];
        
        for(int i = 0; i < row; i++) {
            pq.offer(new Cell(i, 0, heights[i][0]));
            visit[i][0] = 1;
            pq.offer(new Cell(i, col-1, heights[i][col-1]));
            visit[i][col-1] = 1;
        }
        
        for(int j = 0; j < col; j++) {
            pq.offer(new Cell(0, j, heights[0][j]));
            visit[0][j] = 1;
            pq.offer(new Cell(row-1, j, heights[row-1][j]));
            visit[row-1][j] = 1;
        }
        
        int total = 0;
        while(!pq.isEmpty()) {
            Cell curCell = pq.poll();
            for(int i = 0; i < 4; i++) {
                int nx = curCell.x + dx[i];
                int ny = curCell.y + dy[i];
                if(nx >= 0 && nx < row && ny >= 0 && ny < col && visit[nx][ny] == 0) {
                    visit[nx][ny] = 1;
                    pq.offer(new Cell(nx, ny, Math.max(curCell.h, heights[nx][ny])));
                    total += Math.max(0, curCell.h - heights[nx][ny]);
                }
            }
        }
        return total;
    }
}
------------------------------------

11. Container With Most Water
public class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1, maxArea = 0;
        while(left < right) {
            maxArea = Math.max(maxArea, (right-left)*(Math.min(height[left], height[right])));
            if(height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }
}
------------------------------------

215. Kth Largest Element in an Array
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return getKth(nums, 0, nums.length-1, nums.length - k + 1);
    }
    
    private int getKth(int[] nums, int left, int right, int k) {
        if(left == right) return nums[left];
        int pIndex = partition(nums, left, right);
        if(pIndex + 1 < k) return getKth(nums, pIndex+1, right, k);
        else if(pIndex + 1 > k) return getKth(nums, left, pIndex-1, k);
        else return nums[pIndex];
    }
    
    private int partition(int[] nums, int left, int right) {
        Random rand = new Random();
        int pivotIndex = rand.nextInt(right-left) + left;
        int pivot = nums[pivotIndex];

        int tmp = nums[pivotIndex];
        nums[pivotIndex] = nums[left];
        nums[left] = tmp;
        
        while(left < right) {
            while(left < right && nums[right] >= pivot) right--;
            nums[left] = nums[right];
            while(left < right && nums[left] <= pivot) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
}
------------------------------------

209. Minimum Size Subarray Sum
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int left = 0, right = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while(right < nums.length) {
            sum += nums[right++];
            while(sum >= s) {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
------------------------------------

3. Longest Substring Without Repeating Characters
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[256];
        int p = 0, res = 0;
        for(int i = 0; i < s.length(); i++) {
            while(p < s.length() && map[s.charAt(p)] == 0) {
                map[s.charAt(p)] = 1;
                res = Math.max(res, p-i+1);
                p++;
            }
            map[s.charAt(i)] = 0;
        }
        return res;
    }
}
------------------------------------

76. Minimum Window Substring
public class Solution {
    public String minWindow(String source, String target) {
        if(source == null || source.length() == 0) return "";
        if(target == null || target.length() == 0) return "";
        
        int[] targetHash = new int[256];
        int[] sourceHash = new int[256];
        
        initTargetHash(targetHash, target);
        int left = 0, right = 0, minLen = Integer.MAX_VALUE;
        String res = "";
        while(left < source.length()) {
            while(right < source.length() && !isValid(sourceHash, targetHash)) {
                sourceHash[source.charAt(right++)]++;
            }
            if(isValid(sourceHash, targetHash)) {
                if(minLen > right-left) {
                    minLen = right-left;
                    res = source.substring(left, right);
                }
            }
            sourceHash[source.charAt(left++)]--;
        }
        return res;
    }
    
    private void initTargetHash(int[] targetHash, String target) {
        char[] tArray = target.toCharArray();
        for(char c : tArray) {
            targetHash[c]++;
        }
    }
    
    private boolean isValid(int[] sourceHash, int[] targetHash) {
        for(int i = 0; i < 256; i++) {
            if(sourceHash[i] < targetHash[i])
                return false;
        }
        return true;
    }
}
------------------------------------

