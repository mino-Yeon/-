package day0204;
public class Perm_for {

	static int N = 4;	//전체 요소의 개수
	static int[] arr = {3, 5, 7, 9};

	public static void main(String[] args) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				for(int k=0; k<N; k++) {
					if(i==k || j==k) continue;
					System.out.println(arr[i]+" "+arr[j]+" "+arr[k]);
				}
			}
		}
	}
}