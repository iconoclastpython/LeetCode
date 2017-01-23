Array:

Plus One:
public class Solution {
    public int[] plusOne(int[] digits) 
    {
        int given = 0;
        for(int i = digits.length-1; i >= 0; i--)
        {
            if(digits[i] == 9)
            {
                digits[i] = 0;
            }
            else
            {
                digits[i]++;
                return digits;
            }
        }
        //If all 9
        int[] newdigits = new int[digits.length+1];
        newdigits[0] = 1;
        //for(int i = 1; i < newdigits.length; i++) newdigits[i] = 0;
        return newdigits;
    }
}
------------------------------------------------
Rotate Array:
public class Solution {
    public void rotate(int[] nums, int k) 
    {
        int len = nums.length;
        if(len == 0 || k <= 0) return ;

        int[] copy = new int[len];
        for(int i = 0; i < len; i++)
            copy[i] = nums[i];
        for(int i = 0; i < len; i++)
            nums[(i+k)%len] = copy[i];
    }
}
------------------------------------------------
Summary Ranges:
public class Solution {
    public List<String> summaryRanges(int[] nums) 
    {
        List<String> res = new ArrayList<>();
        if(nums.length == 0) return res;
        if(nums.length == 1)
        {
        	res.add(Integer.toString(nums[0]));
        	return res;
        }
        
        for(int i = 0; i < nums.length; i++)
        {
        	int cur = nums[i];
        	while(i+1 < nums.length && nums[i]+1 == nums[i+1]) i++;
        	if(cur != nums[i])
        		res.add(cur + "->" + nums[i]);
        	else
        		res.add(Integer.toString(cur));
        }

        return res;
    }
}
------------------------------------------------
 Move Zeroes:
 public class Solution {
    public void moveZeroes(int[] nums) 
    {
        if(nums.length == 0) return;
        
        int pos = 0;
        
        for(int num : nums)
            if(num != 0)
                nums[pos++] = num;
                
        while(pos < nums.length)
            nums[pos++] = 0;
    }
}
------------------------------------------------
Majority Element:
public class Solution {
    public int majorityElement(int[] nums) 
    {
        //O(n) time, O(1) space
        int max = nums[0];
        int count = 0;
        for(int i = 0; i < nums.length; i++)
        {
            if(max == nums[i])
                count++;
            else
                count--;
            
            if(count == 0)
            {
                max = nums[i];
                count = 1;
            }
        }
        return max;
        
    }
}
------------------------------------------------
Majority Element II:
public class Solution {
    
