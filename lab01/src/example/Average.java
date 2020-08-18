package example;

public class Average {
        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public float computeAverage(int[] nums) {
            float result = 0;
            // Add your code
            for (int i = 0; i < nums.length; i++) {
                result += nums[i];
            }
            result /= nums.length;
            return result;
        }

        public static void main(String[] args) {
            // Add your code
            int[] nums = {1, 2, 3};
            Average a = new Average();
            float avg = a.computeAverage(nums);
            System.out.printf("The average is %.1f", avg);
        }
}
