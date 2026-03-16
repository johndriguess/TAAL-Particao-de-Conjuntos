package models;

import java.util.List;

public class PartitionResult {
    private final boolean possible;
    private final List<Integer> subset1;
    private final List<Integer> subset2;

    public PartitionResult(boolean possible, List<Integer> subset1, List<Integer> subset2) {
        this.possible = possible;
        this.subset1 = subset1;
        this.subset2 = subset2;
    }

    public boolean isPossible() {
        return possible;
    }

    public List<Integer> getSubset1() {
        return subset1;
    }

    public List<Integer> getSubset2() {
        return subset2;
    }
}
