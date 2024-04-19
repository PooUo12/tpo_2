package main;

import main.log.Log;
import main.trigonometry.Cos;
import main.trigonometry.Cot;
import main.trigonometry.Csc;
import main.trigonometry.Tan;

public class Function {

    private final Cos cos;
    private final Cot cot;
    private final Tan tan;
    private final Csc csc;
    private final Log log;
    public Function(Cos cos, Cot cot, Tan tan, Csc csc, Log log){
        this.cos = cos;
        this.cot = cot;
        this.csc = csc;
        this.tan = tan;
        this.log = log;
    }

    public Double solveFunction(double value, double eps){
        if (value <= 0){
            return ((cos.solveCos(value, eps) - cot.solveCot(value, eps))/(tan.solveTan(value, eps))* (csc.solveCsc(value, eps) + tan.solveTan(value, eps)* cos.solveCos(value, eps)));
        } else {
            return Math.pow((log.SolveLog(5,value, eps) + log.SolveLog(10, value, eps))/ (log.SolveLog(5, value, eps)), 2)/Math.pow((log.SolveLog(2,value,eps)/ log.SolveLog(10, value,eps)),2) * log.SolveLog(2, Math.pow(value, 3), eps);
        }
    }
}
