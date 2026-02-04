package day0204;
import java.util.Arrays;
import java.util.Scanner;

public class Perm_recursive {
	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};
	static int R;	//뽑을 개수

	static boolean[] isSelected;
	static int[] result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();

		result = new int[R];
		isSelected = new boolean[N];
		permutation(0);
	}

	/**
	 * 
	 * @param cnt 현재까지 뽑은 요소의 개수
	 */
	static void permutation(int cnt) {
		//기저조건(종료조건)
		if(cnt==R) {	//R개를 뽑는 순간
			System.out.println(Arrays.toString(result));
			return;
		}

		//반복파트(유도파트)
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;

			result[cnt] = arr[i];	//요소 뽑기
			isSelected[i] = true;	//arr[i] 뽑힘

			permutation(cnt+1);

			isSelected[i] = false;	//되돌리기
		}
	}
}