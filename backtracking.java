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