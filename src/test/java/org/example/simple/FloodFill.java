package org.example.simple;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * You are given an image represented by an m x n grid of integers image, where image[i][j] 
 * represents the pixel value of the image. You are also given three integers sr, sc, and color. 
 * Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
 * To perform a flood fill:
 * 
 * Begin with the starting pixel and change its color to color.
 * Perform the same process for each pixel that is directly adjacent 
 * (pixels that share a side with the original pixel, either horizontally or vertically) 
 * and shares the same color as the starting pixel.
 * Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
 * The process stops when there are no more adjacent pixels of the original color to update.
 * Return the modified image after performing the flood fill.
 * 
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image with position 
 * (sr, sc) = (1, 1) (i.e., the red pixel), 
 * all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) 
 * are colored with the new color.
 * 
 * Note the bottom corner is not colored 2, because it is not horizontally or 
 * vertically connected to the starting pixel.
 * 
 * Example 2:
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation:
 * The starting pixel is already colored with 0, which is the same as the target color. 
 * Therefore, no changes are made to the image.
 */
public class FloodFill {

    public static void main(String[] args) {
        int[][] img = {
        //   0  1  2  3  4  5  6  7
            {0, 0, 0, 0, 0, 1, 1, 1}, // 0
            {0, 0, 0, 1, 0, 1, 1, 1}, // 1
            {1, 0, 0, 0, 0, 1, 1, 1}, // 2
            {1, 0, 0, 0, 0, 1, 1, 1}, // 3
            {0, 0, 1, 1, 1, 1, 1, 1}, // 4
            {0, 1, 0, 0, 1, 0, 0, 0}, // 5
            {0, 0, 0, 0, 1, 0, 0, 0}, // 6
            {0, 0, 0, 0, 1, 1, 1, 1}, // 7
            {0, 1, 0, 0, 1, 0, 0, 0}, // 8
            {0, 1, 1, 1, 1, 0, 0, 0}  // 9
        };

        FloodFill.prettyPrint(img);

        var result = new FloodFill().floodFill(img, 0, 7, 2);
        FloodFill.prettyPrint(result);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        var queue = new ArrayDeque<Point>();
        var sourceColor = image[sr][sc];
        image[sr][sc] = color;
        if (sourceColor != color)
            queue.add(new Point(sc, sr));

        while (!queue.isEmpty()) {
            var nextPoint = queue.pop();
            // left
            paint(nextPoint.y, nextPoint.x - 1, image, sourceColor, color, queue);
            // up
            paint(nextPoint.y - 1, nextPoint.x, image, sourceColor, color, queue);
            // right
            paint(nextPoint.y, nextPoint.x + 1, image, sourceColor, color, queue);
            // down
            paint(nextPoint.y + 1, nextPoint.x, image, sourceColor, color, queue);

        }
        return image;   
    }

    private void paint(int sr, int sc, int[][] image, 
            int sourceColor, int targetColor, Queue<Point> queue) {
        var bound = sr >= 0 && sc >= 0 && sr < image.length && sc < image[sr].length;
        if (bound && sourceColor == image[sr][sc]) {
            image[sr][sc] = targetColor;
            queue.add(new Point(sc, sr));
        }
    }

    private static class Point {
        final int x; 
        final int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void prettyPrint(int[][] img) {
        for (var i = 0; i < img.length; i++) {
            for (var j = 0; j < img[i].length; j++) {
                System.out.print(String.format("\u001B[48;5;%dm   \u001B[0m", img[i][j], img[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}
