package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GetZone {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[][] table = new boolean[m][n];

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            int x_start = Integer.parseInt(st.nextToken());//가로
            int y_start = Integer.parseInt(st.nextToken());//세로
            int x_end = Integer.parseInt(st.nextToken());//가로
            int y_end = Integer.parseInt(st.nextToken());//세로

            table = set_square(x_start,y_start,x_end,y_end,table);
        }

        int count = 0;
        ArrayList<Integer> areas = new ArrayList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!table[i][j]){
                    int area = bfs(i,j,table);
                    areas.add(area);
                    count++;
                }
            }
        }

        Collections.sort(areas);
        System.out.println(count);
        for(int i = 0; i < areas.size(); i++){
            System.out.print(areas.get(i) + " ");
        }
    }

    public static boolean[][] set_square(int x_start, int y_start, int x_end, int y_end, boolean[][] table){
        for(int i = y_start; i < y_end; i++){
            for(int j = x_start; j < x_end; j++){
                table[i][j] = true;
            }
        }

        return table;
    }

    public static int bfs(int x, int y, boolean[][] table){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x,y));
        table[x][y] = true;

        int count = 0;
        while(!queue.isEmpty()){
            Node nd = queue.poll();
            int now_x = nd.x;
            int now_y = nd.y;
            count++;

            if(now_x > 0 && !table[now_x - 1][now_y]){
                queue.offer(new Node(now_x - 1, now_y));
                table[now_x - 1][now_y] = true;
            }

            if(now_x < table.length - 1 && !table[now_x + 1][now_y]){
                queue.offer(new Node(now_x + 1, now_y));
                table[now_x + 1][now_y] = true;
            }

            if(now_y > 0 && !table[now_x][now_y - 1]){
                queue.offer(new Node(now_x, now_y - 1));
                table[now_x][now_y - 1] = true;
            }

            if(now_y < table[0].length - 1 && !table[now_x][now_y + 1]){
                queue.offer(new Node(now_x, now_y + 1));
                table[now_x][now_y + 1] = true;
            }
        }

        return count;
    }

}

class Node{
    int x;//세로
    int y;//가로

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
