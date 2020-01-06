package how2j.algorithm;

import java.util.Scanner;

/**
 * @author chenxin
 * @date 2020/1/3 15:15
 */
public class Test1 {

    public static String blank = " ";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] nums = line.split(blank);
        int num = Integer.valueOf(nums[0]);


        int[] array = new int[1001];

        if (nums.length > num) {
            for (int i = 1; i <= num; i++) {
                int temp = Integer.valueOf(nums[i]);
                array[temp] = 1;
            }
        } else {
            for (int i = 1; i < nums.length; i++) {
                int temp = Integer.valueOf(nums[i]);
                array[temp] = 1;
            }
            int count = nums.length - 1;
            while (count < num) {
                nums = scanner.nextLine().split(blank);
                for (int i = 0; i < nums.length && count <= num; i++) {

                    int temp = Integer.valueOf(nums[i]);
                    array[temp] = 1;
                    count++;
                }
            }
        }


        for (int i = 1; i <= 1000; i++) {
            if (array[i] == 1) {
                System.out.println(i + blank);
            }
        }

    }


}
