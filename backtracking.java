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
                backtrackingS(res, list, nums, i, target);
                list.remove(list.size()-1);
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

