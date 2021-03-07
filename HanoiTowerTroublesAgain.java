import java.util.PriorityQueue;
import java.util.Scanner;

public class HanoiTowerTroublesAgain {
    
    static int T; // T is number of tests
    static int[] N; // N is number of pegs in each test

    // priority queue enables O(log n) methods to poll, offer
    // gives efficient access to correct peg if one exists
    static PriorityQueue<Peg> q;

    // peg array enables O(1) access to next empty peg
    static Peg[] pegs;
    static int nextEmptyPeg;
    
    public static void main(String[] args) {

        // get input 
        // "The first line of the input contains a single integer T (1 <= T <= 50),
        // indicating the number of test cases. Each test case contains a single
        // integer N (1 <= N <= 50), indicating the number of pegs available."
        try (Scanner input = new Scanner(System.in)) {
            T = Integer.parseInt(input.nextLine());
            N = new int[T];
            for (int i = 0; i < T; i++)
                N[i] = Integer.parseInt(input.nextLine());
        }

        // "For each test case in the input print a line containing an integer
        // indicating the maximal number of balls that can be placed. Print
        // -1 if an infinite number of balls can be placed."
        for (int test = 0; test < T; test++) {
            
            q = new PriorityQueue<>();
            pegs = new Peg[N[test]];
            nextEmptyPeg = 0;
            
            // initialize priority queue and pegs array
            for (int i = 0; i < N[test]; i++) {
                Peg p = new Peg(); 
                pegs[i] = p;
                q.offer(p);
            }
            
            // place balls until no valid placement exists
            int n = 1;
            while (true) {
                Peg p = q.peek();
                if (n == p.nextBall) { // case when n is valid next ball
                    p = q.poll();
                    p.placeBall(n++);
                    q.offer(p);
                } else if (nextEmptyPeg >= 0) { // case when there is still an empty peg
                    p = pegs[nextEmptyPeg];
                    q.remove(p); // O(n) 
                    p.placeBall(n++);
                    q.offer(p);
                    // increment nextEmptyPeg or set to -1 if none remain
                    nextEmptyPeg = nextEmptyPeg < N[test]-1 ? nextEmptyPeg+1 : -1;
                } else { // case when no valid placements remain; print result
                    System.out.println(--n);
                    break;
                }
            }
        }
    }
}

class Peg implements Comparable<Peg> {
    int topBall;
    int nextSquare;
    int nextBall;
    
    public Peg() {
        topBall = 0;
        nextSquare = 1;
        nextBall = Integer.MAX_VALUE;
    }

    void placeBall(int ball) {
        topBall = ball;
        updateNextSquare();
        updateNextBall();
    }
    
    void updateNextSquare() {
        while (nextSquare <= topBall * 2)
            nextSquare += 2 * Math.sqrt(nextSquare) + 1;
    }
    
    void updateNextBall() {
        nextBall = nextSquare - topBall;
    }

    @Override
    public int compareTo(Peg o) {
        return this.nextBall - o.nextBall;
    }
}