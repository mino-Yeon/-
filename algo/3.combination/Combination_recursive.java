package day0204;
import java.util.Arrays;
import java.util.Scanner;

public class Combination_recursive {
	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};
	static int R;	//뽑을 개수

	static int[] result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();

		result = new int[R];
		combination(0, 0);
	}

	/**
	 * cnt인덱스에 담을 요소를 하나뽑고, cnt+1인덱스에 담을 요소뽑기는 재귀로 넘김
	 * 
	 * @param cnt 현재까지 뽑은 요소의 개수 or 뽑은 요소를 결과배열에 담을 인덱스
	 * @param start 현재 뽑아볼 요소 후보군들의 시작인덱스
	 */
	static void combination(int cnt, int start) {
		//기저조건(종료조건)
		if(cnt==R) {	//R개를 뽑는 순간
			System.out.println(Arrays.toString(result));
			return;
		}

		//반복파트(유도파트)
		for(int i=start; i<N; i++) {
			result[cnt] = arr[i];	//cnt에 담을 요소 뽑기

			combination(cnt+1, i+1);	//cnt+1에 담을 요소 뽑기는 재귀로 넘김
		}
	}
}
