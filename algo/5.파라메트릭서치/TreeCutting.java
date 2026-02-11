import java.util.Scanner;

public class TreeCutting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] trees = new int[N];
        int high = 0;
        for (int i = 0; i < N; i++) {
            trees[i] = sc.nextInt();
            high = Math.max(high, trees[i]);
        }

        int low = 0;
        int answer = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            long total = 0;
            for (int i = 0; i < N; i++) {
                if (trees[i] > mid)
                    total += trees[i] - mid;
            }
            if (total >= M) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
