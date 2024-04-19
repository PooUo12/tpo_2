package main;

import main.trigonometry.Cos;
import main.trigonometry.Cot;
import main.trigonometry.Csc;
import main.trigonometry.Tan;

public class Function {

    private final Cos cos;
    private final Cot cot;
    private final Tan tan;
    private final Csc csc;
    public Function(Cos cos, Cot cot, Tan tan, Csc csc){
        this.cos = cos;
        this.cot = cot;
        this.csc = csc;
        this.tan = tan;
    }

    public Double solveFunction(double value, double eps){
        if (value <= 0){
            return ((cos.solveCos(value, eps) - cot.solveCot(value, eps))/(tan.solveTan(value, eps))* (csc.solveCsc(value, eps) + tan.solveTan(value, eps)* cos.solveCos(value, eps)));
        } else {
            return 0.0;
        }
    }
}
