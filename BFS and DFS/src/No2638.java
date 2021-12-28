package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
치즈가 있는 부분을 큐에 저장
이번 문제는 외부공기에만 닿을 때 치즈가 녹는다. 그래서 외부공기는 2로 저장
가장자리는 치즈가 없기 때문에 0,0부터 BFS로 외부공기 초기 설정
치즈를 담은 큐를 통해 외부 공기(map에서는 2)가 2개 이상이면 삭제 큐에 담는다
그렇지 않으면 다시 치즈 큐에 담는다
삭제된 치즈는 외부공기가 되면서 주변에 내부공기(map에서는 0)가 있다면 BFS로 외부공기로 변환
이 과정을 모두 거쳐 시간을 1씩 더하고 치즈 큐에 데이터가 없을 때 까지 계속 반복
 */

public class No2638 {

    static int[][] map;//입력받은 치즈와 공기
    static int n, m;
    static int[] x_Arr = {-1,1,0,0};
    static int[] y_Arr = {0,0,-1,1};
    static int time = 0;//정담이 될 치즈가 녹는 시간
    static Queue<CheeseSpace> cheese_Queue = new LinkedList<>();//치즈 구간을 담는 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){//치즈부분은 치즈 구간을 담는 큐에 담는다
                    cheese_Queue.offer(new CheeseSpace(i,j));
                }
            }
        }

        BFS(new CheeseSpace(0,0));//치즈가 안생기는 0,0부터 탐색을 해본다
        Queue<CheeseSpace> delete_Queue = new LinkedList<>();//녹는 치즈를 담는 큐
        while(!cheese_Queue.isEmpty()){

            int size = cheese_Queue.size();//남아 있는 치즈를 전부 살펴본다
            for(int i = 0; i < size; i++){
                CheeseSpace cs = cheese_Queue.poll();
                int x = cs.x;
                int y = cs.y;

                int cnt = 0;
                for(int j = 0; j < 4; j++){
                    int nx = x + x_Arr[j];
                    int ny = y + y_Arr[j];

                    //상하좌우중 범위 내에 있으며 외부공기일 경우 cnt를 늘린다
                    if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 2){
                        cnt++;
                    }
                }

                if(cnt >= 2){//외부공기가 2개 이상일 경우 삭제 큐에 담는다
                    delete_Queue.offer(new CheeseSpace(x,y));
                }else{//치즈가 아직 안녹았으면 다시 큐에 넣는다
                    cheese_Queue.offer(cs);
                }
            }

            while(!delete_Queue.isEmpty()){
                CheeseSpace cs = delete_Queue.poll();
                int x = cs.x;
                int y = cs.y;

                map[x][y] = 2;//삭제된 치즈는 외부공기가 된다
                for(int i = 0; i < 4; i++){
                    int nx = x + x_Arr[i];
                    int ny = y + y_Arr[i];

                    //치즈가 녹았는데 내부 공기가 있다면 BFS를 통해 다시 바꿔준다
                    if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0){
                        BFS(cs);
                    }
                }
            }

            time++;//치즈가 외부공기에 녹았으면 시간을 늘린다
        }

        System.out.println(time);
    }

    //BFS를 하는 메서드
    static void BFS(CheeseSpace ch){
        Queue<CheeseSpace> queue = new LinkedList<>();
        queue.offer(ch);
        map[ch.x][ch.y] = 2;

        while(!queue.isEmpty()){
            CheeseSpace c = queue.poll();
            int x = c.x;
            int y = c.y;

            for(int i = 0; i < 4; i++){
                int nx = x + x_Arr[i];
                int ny = y + y_Arr[i];

                //아직 내.외부 정하지 않은 공기거나 내부공기일 경우 외부공기로 변환
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0){
                    map[nx][ny] = 2;
                    queue.offer(new CheeseSpace(nx, ny));
                }
            }
        }
    }
}

class CheeseSpace{
    int x;
    int y;

    public CheeseSpace(int x, int y){
        this.x = x;
        this.y = y;
    }
}