    public List<Integer> majorityElement(int[] nums) 
    {
        List<Integer> res = new ArrayList<>();
        if(nums.length == 0) return res;
        int count1 = 0, count2 = 0, m1 = 0, m2 = 0;
        
        for(int n : nums)
        {
            if(m1 == n) count1++;
            else if(m2 == n) count2++;
            else if(count1 == 0)
            {
                m1 = n;
                count1 = 1;
            }
            else if(count2 == 0)
            {
                m2 = n;
                count2 = 1;
            }
            else
            {
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        
        for(int n : nums)
        {
            if(m1 == n) count1++;
            else if(m2 == n) count2++;
        }
        
        if(count1 > nums.length/3) res.add(m1);
        if(count2 > nums.length/3) res.add(m2);
        
        return res;
    }
}
------------------------------------------------
Unique Paths:
public class Solution {
    public int uniquePaths(int m, int n) 
    {
        //---------dp------------//
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++){
            res[i][0]=1;
        }
        for(int i = 0; i < n; i++){
            res[0][i]=1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                res[i][j]=res[i-1][j]+res[i][j-1];
            }
        }
        return res[m-1][n-1];
            
        //---------Math-----------//
        int N = m + n - 2;// how much steps we need to do
        int k = m - 1;// number of steps that need to go down
        double res = 1;
        // here we calculate the total possible path number 
        // Combination(N, k) = n! / (k!(n - k)!)
        // reduce the numerator and denominator and get
        // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
        for(int i = 1; i <= k; i++)
        {
            res = res * (N-k+i)/i;
        }
        return (int)res;

    }
}
------------------------------------------------
Unique Paths II:
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) 
    {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        
        for(int[] row : obstacleGrid)
        {
            for(int i = 0; i < width; i++)
            {
                if(row[i] == 1)
                    dp[i] = 0;
                else if(i > 0)
                    dp[i] += dp[i-1];
            }
        }
        
        return dp[width-1];
    }
}
------------------------------------------------
Minimum Size Subarray Sum:
public class Solution {
    public int minSubArrayLen(int s, int[] nums) 
    {
        if(nums == null || nums.length == 0) return 0;
        
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        
        while(j < nums.length)
        {
            sum += nums[j++];
            while(sum >= s)
            {
                min = Math.min(min, j-i);
                sum -= nums[i++];
            }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
------------------------------------------------
3Sum:
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) 
    {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < nums.length-2; i++)
        {
            int sum = 0 - nums[i];
            
            if(i == 0 || (i > 0 && nums[i] != nums[i-1]))
            {
                int low = i+1, high = nums.length-1;
                
                while(low < high)
                {
                    if(nums[low] + nums[high] == sum)
                    {
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while(low < high && nums[low] == nums[low+1]) low++;
                        while(low < high && nums[high] == nums[high-1]) high--;
                        low++;
                        high--;
                    }
                    else if(nums[low] + nums[high] < sum) low++;
                    else high--;
                }
            }
        }
        
        return res;
    }
}
------------------------------------------------
Set Matrix Zeroes:
public class Solution {
    public void setZeroes(int[][] matrix) 
    {
        int col0 = -1, row = matrix.length, col = matrix[0].length;
        
        for(int i = 0; i < row; i++)
        {
            if(matrix[i][0] == 0) col0 = 0;
            
            for(int j = 1; j < col; j++)
            {
                if(matrix[i][j] == 0) 
                {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for(int i = row-1; i >= 0; i--)
        {
            for(int j = col-1; j > 0; j--)
            {
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                {
                    matrix[i][j] = 0;
                }
            }
            if(col0 == 0) matrix[i][0] = 0;
        }
    }
}
------------------------------------------------
Search Insert Position:
public class Solution {
    public int searchInsert(int[] nums, int target) 
    {
        int low = 0, high = nums.length-1;
        while(low <= high)
        {
            int mid = low + (high-low)/2;
            if(nums[mid] < target)
                low = mid+1;
            else
                high = mid-1;
        }
        return low;
    }
}
------------------------------------------------
Search in Rotated Sorted Array:
public class Solution{
	public int search(int[] nums, int target)
	{
		int low = 0, high = nums.length-1;

		while(low < high)
		{
			int mid = low + (high-low)/2;

			if(nums[mid] == target) return mid;

			if(nums[low] < nums[mid])
			{
				if(target >= nums[low] && target < nums[mid])
					high = mid - 1;
				else
					low = mid + 1;
			}
			else
			{
				if(target > nums[mid] && target <= nums[high])
					low = mid + 1;
				else
					high = mid - 1;
			}
		}

		return nums[low] == target ? low : -1;
	}
}
------------------------------------------------
Search in Rotated Sorted Array II:
public class Solution {
    public boolean search(int[] nums, int target) 
    {
        int low = 0, high = nums.length-1;
        
        while(low < high)
        {
            int mid = low + (high-low)/2;
            
            if(nums[mid] == target) return true;
            
            if(nums[low] < nums[mid])
            {
                if(target >= nums[low] && target < nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            else if(nums[low] > nums[mid])
            {
                if(target > nums[mid] && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            else low++;
        }
        return nums[low] == target ? true : false;
    }
}
------------------------------------------------
Search for a Range:
public class Solution {
    public int[] searchRange(int[] nums, int target) 
    {
        int range[] = new int[2];
        range[0] = range[1] = -1;
        
        int i = 0, j = nums.length-1;
        
        // Search for the left most one
        while(i < j)
        {
            int mid = (i + j) / 2;
            if(nums[mid] < target) i = mid+1;
            else j = mid;
        }
        
        if(nums[i] != target)
            return range;
        else
            range[0] = i;
        
        // Search for the right most one
        j = nums.length-1;
        
        while(i < j)
        {
            int mid = (i + j) / 2 + 1; // Make mid biased to the right
            if(nums[mid] > target) j = mid-1;
            else i = mid;
        }
        
        range[1] = j;
        return range;
    }
}
------------------------------------------------
Search a 2D Matrix:
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        
        while(left != right)
        {
            int mid = (left + right) / 2;
            if(matrix[mid/col][mid%col] < target) left = mid+1;
            else right = mid;
        }
        
        return matrix[right/col][right%col] == target;
    }
}
------------------------------------------------
Remove Duplicates from Sorted Array:
public class Solution {
    public int removeDuplicates(int[] nums) 
    {
        int len = 0;
        for(int num : nums)
        {
            if(len == 0 || num > nums[len-1])
                nums[len++] = num;
        }
        return len;
    }
}
------------------------------------------------
Remove Duplicates from Sorted Array II:
public class Solution {
    public int removeDuplicates(int[] nums) 
    {
        int i = 0;
        for(int num : nums)
            if(i < 2 || num > nums[i-2])
                nums[i++] = num;
        return i;
    }
}
------------------------------------------------
Insert Interval:
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) 
    {
        List<Interval> res = new ArrayList<>();
        int i = 0;

        while(i < intervals.size() && intervals.get(i).end < newInterval.start)
        	res.add(intervals.get(i++));

        while(i < intervals.size() && intervals.get(i).start <= newInterval.end)
        {
        	newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start),
        		Math.max(intervals.get(i).end, newInterval.end));
        	i++;
        }
        res.add(newInterval);

        while(i < intervals.size())
        	res.add(intervals.get(i++));

        return res;
    }
}
------------------------------------------------
Merge Intervals:
public class Solution {
    public List<Interval> merge(List<Interval> intervals)
    {
        if(intervals.size() <= 1) return intervals;
        
        Collections.sort(intervals, (Interval v1, Interval v2) -> 
        	v1.start.compareTo(v2.start));

        List<Interval> res = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for(Interval item : intervals)
        {
        	if(item.start <= end)
        		end = Math.max(end, item.end);
        	else
        	{
        		res.add(new Interval(start, end));
        		start = item.start;
        		end = item.end;
        	}
        }

        res.add(new Interval(start, end));
        return res;
    }
}
------------------------------------------------
public abstract class Me {

    
}
------------------------------------------------

------------------------------------------------

------------------------------------------------

------------------------------------------------