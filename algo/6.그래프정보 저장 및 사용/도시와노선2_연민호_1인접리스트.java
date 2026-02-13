package day0213;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 도시와노선2_연민호_1인접리스트 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			List<Integer>[] adjList = new List[N];	//인접 리스트
			for(int num=0; num<N; num++) adjList[num] = new ArrayList<>();
			
			//M개의 도로 정보를 인접리스트에 저장
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to= Integer.parseInt(st.nextToken());
				
				adjList[from].add(to);
			}
			
			//K개의 각 도시에서 출발하여 이동가능한 도시정보 출력
			sb.append("#").append(tc).append("\n");
			for(int i=0; i<K; i++) {
				int from = Integer.parseInt(br.readLine());
				sb.append(from).append("=[");
				for(int to : adjList[from]) {
					sb.append(to).append(",");
				}
				sb.append("]\n");
			}
		}
		System.out.println(sb);
	}
}
