package main.log;

public class Ln {
    public double solveLn(Double value, Double eps){
        double x = (value - 1)/ (value + 1);
        double term = x;
        double res = x;
        int k = 3;
        while(Math.abs(term) > eps){
            term = Math.pow(x, k) / k;
            k += 2;
            res += term;
        }
        return 2* res;
    }
}
