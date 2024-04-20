package com.trigonometry;

import com.common.IFunction;

public class Tan implements IFunction {

    private final Sin sin;
    private final Cos cos;
    public Tan(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double solveFunction(double value, double eps){
        double check = (value / Math.PI);
        check = check - Math.floor(check);
        if (check == 0.5){
            return Double.POSITIVE_INFINITY;
        }
        return sin.solveFunction(value, eps)/(cos.solveFunction(value, eps));
    }
}
