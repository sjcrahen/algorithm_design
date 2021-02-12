import java.util.LinkedList;
import java.util.Queue;

// implementation of 1962 Gale-Shapley stable matching algorithm
public class StableMatchingDemo {

    // N = number of men, women
    private static final int N = 10;
    
    // list of free men
    private static Queue<Integer> freeMen;
    
    // arrays to represent engagements
    private static int[] wife = new int[N+1];
    private static int[] husband = new int[N+1];
    
    // arrays to store preference lists
    // index is rank, value is person
    private static int[][] mPref = new int[N+1][N+1];
    private static int[][] wPref = new int[N+1][N+1];
    
    // inverse list maps wPref by index
    // index is person, value is rank
    private static int[][] wInv = new int[N+1][N+1];
    
    // maintain count of proposals made by each man
    private static int[] count = new int[N+1];
    
    // utility method to randomly assign preferences
    private static void shuffle(int[][] a) {
        for (int i = 1; i < N+1; i++)
            for (int j = 1; j < N+1; j++) {
                int temp = a[i][j];
                int k = (int) (Math.random()*N + 1);
                a[i][j] = a[i][k];
                a[i][k] = temp;
            }
    }
    
    // utility method to print 2-d array
    /*private static void print(int[][] a) {
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }*/
    
    // utility method to engage m and w
    private static void getEngaged(int m, int w) {
        wife[m] = w;
        husband[w] = m;
        System.out.println(" - man " + m + " engaged to woman " + w);
    }
    
    // utility method to dump man
    private static void dumpFiance(int w) {
        int m = husband[w];
        freeMen.add(m);
        System.out.print(" - man " + m + " dumped");
    }
    
    public static void main(String[] args) {

        // initialize list of free men
        freeMen = new LinkedList<Integer>();
        for (int i = 1; i < N+1; i++)
            freeMen.add(i);
        
        // initialize preferences
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                mPref[i][j] = j;
                wPref[i][j] = j;
            }
        }

        // generate random preferences
        shuffle(mPref);
        shuffle(wPref);
        
        // initialize inverse list of wPref
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                wInv[i][wPref[i][j]] = j; 
            }
        }
        
        // Gale-Shapley algorithm
        while (!freeMen.isEmpty()) {
            int m = freeMen.poll(); // select free man
            while (count[m] < N) {
                int w = mPref[m][++count[m]]; // select woman to propose to
                System.out.print("man " + m + " proposed to woman " + w);
                if (husband[w] == 0) { // woman is free
                    getEngaged(m, w);
                    break;
                } else if (wInv[w][m] < wInv[w][husband[w]]) { // woman prefers m
                    dumpFiance(w);
                    getEngaged(m, w);
                    break;
                } else {
                    System.out.println(" - man " + m + " rejected");
                }
            }
        }
        
        System.out.println();
        for (int m = 1; m < N+1; m++) {
            System.out.println("man " + m + " married woman " + wife[m]);
        }
    }

}
