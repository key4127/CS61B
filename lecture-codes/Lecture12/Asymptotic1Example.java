public class Asymptotic1Example {
    public static boolean dup1(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] == A[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dup2(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static <Stopwatch> void main(String[] args) {
        int testsize = 1000;
        double lasttestruntime = 0.0;
        while (lasttestruntime < 10) {
            int[] arr = new int[testsize];
            for (int j = 0; j < testsize; j++) {
                arr[j] = j;
            }
            Stopwatch s = new Stopwatch();
        }
    }
}
