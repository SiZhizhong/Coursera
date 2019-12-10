import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] openProb;
    private final int trials;
    private double meanProb, stddevNum;

    public PercolationStats(int n, int trials) {
        if (n <=0 || trials <= 0)
            throw new IllegalArgumentException();
        this.openProb = new double[trials];
        this.trials = trials;
        int row, col;
        Percolation per;
        for (int i = 0; i < trials; i++) {
            per = new Percolation(n);
            while (!per.percolates()) {
                row = StdRandom.uniform(n)+1;
                col = StdRandom.uniform(n)+1;
                per.open(row, col);
            }
            this.openProb[i] = (double) per.numberOfOpenSites() / (n * n);
        }


    }

    public double mean() {
        this.meanProb = StdStats.mean(this.openProb);
        return this.meanProb;

    }

    public double stddev() {
        this.stddevNum = StdStats.stddev(this.openProb);
        return this.stddevNum;

    }

    public double confidenceLo() {
        return this.meanProb - 1.96 * this.stddevNum / Math.pow(this.trials, 0.5);
    }

    public double confidenceHi() {
        return this.meanProb + 1.96 * this.stddevNum / Math.pow(this.trials, 0.5);
    }

    public static void main(String[] args) {
        PercolationStats pers = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(pers.mean());
        System.out.println(pers.stddev());
        System.out.printf("[%f,%f]", pers.confidenceLo(), pers.confidenceHi());
    }
}
