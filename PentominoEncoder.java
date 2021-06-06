import java.util.ArrayList;

public class PentominoEncoder {

    private PentominoEncoder() {};
    
    public static ArrayList<String> getPentominoHeaders(int nRows, int nCols) {
        ArrayList<String> headers = new ArrayList<>();
        
        for (char c = 'O'; c <= 'Z'; c++)
            headers.add(Character.toString(c));
        for (int r = 0; r < nRows; r++)
            for (int c = 0; c < nCols; c++) {
                if (c < 10)
                    headers.add(Integer.toString(r) + Integer.toString(c));
                else
                    headers.add(Integer.toString(r) + Character.toString(c - 10 + 'A'));
            }
        return headers;
    }

    
    public static ArrayList<ArrayList<Integer>> getPentominoEncoding(int nRows, int nCols) {
        int first = 12;
        int last = nRows * nCols + 11;
        
        ArrayList<ArrayList<Integer>> rows = new ArrayList<>();

        // build rows for "O"
        for (int i = first; i <= last - 4; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(0);
            if (!((i-12)%nCols < nCols-4)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 3);
            row.add(i + 4);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 4*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(0);
            row.add(i);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 3*nCols);
            row.add(i + 4*nCols);
            rows.add(row);
        }
        
        // build rows for "P"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2*nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols > 0)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            rows.add(row);
        }
        
        // build rows for "Q"
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols < nCols-3)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 3);
            row.add(i + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols < nCols-3)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            row.add(i + 3 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols < nCols-3)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 3);
            row.add(i + 3 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols > 2)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i - 3 + nCols);
            row.add(i - 2 + nCols);
            row.add(i - 1 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 3*nCols);
            row.add(i + 1 + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 1 + nCols);
            row.add(i + 1 + 2*nCols);
            row.add(i + 1 + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(2);
            if (!((i-12)%nCols > 0)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i - 1 + 3*nCols);
            row.add(i + 3*nCols);
            rows.add(row);
        }
        
        // build rows for "R"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i - 1 + 2*nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i - 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(3);
            if (!((i-12)%nCols > 1)) {
                continue;
            }
            row.add(i);
            row.add(i - 2 + nCols);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            rows.add(row);
        }
        
        // build rows for "S"
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols < nCols-3)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 2 + nCols);
            row.add(i + 3 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols < nCols-3)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            row.add(i + 3 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols > 1 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i - 2 + nCols);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 1 + 2*nCols);
            row.add(i + 1 + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 1 + 2*nCols);
            row.add(i + 1 + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols > 0)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            row.add(i + 2*nCols);
            row.add(i - 1 + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(4);
            if (!((i-12)%nCols > 0)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            row.add(i - 1 + 3*nCols);
            rows.add(row);
        }
        
        // build rows for "T"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(5);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 1 + nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(5);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(5);
            if (!((i-12)%nCols > 1)) {
                continue;
            }
            row.add(i);
            row.add(i - 2 + nCols);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(5);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            row.add(i + 2*nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        // build rows for "U"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(6);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(6);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 1 + nCols);
            row.add(i + 2*nCols);
            row.add(i + 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(6);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 2);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(6);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + nCols);
            row.add(i + 2 + nCols);
            rows.add(row);
        }
        
        // build rows for "V"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(7);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        // build rows for "W"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(8);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 1 + 2*nCols);
            row.add(i + 2 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(8);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            row.add(i + 2 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(8);
            if (!((i-12)%nCols > 1)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i - 2 + 2*nCols);
            row.add(i - 1 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(8);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            rows.add(row);
        }
        
        // build rows for "X"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(9);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i+nCols-1);
            row.add(i+nCols);
            row.add(i + nCols + 1);
            row.add(i + nCols * 2);
            rows.add(row);
        }
                
        // build rows for "Y"
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols < nCols-3)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 3);
            row.add(i + 1 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols < nCols-3)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 2);
            row.add(i + 3);
            row.add(i + 2 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols > 1 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i - 2 + nCols);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2*nCols);
            row.add(i + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 1 + 2*nCols);
            row.add(i + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols > 0)) {
                continue;
            }
            row.add(i);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i + 2*nCols);
            row.add(i + 3*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 3*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(10);
            if (!((i-12)%nCols > 0)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            row.add(i + 2*nCols);
            row.add(i + 3*nCols);
            rows.add(row);
        }
        
        // build rows for "Z"
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(11);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + 1 + nCols);
            row.add(i + 1 + 2*nCols);
            row.add(i + 2 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(11);
            if (!((i-12)%nCols < nCols-2)) {
                continue;
            }
            row.add(i);
            row.add(i + nCols);
            row.add(i + 1 + nCols);
            row.add(i + 2 + nCols);
            row.add(i + 2 + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(11);
            if (!((i-12)%nCols > 0 && (i-12)%nCols < nCols-1)) {
                continue;
            }
            row.add(i);
            row.add(i + 1);
            row.add(i + nCols);
            row.add(i - 1 + 2*nCols);
            row.add(i + 2*nCols);
            rows.add(row);
        }
        
        for (int i = first; i <= last - 2*nCols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(11);
            if (!((i-12)%nCols > 1)) {
                continue;
            }
            row.add(i);
            row.add(i - 2 + nCols);
            row.add(i - 1 + nCols);
            row.add(i + nCols);
            row.add(i - 2 + 2*nCols);
            rows.add(row);
        }
        
        return rows;
    }
}