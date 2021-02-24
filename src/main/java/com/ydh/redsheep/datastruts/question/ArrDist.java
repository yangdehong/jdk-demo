package com.ydh.redsheep.datastruts.question;

/**
* 判断数组中所有的数字是否只出现一次。给定一个数组array，判断数组array中是否所有的数字都只出现过一次。例如，arr = {1, 2, 3}，
 * 输出YES。又如，arr = {1, 2, 1}，输出NO。约束时间复杂度为O(n)。
* @author : yangdehong
* @date : 2021/2/22 13:32
*/
public class ArrDist {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 1};
//        int[] arr = {1, 2, 12,43,34,12,34,124,67,54,34,54,12};
        int[] arr = {1, 2, 3,4,5,6,7,8,9,11,12,13,14};
        deal(arr);
    }

    /**
     * 1、排序，快速排序时间复杂度O(logn)
     * 2、两两比较，从第二个开始，遍历一遍就好，时间复杂度是O(N-1)
     * 3、总的时间复杂度O(logn)+O(N-1) 约等于 O(N)
     * @param arr
     */
    private static void deal(int[] arr) {
        // 快速排序
        quick(arr, 0, arr.length-1);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] == arr[i]) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    //递归排序
    public static void quick(int[] arr, int start, int end) {
        //递归结束
        if (start >= end) {
            return;
        }
        //获得基准元素位置
        int pivot = pivot(arr, start, end);
        //前半段
        quick(arr, start, pivot - 1);
        //后半段
        quick(arr, pivot + 1, end);

    }

    /**
     * 返回基准位置
     *
     * @return
     */
    private static int pivot(int[] arr, int start, int end) {
        //取基准元素 第一个
        int pivot = arr[start];
        int left = start;
        int right = end;

        while (left != right) {
            //右指针左移
            while (left < right && arr[right] > pivot) {
                right--;
            }
            //左指针右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 左右交换
            if (left < right) {
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
            }
        }
        //交换pivot和重合点
        arr[start] = arr[left];
        arr[left] = pivot;
        return left;
    }

}
