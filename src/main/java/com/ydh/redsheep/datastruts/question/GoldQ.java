package com.ydh.redsheep.datastruts.question;

/**
 * 很久很久以前，有一位国王拥有5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人人数也不同。
 * 例如有的金矿储量是500kg黄金，需要5个工人来挖掘；有的金矿储量是200kg黄金，需要3个工人来挖掘……如果参与挖矿的工人的总数是10。
 * 每座金矿要么全挖，要么不挖，不能派出一半人挖取一半的金矿。要求用程序求出，要想得到尽可能多的黄金，应该选择挖取哪几座金矿？
 *
 * @author : yangdehong
 * @date : 2021/2/22 13:33
 */
public class GoldQ {

    public static void main(String[] args) {
        int[] values = {200, 200, 200, 500, 500};
        int[] weights = {3, 3, 3, 5, 5};
        int max = 10;
        System.out.println(deal(values, weights, max));
    }

    /**
     * 可以转成背包问题
     * 1、10个人最多挖3座200kg的或者2座500kg的，这里计算的时间复杂度O(KN)，K是金矿总数的类型
     * 2、然后就跟进背包问题求解
     * @param values
     * @param weights
     * @param max
     * @return
     */
    private static int deal(int[] values, int[] weights, int max) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || max <= 0) return 0;

        //dp数组  dp[i-1]  i从1开始
        int[][] dp = new int[values.length + 1][max + 1];

        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= max; j++) {
                //选择的物品超过最大承重
                int weight = weights[i - 1];
                if (weight > j) {
                    //不能选该物品 等于上轮的最大价值
                    dp[i][j] = dp[i - 1][j];
                }
                //选择的物品不超过最大承重
                else {
                    //上轮的最大价值
                    int proValue = dp[i - 1][j];
                    //选择该商品后的最大价值
                    int curValue = values[i - 1] + dp[i - 1][j - weight];
                    //两者取最大值
                    dp[i][j] = Math.max(proValue, curValue);
                }

            }
        }
        return dp[values.length][max];
    }

}
