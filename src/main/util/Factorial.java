package main.util;

public class Factorial {
    public int getFactorial(int value){
        int res = 1;
        for(int i = 1; i<= value; i++){
            res *= i;
        }
        return res;
    }
}
