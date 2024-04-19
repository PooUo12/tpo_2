package trigonometry;

public class Cot {
    private final Sin sin;
    private final Cos cos;
    public Cot(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    public Double solveCot(double value, double eps){
        double check = (value / Math.PI);
        check = check - Math.floor(check);
        if (check == 0){
            return Double.NaN;
        }
        return cos.solveCos(value, eps)/sin.solveSin(value, eps);
    }
}
