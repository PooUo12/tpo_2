import log.Ln;
import log.Log;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import trigonometry.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;


public class test {
    public Sin sin = new Sin();
    public Csc csc = new Csc(sin);
    public Ln ln = new Ln();
    public Log log = new Log(ln);
//    @Test
//    public void sinTest(){
//        Assert.assertEquals(1.0/Math.sin(Math.PI/2), csc.solveCsc((double) Math.PI/2, 0.001), 0.001);
//    }
//
//    @Test
//    public void LnTest(){
//        Assert.assertEquals(Math.log10(7), log.SolveLog(10.0, 7.0, 0.001), 0.001);
//    }
}
