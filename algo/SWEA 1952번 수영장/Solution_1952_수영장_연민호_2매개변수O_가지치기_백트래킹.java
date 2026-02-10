package day0210;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:24,832KB, 시간:87ms
 * @author SSAFY
 *
 */
public class Solution_1952_수영장_연민호_2매개변수O_가지치기_백트래킹 {
	static int[] cost = new int[4];		//이용권 가격 저장	
	static int[] plan = new int[13];	//이용 계획 저장	plan[12] : 12월의 이용일자

	static int min;	//비용의 최솟값

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) cost[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for(int month=1; month<=12; month++) plan[month] = Integer.parseInt(st.nextToken());

			min = cost[3];	//1년 비용을 초기값으로 넣고 시작
			selectTicket(1, 0);

			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	/**
	 * month월의 이용권을 결정하고 (month+1)월의 이용권 결정은 재귀로 넘김
	 * @param month 이용권을 결정할 월
	 * @param costSum 현재까지 선택된 이용권들의 비용 합
	 */
	private static void selectTicket(int month, int costSum) {
		if(min <= costSum) return;	//가지치기 조건

		if(month>12) {
			min = costSum;
			return;
		}

		selectTicket(month+1, costSum+plan[month]*cost[0] );	//1일 이용권
		selectTicket(month+1, costSum+cost[1]);					//1달 이용권
		selectTicket(month+3, costSum+cost[2]);					//3달 이용권( month, month+1, month+2) 까지 비용이 정해지므로 month+3결정하러 감
	}
}
