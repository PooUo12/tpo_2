package com.trigonometry;

import com.common.IFunction;

public class Csc implements IFunction {
    private final Sin sin;
    public Csc(Sin sin){
        this.sin = sin;
    }

    @Override
    public Double solveFunction(double value, double eps){
        double check = (value / Math.PI);
        check = check - Math.floor(check);
        if (check == 0){
            return Double.POSITIVE_INFINITY;
        }

        return 1.0/sin.solveFunction(value, eps);
    }
}
