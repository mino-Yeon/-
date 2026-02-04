package day0204;
public class Combination_for {

	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};

	public static void main(String[] args) {
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					System.out.println(arr[i]+" "+arr[j]+" "+arr[k]);
				}
			}
		}
	}
}