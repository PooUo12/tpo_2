package com.trigonometry;

public class Csc {
    private final Sin sin;
    public Csc(Sin sin){
        this.sin = sin;
    }

    public double solveCsc(double value, double eps){
        double check = (value / Math.PI);
        check = check - Math.floor(check);
        if (check == 0){
            return Double.NaN;
        }

        return 1.0/sin.solveFunction(value, eps);
    }
}
