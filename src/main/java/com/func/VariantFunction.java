package com.func;

import com.common.IFunction;
import com.log.Log;
import com.trigonometry.Cos;
import com.trigonometry.Cot;
import com.trigonometry.Csc;
import com.trigonometry.Tan;

public class VariantFunction implements IFunction {

    private final Cos cos;
    private final Cot cot;
    private final Tan tan;
    private final Csc csc;
    private final Log log;

    public VariantFunction(Cos cos, Cot cot, Tan tan, Csc csc, Log log) {
        this.cos = cos;
        this.cot = cot;
        this.csc = csc;
        this.tan = tan;
        this.log = log;
    }

    public Double solveFunction(double value, double eps) {
        if (value <= 0) {
            return ((cos.solveCos(value, eps) - cot.solveCot(value, eps)) / (tan.solveTan(value, eps)) * (csc.solveCsc(value, eps) + tan.solveTan(value, eps) * cos.solveCos(value, eps)));
        } else {
            return Math.pow((log.solveLog(5, value, eps) + log.solveLog(10, value, eps)) / (log.solveLog(5, value, eps)), 2) / Math.pow((log.solveLog(2, value, eps) / log.solveLog(10, value, eps)), 2) * log.solveLog(2, Math.pow(value, 3), eps);
        }
    }
}
