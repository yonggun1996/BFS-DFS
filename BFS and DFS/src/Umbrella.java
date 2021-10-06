package Avatar;
import java.io.*;
import java.util.*;

public class Umbrella {

    static char[][] map;
    static ArrayList<NodeData> nodelist;//물건의 위치를 담은 리스트
    static ArrayList<Integer> dist_list = new ArrayList<>();//모든 경우의 거리를 담은 리스트

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new char[m][n];
        nodelist = new ArrayList<>();

        NodeData start = null;
        NodeData end = null;
        for(int i = 0; i < m; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') start = new NodeData(i,j,0);//시작구간 설정
                if(map[i][j] == 'X') nodelist.add(new NodeData(i,j,0));//물건이 있는 구간을 리스트에 담는다
                if(map[i][j] == 'E') end = new NodeData(i,j,0);//끝나는 구간 설정
            }
        }

        if(nodelist.size() == 0){//물건이 없는 경우 시작지점과 끝 지점의 길이를 반환
            System.out.println(search(start, end));
        }else{
            boolean[] use = new boolean[nodelist.size()];
            for(int i = 0; i < nodelist.size(); i++){//시작지점에서 n개의 물건을 찾으러 가는 for문
                use[i] = true;
                set_dist(1, search(start, nodelist.get(i)), use, nodelist.get(i), end);
                use[i] = false;
            }

            Collections.sort(dist_list);
            System.out.println(dist_list.get(0));
        }
    }

    public static void set_dist(int depth, int dist, boolean[] use, NodeData start, NodeData end){
        if(nodelist.size() == depth){//모든 물건의 경로를 살폈으면 마지막 물건과 끝 지점의 길이를 더해 리스트에 담는다
            dist_list.add(dist + search(start, end));
        }else{//그렇지 않은 경우 시작지점을 다시 세팅
            for(int i = 0; i < nodelist.size(); i++){
                if(!use[i]){
                    use[i] = true;
                    set_dist(depth + 1, dist + search(start, nodelist.get(i)), use, nodelist.get(i), end);
                    use[i] = false;
                }
            }
        }
    }

    //BFS로 물건사이의 최단거리를 구하는 메서드
    public static int search(NodeData start, NodeData end){
        Queue<NodeData> queue = new LinkedList<>();
        queue.offer(start);
        int dist = 0;
        boolean[][] visit = new boolean[map.length][map[0].length];
        visit[start.x][start.y] = true;

        while(!queue.isEmpty()){
            NodeData nd = queue.poll();
            int now_x = nd.x;
            int now_y = nd.y;
            int now_dist = nd.dist;

            if(now_x == end.x && now_y == end.y){
                dist = now_dist;
                break;
            }

            if(now_x > 0 && !visit[now_x - 1][now_y] && map[now_x - 1][now_y] != '#'){
                queue.offer(new NodeData(now_x - 1, now_y, now_dist + 1));
                visit[now_x - 1][now_y] = true;
            }

            if(now_x < map.length - 1 && !visit[now_x + 1][now_y] && map[now_x + 1][now_y] != '#'){
                queue.offer(new NodeData(now_x + 1, now_y, now_dist + 1));
                visit[now_x + 1][now_y] = true;
            }

            if(now_y > 0 && !visit[now_x][now_y - 1] && map[now_x][now_y - 1] != '#'){
                queue.offer(new NodeData(now_x, now_y - 1, now_dist + 1));
                visit[now_x][now_y - 1] = true;
            }

            if(now_y < map[0].length - 1 && !visit[now_x][now_y + 1] && map[now_x][now_y + 1] != '#'){
                queue.offer(new NodeData(now_x, now_y + 1, now_dist + 1));
                visit[now_x][now_y + 1] = true;
            }
        }

        return dist;
    }

}

class NodeData{
    int x;
    int y;
    int dist;

    public NodeData(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}
