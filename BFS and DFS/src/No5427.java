package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
처음에는 불이 번지는 시간을 BFS, 각 출구까지 도달하는 시간을 DFS로 구현했는데 이는 시간초과라는 결과가 나왔다.
그래서 두 가지 경우 모두 BFS를 이용해 시간초과를 면했다.
불이 번지는 시간을 BFS로 구하고
사람이 각 구역을 갈 수 있는 최소시간을 구한다.
불이 번지는 시간과 사람이 이동하는 시간을 비교해 불보다 빨리 왔으면 그 구간은 이동 가능한 구간이 된다.
 */

public class No5427 {

    static boolean[][] visit;//탈출을 할 때 방문했던 구간
    static int count = Integer.MAX_VALUE;//출구로 빠져나갈 수 있는 최소 시가
    static Queue<FireData> queue = new LinkedList<>();//불이 번지는 것을 BFS로 탐색하기 위해 선언
    static int[][] second;//불이 각 구역으로 번지는데 걸리는 시간
    static char[][] map;//입력받은 지도

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testcase; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            //2를 더한 이유는 가로축이나 세로축이 0인 공간으로 이동하면 탈줄했다는 것을 구현하기 위해 이러한 방식으로 선언
            second = new int[h + 2][w + 2];
            map = new char[h + 2][w + 2];
            visit = new boolean[h + 2][w + 2];
            FireData start = null;//탈출 시작지점('@'이 나오는 시점)

            for(int j = 1; j <= h; j++){
                String s = br.readLine();
                for(int k = 1; k <= w; k++){
                    map[j][k] = s.charAt(k - 1);

                    if(map[j][k] == '#'){//벽은 시간을 체크할 수 없다
                        second[j][k] = -1;
                    }

                    if(map[j][k] == '*'){//불이 시작되는 곳, 1초로 지정
                        queue.offer(new FireData(j,k));
                        second[j][k] = 1;
                    }

                    if(map[j][k] == '@'){//탈출 시작 지점
                        start = new FireData(j, k);
                        visit[j][k] = true;
                    }
                }
            }

            set_second();
            get_second(start);

            if(count < Integer.MAX_VALUE){//최소시간을 구했으므로 시간을 출력하고 다시 MAXVALUE로 선언
                System.out.println(count);
                count = Integer.MAX_VALUE;
            }else{//탈출을 못했으므로 IMPOSSIBLE 출력
                System.out.println("IMPOSSIBLE");
            }

            queue.clear();
        }
    }

    public static void get_second(FireData start){
        Queue<Counting> exitqueue = new LinkedList<>();
        exitqueue.offer(new Counting(start, 1));

        while(!exitqueue.isEmpty()){
            Counting c = exitqueue.poll();
            int x = c.now.x;
            int y = c.now.y;
            int time = c.time;

            if(map[x][y] == '\u0000'){//문자가 아무것도 없는 구간은 건물 바깥구간으로 탈출 성공
                count = Math.min(count, time - 1);//탈출하는데 걸린 시간을 기존 시간과 비교(연산을 편안하게 하기 위해 1을 더했기 때문에 설정할 때는 time을 1 뺀다
                continue;
            }

            if(x > 0 && !visit[x - 1][y] && map[x - 1][y] != '#'){//위로 갈 수 있으며 방문안했고, 벽이 아닌 경우
                if(second[x - 1][y] == 0 || second[x - 1][y] > time + 1){//탈출구거나 불이 번지기 전에 도달 가능한 경우
                    visit[x - 1][y] = true;
                    exitqueue.offer(new Counting(new FireData(x - 1, y), time + 1));
                }
            }

            if(x < second.length - 1 && !visit[x + 1][y] && map[x + 1][y] != '#'){//아래로 갈 수 있는 경우
                if(second[x + 1][y] == 0 || second[x + 1][y] > time + 1){
                    visit[x + 1][y] = true;
                    exitqueue.offer(new Counting(new FireData(x + 1, y), time + 1));
                }
            }

            if(y > 0 && !visit[x][y - 1] && map[x][y - 1] != '#'){//왼쪽으로 갈 수 있는 경우
                if(second[x][y - 1] == 0 || second[x][y - 1] > time + 1){
                    visit[x][y - 1] = true;
                    exitqueue.offer(new Counting(new FireData(x, y - 1), time + 1));
                }
            }

            if(y < second[0].length - 1 && !visit[x][y + 1] && map[x][y + 1] != '#'){//오른쪽으로 갈 수 있는 경우
                if(second[x][y + 1] == 0 || second[x][y + 1] > time + 1){
                    visit[x][y + 1] = true;
                    exitqueue.offer(new Counting(new FireData(x, y + 1), time + 1));
                }
            }
        }
    }

    //불이 각 구역으로 번지는데 걸리는 시간
    public static void set_second(){
        while(!queue.isEmpty()){
            FireData fd = queue.poll();
            int x = fd.x;
            int y = fd.y;
            int s = second[x][y];//현위치까지 번지는데 걸린 시간

            //건물 바깥 구역으로 가지 않게 연산되어야 한다.
            if(x > 1 && second[x - 1][y] == 0){//위로 갈 수 있으며 벽이 아닌 경우
                second[x - 1][y] = s + 1;
                queue.offer(new FireData(x - 1, y));
            }

            if(x < second.length - 2 && second[x + 1][y] == 0){//아래로 갈 수 있으며 벽이 아닌 경우
                second[x + 1][y] = s + 1;
                queue.offer(new FireData(x + 1, y));
            }

            if(y > 1 && second[x][y - 1] == 0){//왼쪽으로 갈 수 있으며 벽이 아닌 경우
                second[x][y - 1] = s + 1;
                queue.offer(new FireData(x, y - 1));
            }

            if(y < second[0].length - 2 && second[x][y + 1] == 0){//오른쪽으로 갈 수 있으며 벽이 아닌 경우
                second[x][y + 1] = s + 1;
                queue.offer(new FireData(x, y + 1));
            }
        }
    }
}

class FireData{
    int x;
    int y;

    public FireData(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Counting{
    FireData now;
    int time;

    public Counting(FireData now, int time){
        this.now = now;
        this.time = time;
    }
}

