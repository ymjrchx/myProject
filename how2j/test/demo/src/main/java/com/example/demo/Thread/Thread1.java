package com.example.demo.Thread;

/**
 * @author chenxin
 * @date 2019/10/9 16:16
 */

public class Thread1 {
    public static void main(String[] args) {
        Runnable runnable = ()->{
          throw new IllegalArgumentException("ddddd");
        };
        try{
            new Thread(runnable).start();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println("dgege");
            e.printStackTrace();
        }


    }
}
