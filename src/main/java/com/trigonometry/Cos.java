package com.trigonometry;

import com.common.IFunction;

public class Cos implements IFunction {

    private final Sin sin;
    public Cos(Sin sin){
        this.sin = sin;
    }

    @Override
    public Double solveFunction(double value, double eps){
        return sin.solveFunction(value + Math.PI/2, eps);
    }


}
