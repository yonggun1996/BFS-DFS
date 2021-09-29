package com.company;

import java.io.*;
import java.util.*;

public class StartLink {

    static int f;//가장 높은 층
    static int u;//위로 올라가는 층
    static int d;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        System.out.println(search(s,g));
    }

    public static String search(int s, int g){
        //BFS로 현재 위치에서 올라가거나 내려가는 포인트를 큐에 담아 찾는 지점이 있으면 카운트를 반환
        Queue<ElevatorData> queue = new LinkedList<>();
        boolean[] visit = new boolean[f + 1];
        queue.offer(new ElevatorData(s, 0));

        while(!queue.isEmpty()){
            ElevatorData now_data = queue.poll();
            int now = now_data.now;
            int count = now_data.count;

            //이미 방문한 적이 있다는 것은 해당 층에서 바로 찾는 층까지 갈 수 없다는 뜻
            //방문한 적이 없다면 해당 층에서 올라갈 때, 내려갈 때를 담는다
            if(!visit[now]){
                if(now == g){//찾는 층이 나오면 반환
                    return String.valueOf(count);
                }

                visit[now] = true;
                int up = now + u;
                if(up <= f){//위로 올라갈 때 건물을 넘지 않는다면
                    queue.offer(new ElevatorData(up, count + 1));
                }

                int down = now - d;
                if(down >= 1){//아래로 내려갈 때 1층보다 낮지 않다면
                    queue.offer(new ElevatorData(down, count + 1));
                }
            }
        }

        return "use the stairs";
    }

}

class ElevatorData{
    int now;
    int count;

    public ElevatorData(int now, int count){
        this.now = now;
        this.count = count;
    }
}
