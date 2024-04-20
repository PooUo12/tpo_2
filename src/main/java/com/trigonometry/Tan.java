package com.trigonometry;

public class Tan {

    private final Sin sin;
    private final Cos cos;
    public Tan(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    public Double solveTan(double value, double eps){
        double check = (value / Math.PI);
        check = check - Math.floor(check);
        if (check == 0.5){
            return Double.NaN;
        }
        return sin.solveFunction(value, eps)/(cos.solveCos(value, eps));
    }
}
