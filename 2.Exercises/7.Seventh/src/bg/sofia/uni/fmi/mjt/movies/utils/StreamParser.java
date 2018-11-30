package bg.sofia.uni.fmi.mjt.movies.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class StreamParser {
    public static List<String> parse(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().map(String::trim).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
