package com.trigonometry;

import com.common.IFunction;
import com.util.Factorial;

public class Sin implements IFunction {
    private final Factorial fact = new Factorial();

    @Override
    public Double solveFunction(double value, double epsilon) {
        double term = value;
        double sol = term;
        int k = 3;
        while (Math.abs(term) > epsilon){
            term = Math.pow(value, k) / fact.getFactorial(k);
            if (k % 4 == 3){
                term *= -1;
            }
            k += 2;
            sol += term;
            if (k == 31){
                break;
            }
            if (sol > 5000){
                sol = Double.POSITIVE_INFINITY;
            } else if(sol < -5000){
                sol = Double.NEGATIVE_INFINITY;
            }
        }
        return sol;
    }
}
