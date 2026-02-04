package day0204;

public class DupPerm_for_arr {

	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};

	static int[] result = new int[3];

	public static void main(String[] args) {

		for(int i=0; i<N; i++) { // i : 첫번째로 뽑을 요소의 인덱스
			result[0] = arr[i];

			for(int j=0; j<N; j++) {	// j : 두번째로 뽑을 요소의 인덱스
				result[1] = arr[j];

				for(int k=0; k<N; k++) {
					result[2] = arr[k];
					System.out.println(result);
				}
			}
		}
	}
}