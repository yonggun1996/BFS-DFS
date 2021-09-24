package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cheese {

    static Queue<Location> remove_queue = new LinkedList<>();//치즈를 살피면서 삭제해야할 노드를 담는 큐
    static String[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        board = new String[n][m];

        int time = 0;
        int before = 0;
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                board[i][j] = st.nextToken();
                if(board[i][j].equals("1")) before++;//치즈가 있는 구역의 수를 누적
            }
        }

        while(search()){
            before = remove_queue.size();//치즈의 개수를 초기화
            time++;//루프문을 도는 횟수

            while(!remove_queue.isEmpty()){
                Location location = remove_queue.poll();
                board[location.n][location.m] = "0";
            }
        }

        System.out.println(time);
        System.out.println(before);
    }

    //BFS로 치즈가 없는 구간(0)을 파악하고 주변에 "1"이 있으면 remove큐에 담는다
    public static boolean search(){
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(0, 0));
        boolean[][] visit = new boolean[board.length][board[0].length];
        visit[0][0] = true;

        while(!queue.isEmpty()){
            Location l = queue.poll();
            int n = l.n;
            int m = l.m;

            if (n > 0 && !visit[n - 1][m]) {
                if (board[n - 1][m].equals("1")) {//위 부분이 치즈라면
                    remove_queue.offer(new Location(n - 1, m));
                }else{//그렇지 않을 경우
                    queue.offer(new Location(n - 1, m));
                }
                visit[n - 1][m] = true;
            }

            if (n < board.length - 1 && !visit[n + 1][m]) {
                if (board[n + 1][m].equals("1")) {
                    remove_queue.offer(new Location(n + 1, m));
                }else{
                    queue.offer(new Location(n + 1, m));
                }
                visit[n + 1][m] = true;
            }

            if (m > 0 && !visit[n][m - 1]) {
                if (board[n][m - 1].equals("1")) {
                    remove_queue.offer(new Location(n, m - 1));
                }else{
                    queue.offer(new Location(n, m - 1));
                }
                visit[n][m - 1] = true;
            }

            if (m < board[0].length - 1 && !visit[n][m + 1]) {
                if (board[n][m + 1].equals("1")) {
                    remove_queue.offer(new Location(n, m + 1));
                }else{
                    queue.offer(new Location(n, m + 1));
                }
                visit[n][m + 1] = true;
            }
        }

        if(remove_queue.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}

class Location{
    int n;
    int m;

    public Location(int n, int m){
        this.n = n;
        this.m = m;
    }
}
