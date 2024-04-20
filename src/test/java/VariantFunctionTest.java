import com.func.VariantFunction;
import com.log.Ln;
import com.log.Log;
import com.trigonometry.*;
import com.util.CsvUtil;
import com.trigonometry.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;


public class VariantFunctionTest {
    static double epsilon = 0.01;
    static CsvUtil systemCsv;
    static CsvUtil sinCsv;

    static Sin sinMock;
    static Cos cosMock;
    static Csc cscMock;
    static Cot cotMock;
    static Tan tanMock;
    static Ln lnMock;
    static Log logMock;

    static Reader sinIn;
    static Reader cosIn;
    static Reader cscIn;
    static Reader cotIn;
    static Reader tanIn;
    static Reader lnIn;
    static Reader log2In;
    static Reader log3In;


    // Найдем область допустимых значений
    //
    // с помощью Вольфрам Альфа и Desmos мы определили, что первая часть функции (x<=0) не определена при x % 3 == 0,
    // а вторая вообще не имеет решений при любых x
    //



    @BeforeAll
    static void init() {

        sinMock = Mockito.mock(Sin.class);
        cosMock = Mockito.mock(Cos.class);
        cscMock = Mockito.mock(Csc.class);
        cotMock = Mockito.mock(Cot.class);
        tanMock = Mockito.mock(Tan.class);
        lnMock = Mockito.mock(Ln.class);
        logMock = Mockito.mock(Log.class);

        try {
            var systemOut = new FileWriter("src/test/csv/out/system.csv");
            systemCsv = new CsvUtil(systemOut);
            var sinOut = new FileWriter("src/test/csv/out/sin.csv");
            sinCsv = new CsvUtil(sinOut);

            sinIn = new FileReader("src/test/csv/in/sin.csv");
            cosIn = new FileReader("src/test/csv/in/cos.csv");
            cscIn = new FileReader("src/test/csv/in/csc.csv");
            cotIn = new FileReader("src/test/csv/in/cot.csv");
            tanIn = new FileReader("src/test/csv/in/tan.csv");
            lnIn = new FileReader("src/test/csv/in/ln.csv");
            log2In = new FileReader("src/test/csv/in/log2.csv");
            log3In = new FileReader("src/test/csv/in/log3.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.solveFunction(Double.parseDouble(record.get(0)), epsilon)).thenReturn(Double.valueOf(record.get(1)));
                Mockito.when(sinMock.solveFunction(Double.parseDouble(record.get(0)) + Math.PI / 2, epsilon)).
                        thenReturn(Math.sin(Double.parseDouble(record.get(0)) + Math.PI / 2));
            }
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.solveCos(Double.parseDouble(record.get(0)), epsilon)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records) {
                Mockito.when(cscMock.solveCsc(Double.parseDouble(record.get(0)), epsilon)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tanIn);
            for (CSVRecord record : records) {
                Mockito.when(tanMock.solveTan(Double.parseDouble(record.get(0)), epsilon)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(lnMock.solveLn(Double.parseDouble(record.get(0)), epsilon)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.solveLog(Double.parseDouble(record.get(0)), epsilon, 2)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log3In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.solveLog(Double.parseDouble(record.get(0)), epsilon, 3)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException e) {
            System.err.println("error when reading or creating csv files " + e.getMessage());
        }
    }

    @AfterAll
    static void closeCsv() {
        try {
            systemCsv.close();
            sinCsv.close();
        } catch (IOException ignored) {
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/in/sin.csv")
    @DisplayName("test sin out")
    void testSinOut(double value, double expected) {
        var result = sinCsv.solveAndWrite(new Sin(), value, epsilon);
        Assertions.assertEquals(expected, result, epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/in/system.csv")
    @DisplayName("test with mocks")
    void testWithMocks(double value, double expected) {
        VariantFunction function = new VariantFunction(cosMock, cotMock, tanMock, cscMock, logMock);
        Assertions.assertEquals(expected, function.solveFunction(value, epsilon), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/in/system.csv")
    @DisplayName("test cos and mocks")
    void testWithCos(double value, double expected) {
        VariantFunction function = new VariantFunction(new Cos(sinMock), cotMock, tanMock, cscMock, logMock);
        Assertions.assertEquals(expected, function.solveFunction(value, epsilon), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/in/system.csv")
    @DisplayName("test cos, sin and mocks")
    void testWithCosAndSin(double value, double expected) {
        VariantFunction function = new VariantFunction(new Cos(new Sin()), cotMock, tanMock, cscMock, logMock);
        Assertions.assertEquals(expected, function.solveFunction(value, epsilon), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/in/system.csv")
    @DisplayName("test tan and mocks")
    void testWithTan(double value, double expected) {
        VariantFunction function = new VariantFunction(cosMock, new Cot(sinMock, cosMock), tanMock, cscMock, logMock);
        Assertions.assertEquals(expected, function.solveFunction(value, epsilon), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/in/system.csv")
    @DisplayName("test tan, sin, cos and mocks")
    void testWithTanSinCos(double value, double expected) {
        VariantFunction function = new VariantFunction(cosMock, cotMock, new Tan(new Sin(), new Cos(new Sin())), cscMock, logMock);
        Assertions.assertEquals(expected, function.solveFunction(value, epsilon), epsilon);
    }



    @ParameterizedTest
    @CsvFileSource(resources = "csv/in/system.csv")
    @DisplayName("test ln and mocks")
    void testWithLn(double value, double expected) {
        VariantFunction function = new VariantFunction(cosMock, cotMock, tanMock, cscMock, new Log(new Ln()));
        Assertions.assertEquals(expected, function.solveFunction(value, epsilon), epsilon);
    }


    @Test
    @DisplayName("integration test without mocks to make big csv file")
    void testIntegrationCSV() {
        VariantFunction function = new VariantFunction(new Cos(new Sin()), new Cot(new Sin(), new Cos(new Sin())), new Tan(new Sin(), new Cos(new Sin())), new Csc(new Sin()), new Log(new Ln()) );
        for (double x = -20; x < 10; x += 0.1) {
            var res = function.solveFunction(x, epsilon);
            if (Math.abs(res) < 100) {
                systemCsv.solveAndWrite(function, x, epsilon);
            }
        }
    }
}
