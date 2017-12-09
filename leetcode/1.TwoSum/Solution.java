class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}

class Client {
    public static void main(String[] args){
        int[] arr = new int[]{6,2,11,7};
        int target = 9;
        Solution test = new Solution();
        int[] result = test.twoSum(arr,target);
        System.out.printf("result: %d,%d", result[0], result[1]);
    }
}