package com.util;

import com.common.IFunction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;


public class CsvUtil implements Closeable {
    private final CSVPrinter printer;

    public CsvUtil(Writer out) throws IOException {
        this.printer = CSVFormat.DEFAULT.print(out);
        writeHeader("X", "Y");
    }

    @Override
    public void close() throws IOException {
        printer.close();
    }

    public void writeResultToCSV(double value, double result) throws IOException {
        printer.printRecord(value, result);
    }

    public void writeHeader(String header1, String header2) {
        try {
            printer.printRecord(header1, header2);
        } catch (IOException e) {
            System.err.println("error when writing header in csv file " + e.getMessage());
        }
    }

    public double solveAndWrite(IFunction function, double value, double epsilon) {
        var result = function.solveFunction(value, epsilon);

        try {
            writeResultToCSV(value, result);
        } catch (IOException e) {
            System.err.println("error when writing csv file " + e.getMessage());
        }

        return result;
    }
}
