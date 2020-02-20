package com.neuedu;

import java.util.Arrays;

/**
 * Created by heystephen on 2020/2/20.
 */
public class Select {
    public static void main(String[] args) {
        //相邻两个数互相比较大小 然后交换位置
        int[] array = {5, 1, 90, 38, 47, 20, 2, 100, 77, 65, 50};
        for (int i = 0; i < array.length - 1; i++) {
            int flag = 0;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag++;
                }
            }
            if (flag == 0) {
                break;
            }
        }
        for (int a : array) {
            System.out.println(a);
        }
    }
}