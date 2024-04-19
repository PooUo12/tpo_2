package main.trigonometry;

import main.util.Factorial;

public class Sin {
    private Factorial fact = new Factorial();
    public double solveSin(double value, double eps){
        double term = value;
        double sol = term;
        int k = 3;
        while (Math.abs(term) > eps){
            term = Math.pow(value, k) / fact.getFactorial(k);
            if (k % 4 == 3){
                term *= -1;
            }
            k += 2;
            sol += term;
        }
        return sol;
    }
}
