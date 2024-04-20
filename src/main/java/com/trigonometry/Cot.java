package com.trigonometry;

import com.common.IFunction;

public class Cot implements IFunction {
    private final Sin sin;
    private final Cos cos;
    public Cot(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double solveFunction(double value, double eps){
        double check = (value / Math.PI);
        check = check - Math.floor(check);
        if (check == 0){
            return Double.POSITIVE_INFINITY;
        }
        return cos.solveFunction(value, eps)/sin.solveFunction(value, eps);
    }
}
