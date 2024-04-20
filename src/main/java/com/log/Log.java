package com.log;

public class Log {
    private final Ln ln;

    public Log(Ln ln){
        this.ln = ln;
    }

    public Double solveLog(double base, double value, double eps){
        if (base == 1){
            return Double.NaN;
        }
        return (ln.solveLn(value, eps))/(ln.solveLn(base, eps));
    }
}
