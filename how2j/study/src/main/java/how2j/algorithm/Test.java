package how2j.algorithm;

import java.util.Scanner;

/**
 * @author chenxin
 * @date 2020/1/3 14:54
 */
public class Test {


    public static int getNum(int num) {
        if (num <= 1) {
            return 0;
        }
        if (num == 2) {
            return 1;
        }
        int num1 = num / 3;
        return num1 + getNum(num1 + (num % 3));
    }

        public static void  main(String[] args){
            Scanner scanner = new Scanner(System.in);
            int nextNum = scanner.nextInt();
            System.out.println(getNum(nextNum));
        }
}
