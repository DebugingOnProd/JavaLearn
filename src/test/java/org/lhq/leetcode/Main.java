package org.lhq.leetcode;

import org.junit.jupiter.api.Test;


/**
 *
 *
 *
 * 二维平面上两个由直线构成且边与坐标轴平行/垂直的矩形，请返回两个矩形覆盖的总面积。
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：:
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 * 其中，-10的4次方 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10的4次方
 *
 * 示例1：
 * 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * 输出：45
 * 示例2：
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * 输出：16
 *
 * public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
 * }
 *
 *
 */
public class Main {

    @Test
    public void test(){
        int area = computeArea(-2, -2, 2, 2, -1, 4, 1, 6);
        System.out.println(area);
        int area2 = computeArea(-3, -0, 3, 4, -0, -1, 9, 2);
        System.out.println(area2);
    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

        int area1 = (ax2 - ax1)  * (ay2-ay1);
        int area2 = (bx2-bx1) * (by2-by1);

        //计算相交部分的坐标
        int cx1 = Math.max(ax1,bx1);
        int cx2 = Math.min(ax2,bx2);
        int cy1 = Math.max(ay1,by1);
        int cy2 = Math.min(ay2,by2);

        if (cx1>=cx2 || cy1>=cy2){
            return area1+area2;
        }else {
            int area3 = (cx2-cx1) * (cy2-cy1);
            return (area1-area3) + area2;
        }





    }



}
