package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
숫자가 낮은 배열칸으로 계속 이동할 때 경로의 개수를 파악하는 문제
단순히 BFS로만 이용했을 때 메모리 초과가 나왔다.
다이나믹 프로그래밍을 이용해 이미 거쳤던 구역은 오른쪽 아래까지 갈 수 있기에 경로에 포함하는 방식
도움이 된 블로그 : https://lotuslee.tistory.com/9
거치지 않은 구역은 -1, 거친 구역은 0, 해당 위치에서 갈 수 있는 경로를 그 외 숫자로 담았다.
 */

public class No1520 {

    static int[][] arr;
    static int[][] board;
    static int[] dist_up = {-1,1,0,0};
    static int[] dist_down = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());//세로
        int n = Integer.parseInt(st.nextToken());//가로
        arr = new int[m][n];
        board = new int[m][n];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = -1;
            }
        }

        int answer = search(0,0);
        System.out.println(answer);
    }

    static int search(int x, int y){
        arr[x][y] = 0;//우선 해둥 구역을 거쳤음을 체크
        if(x == board.length - 1 && y == board[0].length - 1){//맨 오른쪽 아래로 올 경우
            arr[x][y] += 1;//해당 위치에 경로 개수를 늘린다
        }else{
            for(int i = 0; i < 4; i++){//상하좌우로 이동하는 for문
                int nx = x + dist_up[i];
                int ny = y + dist_down[i];

                if(nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length){//이동할 수 있는 거리라면
                    if(board[x][y] > board[nx][ny]){//낮은 지역으로 갈 수 있다면
                        if(arr[nx][ny] >= 0){//이미 거친 구역이라면 현재 지역으로 경로 개수 추가
                            arr[x][y] += arr[nx][ny];
                        }else{//그렇지 않은 경우
                            arr[x][y] += search(nx, ny);//재귀함수를 호출해 반환된 값을 더해 경로 개수 초기화
                        }
                    }
                }
            }

        }
        return arr[x][y];
    }

}

class BoardData{
    int m;//세로
    int n;//가로
    int dist;

    public BoardData(int m, int n, int dist){
        this.m = m;
        this.n = n;
        this.dist = dist;
    }
}
