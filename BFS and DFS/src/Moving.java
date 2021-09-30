package com.company;
import java.util.*;
import java.io.*;
public class Moving {

    static int[][] map_Arr;//각 나라의 인구수를 담은 배열
    static boolean[][] visit;//해당 나라를 방문했는지 확인하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        map_Arr = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                map_Arr[i][j] = num;
            }
        }

        int answer = 0;
        while(true){
            boolean flag = false;
            visit = new boolean[n][n];

            //이중for문을 돌리면서 모든 지점에 대한 이동 가능한지 체크
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!visit[i][j]){//BFS로 방문한 이력이 이미 있다면 더 살피지 않는다
                        boolean b = BFS(i,j,l,r);
                        if(b) flag = true;
                    }
                }
            }

            if(!flag) break;//이동할 수 있는 나라가 없으면 탐색을 멈툰다

            answer++;
        }

        System.out.println(answer);
    }

    public static boolean BFS(int i, int j, int l, int r){
        boolean flag = false;
        Queue<WorldData> search_queue = new LinkedList<>();//탐색할 큐
        Queue<WorldData> location_queue = new LinkedList<>();//값을 넣기 위해 기존 경로들을 담은 큐

        search_queue.offer(new WorldData(i,j));
        location_queue.offer(new WorldData(i,j));
        visit[i][j] = true;

        int num = 0;//각 나라의 인구 합
        int count = 0;//연결된 나라의 수
        while(!search_queue.isEmpty()){
            WorldData wd = search_queue.poll();
            int x = wd.x;
            int y = wd.y;

            int now_result = map_Arr[x][y];
            num += now_result;
            count++;

            //윗 부분을 탐색
            if(x > 0 && !visit[x - 1][y]){
                int next_result = map_Arr[x - 1][y];
                int result = Math.abs(next_result - now_result);
                if(result >= l && result <= r){
                    flag = true;
                    search_queue.offer(new WorldData(x - 1, y));
                    location_queue.offer(new WorldData(x - 1, y));
                    visit[x - 1][y] = true;
                }
            }

            //아래부분 탐색
            if(x < map_Arr.length - 1 && !visit[x + 1][y]){
                int next_result = map_Arr[x + 1][y];
                int result = Math.abs(next_result - now_result);
                if(result >= l && result <= r){
                    flag = true;
                    search_queue.offer(new WorldData(x + 1, y));
                    location_queue.offer(new WorldData(x + 1, y));
                    visit[x + 1][y] = true;
                }
            }

            //왼쪽 탐색
            if(y > 0 && !visit[x][y - 1]){
                int next_result = map_Arr[x][y - 1];
                int result = Math.abs(next_result - now_result);
                if(result >= l && result <= r){
                    flag = true;
                    search_queue.offer(new WorldData(x, y - 1));
                    location_queue.offer(new WorldData(x, y - 1));
                    visit[x][y - 1] = true;
                }
            }

            //오른쪽 탐색
            if(y < map_Arr[0].length - 1 && !visit[x][y + 1]){
                int next_result = map_Arr[x][y + 1];
                int result = Math.abs(next_result - now_result);
                if(result >= l && result <= r){
                    flag = true;
                    search_queue.offer(new WorldData(x, y + 1));
                    location_queue.offer(new WorldData(x, y + 1));
                    visit[x][y + 1] = true;
                }
            }
        }

        int add = num / count;
        while(!location_queue.isEmpty()){//경로들을 담은 큐를 하나씩 꺼내 평균값을 기록한다
            WorldData wd = location_queue.poll();
            int x = wd.x;
            int y = wd.y;
            map_Arr[x][y] = add;
        }

        return flag;//다른 지역을 방문했다면 true가 되고 그렇지 않으면 false를 반환한다
    }
}

class WorldData{
    int x;
    int y;

    public WorldData(int x, int y){
        this.x=x;
        this.y=y;
    }
}
