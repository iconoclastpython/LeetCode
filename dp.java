House Robber:
public class Solution {
    // Solution 1
    public int rob(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len+1][2];
        for(int i = 1; i <= len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = nums[i-1] + dp[i-1][0];
        }
        return Math.max(dp[len][0], dp[len][1]);
    }
    
    // Solution 2
    public int rob(int[] nums) {
        int preNo = 0, preYes = 0;
        for(int n : nums) {
            int tmp = preNo;
            preNo = Math.max(preNo, preYes);
            preYes = n + tmp;
        }
        return Math.max(preNo, preYes);
    }
}
-------------------------------------------------

House Robber II:
public class Solution {
    private int robLine(int[] nums, int low, int high) {
        int preNo = 0, preYes = 0;
        for(int i = low; i <= high; i++) {
            int tmp = preNo;
            preNo = Math.max(preNo, preYes);
            preYes = nums[i] + tmp;
        }
        return Math.max(preNo, preYes);
    }
    
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        return Math.max(robLine(nums, 0, nums.length - 2), robLine(nums, 1, nums.length - 1));
    }
}
-------------------------------------------------

House Robber III:
public class Solution {
    public int rob(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }
    
    private int[] robSub(TreeNode root) {
        if(root == null) return new int[2];
        
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];
        
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        
        return res;
    }
}
-------------------------------------------------
