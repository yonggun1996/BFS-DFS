package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
주어진 정수를 2진수로 변환을 한 후 BFS로 방의 개수와 방의 넓이들을 파악한다
이 때 각각의 구역과 넓이를 배열에 담습니다.
BFS로 다 살펴본 후 이중for문을 통해 벽을 하나씩 부십니다. 그리고 인접한 지역에 구역이 다르면 두 값을 더해 최대값을 갱신합니다.
 */

public class No2234 {

    static int answer1 = 0;//BFS 메서드를 호출할 때 마다 늘어나는 방의 개수
    static int answer2 = 0;//BFS로 방의 널이를 확인한 후 최대값을 갱신하는 방의 최대 넓이
    static int answer3;//벽을 하나 뚫었을 때 가장 넓은 넓이
    static String[][] castle;//입력받은 정수를 bit로 반환해 저장하는 배열
    static boolean[][] visit;//해당 방을 방문한 적 있는지 확인하는 배열
    static int[][] arr;//해당 방의 넓이
    static int[][] roomNum_Arr;//해당 방을 몇 번째에 확인했는지 담는 배열
    static Queue<Room> visit_Room = new LinkedList<>();//방문한 방을 담는 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        castle = new String[m][n];
        visit = new boolean[m][n];
        arr = new int[m][n];
        roomNum_Arr = new int[m][n];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                castle[i][j] = get_bit(num);//입력받은 정수를 bit로 표현해 저장
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visit[i][j]){
                    answer1++;//방 하나씩 확인할 때 마다 증가
                    search(i, j);
                }
            }
        }

        answer3 = answer2;
        get_Ans3();
        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
    }

    //벽을 하나 없앴을 때 넓은 방의 넓이를 구하는 메서드
    static void get_Ans3(){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                String bit = castle[i][j];

                //남쪽에 벽이 있고 같은 구역이 아니라면 합쳐서 최대값을 구한다
                if(bit.charAt(0) == '1' && i + 1 < visit.length && roomNum_Arr[i][j] != roomNum_Arr[i + 1][j]){
                    answer3 = Math.max(answer3, arr[i][j] + arr[i + 1][j]);
                }

                //동쪽에 벽이 있고 같은 구역이 아니라면 합쳐서 최대값을 구한다
                if(bit.charAt(1) == '1' && j + 1 < visit[0].length && roomNum_Arr[i][j] != roomNum_Arr[i][j + 1]){
                    answer3 = Math.max(answer3, arr[i][j] + arr[i][j + 1]);
                }

                //북쪽에 벽이 있고 같은 구역이 아니라면 합쳐서 최대값을 구한다
                if(bit.charAt(2) == '1' && i - 1 >= 0 && roomNum_Arr[i][j] != roomNum_Arr[i - 1][j]){
                    answer3 = Math.max(answer3, arr[i][j] + arr[i - 1][j]);
                }

                //서쪽에 벽이 있고 같은 구역이 아니라면 합쳐서 최대값을 구한다
                if(bit.charAt(3) == '1' && j - 1 >= 0 && roomNum_Arr[i][j] != roomNum_Arr[i][j - 1]){
                    answer3 = Math.max(answer3, arr[i][j] + arr[i][j - 1]);
                }
            }
        }
    }

    //정수를 2진수로 변환하는 메서드
    static String get_bit(int num){
        StringBuilder sb = new StringBuilder("0000");
        for(int i = 3; i >= 0; i--){
            if(num % 2 == 1){
                sb.replace(i, i + 1, "1");
            }
            num /= 2;
        }

        return sb.toString();
    }

    //BFS로 해당 위치부터 방의 넓이를 확인하는 메서드
    static void search(int x, int y){
        int count = 1;
        Queue<Room> queue = new LinkedList<>();
        queue.offer(new Room(x, y));
        visit_Room.offer(new Room(x, y));
        visit[x][y] = true;

        while(!queue.isEmpty()){
            Room roomdata = queue.poll();
            int rdx = roomdata.x;
            int rdy = roomdata.y;

            String bit = castle[rdx][rdy];
            //남쪽으로 갈 수 있는 경우
            if(bit.charAt(0) == '0' && rdx + 1 < visit.length && !visit[rdx + 1][rdy]){
                count++;
                queue.offer(new Room(rdx + 1, rdy));
                visit_Room.offer(new Room(rdx + 1, rdy));
                visit[rdx + 1][rdy] = true;
            }

            //동쪽으로 갈 수 있는 경우
            if(bit.charAt(1) == '0' && rdy + 1 < visit[0].length && !visit[rdx][rdy + 1]){
                count++;
                queue.offer(new Room(rdx, rdy + 1));
                visit_Room.offer(new Room(rdx, rdy + 1));
                visit[rdx][rdy + 1] = true;
            }

            //북쪽으로 갈 수 있는 경우
            if(bit.charAt(2) == '0' && rdx - 1 >= 0 && !visit[rdx - 1][rdy]){
                count++;
                queue.offer(new Room(rdx - 1, rdy));
                visit_Room.offer(new Room(rdx - 1, rdy));
                visit[rdx - 1][rdy] = true;
            }

            //서쪽으로 갈 수 있는 경우
            if(bit.charAt(3) == '0' && rdy - 1 >= 0 && !visit[rdx][rdy - 1]){
                count++;
                queue.offer(new Room(rdx, rdy - 1));
                visit_Room.offer(new Room(rdx, rdy - 1));
                visit[rdx][rdy - 1] = true;
            }
        }

        //여태 방문했던 방에 전체 방의 넓이와 방을 몇 번째에 방문했는지 기록한다
        while(!visit_Room.isEmpty()){
            Room room = visit_Room.poll();
            int rx = room.x;
            int ry = room.y;

            arr[rx][ry] = count;
            roomNum_Arr[rx][ry] = answer1;
        }

        //가장 넓은 방의 넓이를 갱신한다
        answer2 = Math.max(answer2, count);
    }
}

class Room{
    int x;
    int y;

    public Room(int x, int y){
        this.x = x;
        this.y = y;
    }
}
