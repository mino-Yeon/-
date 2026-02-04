package day0204;

import java.util.Arrays;

public class Perm_for_arr {

	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};
	static int[] result = new int[3];

	public static void main(String[] args) {
		for(int i=0; i<N; i++) {
			result[0] = arr[i];

			for(int j=0; j<N; j++) {
				if(i==j) continue;

				result[1] = arr[j];

				for(int k=0; k<N; k++) {
					if(i==k || j==k) continue;

					result[2] = arr[k];
					System.out.println(Arrays.toString(result));
				}
			}
		}
	}
}