import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14500_테트로미노 {

    static int N;
    static int M;
    static int max;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};


    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // N, M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // max
        max = 0;

        // map
        map = new int[N][M];
        visit = new boolean[N][M];

        // map drawing
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        // solve
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visit[i][j] = true;
                tetromino(i, j, map[i][j], 1);
                visit[i][j] = false;
            }
        }

        sb.append(max);
        System.out.println(sb.toString());

    }

    public static void tetromino(int row, int col, int sum, int count){

        if(count == 4){
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < 4; i++){

            int cur_row = row + dx[i];
            int cur_col = col + dy[i];

            if(cur_row < 0 || cur_row >= N || cur_col < 0 || cur_col >= M){
                continue;
            }

            if(!visit[cur_row][cur_col]){

                if(count == 2){
                    visit[cur_row][cur_col] = true;
                    tetromino(row, col, sum + map[cur_row][cur_col],count + 1 );
                    visit[cur_row][cur_col] = false;
                }

                visit[cur_row][cur_col] = true;
                tetromino(cur_row, cur_col, sum + map[cur_row][cur_col],count + 1 );
                visit[cur_row][cur_col] = false;
            }


        }

    }
}

/**
 *
 * 종이 크기 : N * M
 * 1칸은 1 * 1
 * 
 * # 도형의 종류
 * 기준선은 맨 왼쪽, 상단
 * 
 * 1. 기준, 오, 오, 오
 * 2. 기준, 아, 아, 오
 * 3. 기준, 오, 아, 왼
 * 4. 기준, 아, 오, 아
 * 5. 기준, 오, 오, 오왼
 *
 * # 도형 돌리기
 *
 *
 * # 기본 로직
 * 도형을 배치한다.

 * - 배치 할 수 있는지 검증한다.
 * - 배치한다.

 * - 배치 할 수 있는지 검증한다.
 * - 돌려서도 배치한다.
 *
 * 점수를 합산한다.
 *
 * 최대 점수를 구한다.
 *
 *
 * **/