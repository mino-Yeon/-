package day0205;

import java.util.Arrays;

public class Perm_NextPermutation {
	
	static int[] arr = {7, 4, 5, 2, 1, 6, 3};

	//4P4
	public static void main(String[] args) {
		Arrays.sort(arr);	//가장 작은 순열 만들기

		do {

			System.out.println(Arrays.toString(arr));

		} while (np(arr));
	}

	private static boolean np(int[] arr) {
		int N = arr.length;

		//1. i-1, i 비교시 i 요소가 더 큰 순간 찾기
		int i=N-1;
		while(i>0 && arr[i-1] >= arr[i]) i--;

		if(i==0) return false;	//더 이상 큰 순열 없음

		//2. i-1과 swap할 j 인덱스 찾기( 뒤에서 부터 i-1 요소보다 큰 요소 )
		int j=N-1;
		while( arr[i-1] >= arr[j]) j--;

		//3. i-1, j 요소 스왑
		swap(arr, i-1, j);

		//4. i~끝 요소들 오름차순 정렬
		int k=N-1;
		while( i < k ) swap(arr, i++, k--);

		return true;	//순열 만들기 성공
	}

	private static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
}