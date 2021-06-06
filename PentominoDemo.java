import java.util.ArrayList;

public class PentominoDemo {
    
    static int[] rows = { 3, 4, 5, 6 };
    static int[] cols = { 20, 15, 12, 10 };

    public static void main(String[] args) {

        for (int i = 0; i < 4; i++) {
            ArrayList<String> headers = PentominoEncoder.getPentominoHeaders(rows[i], cols[i]);
            ArrayList<ArrayList<Integer>> encodedPentominoes = PentominoEncoder.getPentominoEncoding(rows[i], cols[i]);
            DLX dancingLinks = new DLX(headers, encodedPentominoes);
            dancingLinks.run();
            System.out.printf("There are %d solutions for a %d x %d box\n", dancingLinks.getNumberOfSolutions(), rows[i], cols[i]);
        }

        
    }

}
