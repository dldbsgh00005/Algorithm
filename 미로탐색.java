import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미로탐색 {
    
    // 가로 세로
    static int N;
    static int M;
    // dx, dy
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    // 방문 처리 배열
    static boolean[][] visited;
    // 미로
    static int[][] maze;
    // 결괏값
    static int result;

    public static void main(String[] args) throws IOException {

        /**
         * N, M 이 주어진다.
         * 0, 1이 붙어서 문자열 형태로 주어진다.
         * 0은 이동 가능
         * 1은 이동 불가
         * 
         * 최소 이동 횟수를 구하라  dfs, bfs 문제
         *
         * **/

        result = Integer.MAX_VALUE;
//        System.out.println("result : " + result);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

//        System.out.println("N, M 값 할당");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

//        System.out.println("visit, maze 배열 선언");
        visited = new boolean[N][M];
        maze = new int[N][M];

//        System.out.println("값 입력 받기");
        for(int i = 0; i < N ; i++){
            String line = br.readLine();
//            System.out.println("line : " + line);
            for(int j = 0; j < M; j++){
                    maze[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
//                    System.out.print( maze[i][j] + " ");
            }
//            System.out.println();
        }

//        System.out.println("미로 출력");
//        for(int i = 0; i < N ; i++){
//            for(int j = 0; j < M; j++){
//                System.out.print(maze[i][j] + " ");
//            }
//            System.out.println();
//        }
        // 문제 풀이
        visited[0][0] = true;
        exitMaze(0,0, 1);
        // result가 MAX_VALUE 이면 -1 할당
        if(result == Integer.MAX_VALUE) result = -1;
        // 정답 빌드 및 출력
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        System.out.println(result);
    }

    /**
     * 필요한 정보
     * d
     * **/
    // 최단 거리 구하는 로직
    public static void exitMaze(int x, int y, int mv_cnt){
        // 가지 치기
        if(mv_cnt >= result) return;
        
        // 종료 조건 검사 후 처리
        if(x == N-1 && y == M-1){
            result = Math.min(result, mv_cnt);
//            System.out.println("reached to N,M! result : " + result);
            return;
        }
        // 이동 로직
        for(int i = 0; i < 4; i++){
            // 이동할 좌표
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 좌표 범위 검사
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || maze[nx][ny] == 0 || visited[nx][ny] ){
                continue;
            }
            // 방문 처리 및 재귀 호출
            visited[nx][ny] = true;
            exitMaze(nx, ny, mv_cnt + 1);
            visited[nx][ny] = false;
        }
    }
}
