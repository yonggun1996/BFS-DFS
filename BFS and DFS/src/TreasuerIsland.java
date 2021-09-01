package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreasuerIsland {

    /*
    육지 내에서 최단 경로 중 가장 긴 거리를 구하는 문제
    BFS를 이용해서 풀었다.
    for문을 돌면서 모든 육지 지역을 확인하면서 해당 위치 중 가장 긴 거리를 구했다.
     */
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        map = new char[x][y];
        for(int i = 0; i < x; i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j = 0; j < y; j++){
                map[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(map[i][j] == 'L'){//육지지역이면 해당 지역의 제일 길이가 긴 최단경로를 찾는다
                    int short_dist = bfs(i,j);
                    answer = Math.max(answer, short_dist);//찾은 경로들 중 가장 긴 부분이 정답이다.
                }
            }
        }

        System.out.println(answer);
    }

    public static int bfs(int x, int y){
        Queue<IslandNode> queue = new LinkedList<>();
        queue.offer(new IslandNode(x,y,1));
        int[][] visit = new int[map.length][map[0].length];//각 지역의 길이

        for(int i = 0; i < visit.length; i++){//전부 최대값으로 설정
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }

        visit[x][y] = 0;//시작하는 지역은 0으로 설정
        int max = 0;//시작지역에서 최대 최단거리를 설정하는 변수
        while (!queue.isEmpty()){
            IslandNode in = queue.poll();

            int now_x = in.x;
            int now_y = in.y;
            int dist = in.dist;

            if(max < dist){//거쳐온 거리가 기존에 저장한 최대거리보다 클 경우 수정
                max = dist;
            }

            //위로 향할 수 있으면서, 육지이고, 저장한 거리 보다 새로 나아갈 거리가 작으면 큐에 넣고 BFS를 한다
            if(now_x > 0 && map[now_x - 1][now_y] == 'L' && visit[now_x - 1][now_y] > dist + 1){
                queue.offer(new IslandNode(now_x - 1, now_y, dist + 1));
                visit[now_x - 1][now_y] = dist + 1;
            }

            //아래 부분
            if(now_x < map.length - 1 && map[now_x + 1][now_y] == 'L' && visit[now_x + 1][now_y] > dist + 1){
                queue.offer(new IslandNode(now_x + 1, now_y, dist + 1));
                visit[now_x + 1][now_y] = dist + 1;
            }

            //왼쪽부분
            if(now_y > 0 && map[now_x][now_y - 1] == 'L' && visit[now_x][now_y - 1] > dist + 1){
                queue.offer(new IslandNode(now_x, now_y - 1, dist + 1));
                visit[now_x][now_y - 1] = dist + 1;
            }

            //오른쪽 부분분
           if(now_y < map[0].length - 1 && map[now_x][now_y + 1] == 'L' && visit[now_x][now_y + 1] > dist + 1){
                queue.offer(new IslandNode(now_x, now_y + 1, dist + 1));
                visit[now_x][now_y + 1] = dist + 1;
            }
        }

        return max - 1;
    }

}

class IslandNode{
    int x;
    int y;
    int dist;

    public IslandNode(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}
