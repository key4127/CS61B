import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import net.sf.saxon.functions.ConstantFunction;


public class Percolation {
    // TODO: Add any necessary instance variables.
    // 0 - blocked; 1 - opened; 2 - full
    private int[][] grid;
    private boolean[] connectLowestSite;
    private WeightedQuickUnionUF uf;
    private int numberOfOpenSites;
    private boolean isPercolated;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if(N <= 0) {
            throw new java.lang.IndexOutOfBoundsException("N is less than 0.");
        }

        grid = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }

        uf = new WeightedQuickUnionUF(N * N);

        connectLowestSite = new boolean[N * N];
        for(int i = 0; i < N * N; i++) {
            connectLowestSite[i] = i >= N * (N - 1);
        }

        numberOfOpenSites = 0;
        isPercolated = false;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        checkArgument(row);
        checkArgument(col);

        if(grid[row][col] == 0) {
            grid[row][col] = 1;
            unionAround(row, col);
            numberOfOpenSites += 1;

            if(row == 0) {
                makeFull(row, col);
            }

            if(connectLowestSite[uf.find(row * grid.length + col)] && isFull(row, col)) {
                isPercolated = true;
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        checkArgument(row);
        checkArgument(col);

        return grid[row][col] > 0;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        checkArgument(row);
        checkArgument(col);

        int parent = uf.find(row * grid.length + col);
        return grid[parent / grid.length][parent % grid.length] == 2;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return isPercolated;
    }

    private void checkArgument(int x) {
        if(x < 0 || x >= grid.length) {
            throw new java.lang.IllegalArgumentException("Argument out of bound.");
        }
    }

    private void unionAround(int row, int col) {
        int originalparent = uf.find(row * grid.length + col);
        boolean isFull = isFull(row, col);
        boolean isConnectLowestSites = isConnectLowest(row, col);

        if(row > 0 && grid[row - 1][col] != 0) {
            isFull = isFull || isFull(row - 1, col);
            isConnectLowestSites = isConnectLowestSites || isConnectLowest(row - 1, col);
            uf.union(row * grid.length + col, (row - 1) * grid.length + col);
        }
        if(row < grid.length - 1 && grid[row + 1][col] != 0) {
            isFull = isFull || isFull(row + 1, col);
            isConnectLowestSites = isConnectLowestSites || isConnectLowest(row + 1, col);
            uf.union(row * grid.length + col, (row + 1) * grid.length + col);
        }
        if(col > 0 && grid[row][col - 1] != 0) {
            isFull = isFull || isFull(row, col - 1);
            isConnectLowestSites = isConnectLowestSites || isConnectLowest(row, col - 1);
            uf.union(row * grid.length + col, row * grid.length + col - 1);
        }
        if(col < grid.length - 1 && grid[row][col + 1] != 0) {
            isFull = isFull || isFull(row, col + 1);
            isConnectLowestSites = isConnectLowestSites || isConnectLowest(row, col + 1);
            uf.union(row * grid.length + col, row * grid.length + col + 1);
        }

        int newparent = uf.find(row * grid.length + col);
        if(isFull) {
            grid[newparent / grid.length][newparent % grid.length] = 2;
        }

        connectLowestSite[newparent] = isConnectLowestSites;
    }

    private void makeFull(int row, int col) {
        int parent = row * grid.length + col;
        parent = uf.find(parent);
        grid[parent / grid.length][parent % grid.length] = 2;
    }

    private boolean isConnectLowest(int row, int col) {
        int parent = uf.find(row * grid.length + col);
        return connectLowestSite[parent];
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
