package trigonometry;

public class Cos {

    private final Sin sin;
    public Cos(Sin sin){
        this.sin = sin;
    }

    public double solveCos(double value, double eps){
        return sin.solveSin(value + Math.PI/2, eps);
    }


}
