153. Find Minimum in Rotated Sorted Array
public class Solution {
    public int findMin(int[] nums) {
        int target = nums[nums.length-1];
        int start = 0, end = nums.length-1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] <= target)
                end = mid;
            else 
                start = mid;
        }
        if(nums[start] <= target)
            return nums[start];
        else
            return nums[end];
    }
}
------------------------------------

33. Search in Rotated Sorted Array
public class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length-1, mid;
        while(left + 1 < right) {
            mid = left + (right-left)/2;
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[left] < nums[mid]) {
                if(nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                }
                else {
                    left = mid;
                }
            }
            else {
                if(nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                }
                else {
                    right = mid;
                }
            }
        }
        if(nums[left] == target) return left;
        if(nums[right] == target) return right;
        return -1;
    }
}
------------------------------------

Find Peak Element
class Solution {
    public int findPeak(int[] A) {
        if(A == null || A.length == 0) return -1;
        
        int start = 1, end = A.length-2;
        while(start + 1 < end) {
            int mid = start + (end-start)/2;
            if(A[mid-1] < A[mid])
                start = mid;
            else
                end = mid;

        }
        if(A[start] < A[end])
            return end;
        else
            return start;
    }
}
------------------------------------

Find Peak Element II
class Solution {
    public List<Integer> findPeakII(int[][] A) {
        // 1. find middle row
        // 2. find max num in the line
        // 3. go to up or down if larger
        // 4. both up and down smaller, its a peak
        // 5. mark visited coordinates
        int row = A.length;
        int col = A[0].length;
        boolean flag = true;
        
        return find(1, row-2, 1, col-2, flag, A);
    }
    
    private List<Integer> find(int xUp, int xDown, int yLeft, int yRight, boolean flag, int[][] A) {
        
        if(flag) {
            int midRow = xUp + (xUp-xDown)/2;
            int maxIndex = yLeft;
            for(int i = yLeft; i < yRight; i++) {
                if(A[midRow][i] > A[midRow][maxIndex])
                    maxIndex = i;
            }
            
            int curEle = A[midRow][maxIndex];
            int upAheadEle = A[midRow-1][maxIndex];
            int downAheadEle = A[midRow+1][maxIndex];
            if(upAheadEle > curEle) {
                return find(xUp, midRow-1, yLeft, yRight, !flag, A);
            }
            else if(downAheadEle > curEle) {
                return find(xUp, midRow+1, yLeft, yRight, !flag, A);
            }
            else {
                return new ArrayList<Integer>(Arrays.asList(
                midRow, maxIndex));
            }
        }
        else {
            int midCol = yLeft + (yRight-yLeft)/2;
            int maxIndex = xUp;
            for(int i = xUp; i < xDown; i++) {
                if(A[i][midCol] > A[maxIndex][midCol])
                    maxIndex = i;
            }
            
            int curEle = A[maxIndex][midCol];
            int leftAheadEle = A[maxIndex][midCol-1];
            int rightAheadEle = A[maxIndex][midCol+1];
            if(leftAheadEle > curEle) {
                return find(xUp, xDown, yLeft, midCol-1, !flag, A);
            }
            else if(rightAheadEle > curEle) {
                return find(xUp, xDown, midCol+1, yRight, !flag, A);
            }
            else {
                return new ArrayList<Integer>(Arrays.asList(maxIndex, midCol));
            }
        }
    }
}
------------------------------------

Search in a Big Sorted Array
public class Solution {
    public int searchBigSortedArray(ArrayReader reader, int target) {
        int index = 1;
        while(reader.get(index-1) < target) index = index*2;
        int start = 0, end = index-1;
        while(start+1 < end) {
            int mid = start + (end-start)/2;
            if(reader.get(mid) == target) 
                end = mid;
            else if(reader.get(mid) < target) 
                start = mid;
            else
                end = mid;
        }
        if(reader.get(start) == target)
            return start;
        if(reader.get(end) == target)
            return end;
        return -1;
    }
}
------------------------------------

74. Search a 2D Matrix
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        if(matrix[0] == null || matrix[0].length == 0) return false;
        
        int row = matrix.length, col = matrix[0].length;
        int start = 0, end = row - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(matrix[mid][0] == target)
                return true;
            else if(matrix[mid][0] < target)
                start = mid;
            else
                end = mid;
        }
        
        // end mast be checked first
        if(matrix[end][0] <= target)
            row = end;
        else if(matrix[start][0] <= target)
            row = start;
        else
            return false;
            
        start = 0; 
        end = col-1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(matrix[row][mid] == target)
                return true;
            else if(matrix[row][mid] < target)
                start = mid;
            else
                end = mid;
        }
        
        if(matrix[row][start] == target)
            return true;
        else if(matrix[row][end] == target)
            return true;
        return false;
    }
}
------------------------------------

240. Search a 2D Matrix II
public class Solution {
    public int searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return 0;
        if(matrix[0] == null || matrix[0].length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int x = row-1, y = 0, count = 0;
        while(x >= 0 && y < col) {
            if(matrix[x][y] < target) {
                y++;
            }
            else if(matrix[x][y] > target) {
                x--;
            }
            else {
                count++;
                x--;
                y++;
            }
        }
        return count;
    }
}
------------------------------------

