package day0204;

import java.util.Arrays;

public class Perm_for_arr_boolean {

	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};
	static int[] result = new int[3];
	static boolean[] isSelected = new boolean[N];

	public static void main(String[] args) {
		for(int i=0; i<N; i++) {
			result[0] = arr[i];
			isSelected[i] = true;

			for(int j=0; j<N; j++) {
				if(isSelected[j]) continue;

				result[1] = arr[j];
				isSelected[j] = true;
				for(int k=0; k<N; k++) {
					if(isSelected[k]) continue;

					result[2] = arr[k];
					isSelected[k] = true;
					System.out.println(Arrays.toString(result));
					isSelected[k] = false;
				}
				isSelected[j] = false;
			}
			isSelected[i] = false;
		}
	}
}