package day0204;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:26,496KB, 시간:2,105ms
 */
public class Solution_6808_규영이와인영이의카드게임_연민호_1boolean배열 {
	static int[] in; 	//인영이의 카드 종류

	static boolean[] isSelected;	//순열 생성 시  이미 선택된 카드 체크를 위해

	static int[] roundGyu;	//라운드별 제시한 규영이의 카드 - 고정 값
	static int[] roundIn; //라운드별 제시한 인영이의 카드 - 순열코드로 완성

	static int win, lose; //규영이가 이긴횟수와 진횟수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			boolean[] check = new boolean[19]; //입력받을 때 인영이의 카드를 알아내기 위한 불린 배열 - true의 인덱스: 규영이의 카드

			in = new int[9];
			roundGyu = new int[9];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				int num = Integer.parseInt(st.nextToken()); 
				roundGyu[i] = num;	//규영이의 카드 입력

				check[num] = true;	//규영이의 카드 체크
			}

			//check 배열 탐색하면서 인영이의 카드 골라내기
			int idx = 0;
			for(int i=1; i<=18; i++) {
				//false인 인덱스가 인영이의 카드 번호 - 해당 카드 번호(i) in배열에 담기
				if(!check[i]) in[idx++] = i;
			}

			win = 0;	//규영이 이긴 횟수 초기화
			lose = 0;	//규영이 진 횟수 초기화
			roundIn = new int[9];
			isSelected = new boolean[9];
			permutation(0);

			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
	}

	/**
	 * cnt라운드에 인영이가 낼 카드를 고르고, cnt+1라운드에 인영이가 낼 카드 고르기는 재귀로 넘김
	 * @param cnt 인영이가 내야할 카드를 결정할 라운드 or 현재까지 결정된 라운드 수
	 */
	private static void permutation(int cnt) {
		// step 01. 인영이의 9라운드 카드 선택 완료
		if(cnt==9) {
			//step 02. 규영이와 인영이의 총점 구하고 승패 cnt
			calcTotal();
			return;
		}

		for(int i=0; i<9; i++) {
			if(isSelected[i]) continue;	//이미 선택됨

			roundIn[cnt] = in[i];	//카드 선택
			isSelected[i] = true;	//i카드는 선택중이므로 되돌리기

			permutation(cnt+1);		//다음 카드 선택

			isSelected[i] = false; 	//되돌리기
		}
	}

	private static void calcTotal() {
		//1. 총점 구하기
		int sumGyu = 0;
		int sumIn = 0;
		//라운드 별 숫자 비교
		for(int i=0; i<9; i++) {
			//규영 > 인영
			if(roundGyu[i] > roundIn[i]) sumGyu += (roundGyu[i] + roundIn[i]);
			//규영 < 인영
			else sumIn += (roundGyu[i] + roundIn[i]);
		}

		//2. 승패 cnt
		//규영 승리
		if(sumGyu > sumIn) win++;
		//규영 패배
		else if(sumGyu < sumIn) lose++;
	}
}