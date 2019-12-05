package org.superbiz.moviefun;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvUtils {


    public static String readFile(String path) {

        try {

            InputStream is = CsvUtils.class.getClassLoader().getResourceAsStream(path);
            Scanner scanner = new Scanner(is).useDelimiter("\\A");


            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return "";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error extracting file", e);
        }

    }

    public static <T> List<T> readFromCsv(ObjectReader objectReader, String path) {
        try {
            List<T> results = new ArrayList<>();

            MappingIterator<T> iterator = objectReader.readValues(readFile(path));

            while (iterator.hasNext()) {
                results.add(iterator.nextValue());
            }
            return results;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
