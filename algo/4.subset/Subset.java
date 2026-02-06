package day0206;
public class Subset {
	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};

	static boolean[] isSelected;	//부분집합에 포함 여부
	public static void main(String[] args) {

		isSelected = new boolean[N];
		subset(0);	//0인덱스 요소를 부분집합에 포함/비포함 여부 결정해
	}

	private static void subset(int idx) {
		if(idx==N) {
			for(int i=0; i<N; i++) {
				if(isSelected[i]) System.out.print(arr[i]);
			}
			System.out.println();
			return;
		}

		//idx요소 부분집합에 포함
		isSelected[idx] = true;
		subset(idx+1);	//idx+1요소의 포함/비포함은 재귀로 넘기기

		//idx요소 부분집합에 비포함
		isSelected[idx] = false;
		subset(idx+1);	//idx+1요소의 포함/비포함은 재귀로 넘기기
	}
}