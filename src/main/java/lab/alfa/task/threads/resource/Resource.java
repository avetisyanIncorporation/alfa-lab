package lab.alfa.task.threads.resource;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Pargev A. created on 09.05.2023
 */
public class Resource {
    private final BufferedWriter bufferedWriter;

    public Resource(String fileName) {
        try {
            bufferedWriter = Files.newBufferedWriter(Path.of(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(int i) {
        try {
            bufferedWriter.write((i + " "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
