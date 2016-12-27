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
