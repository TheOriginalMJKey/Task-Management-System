package com.example.taskmanagement.util;

import com.example.taskmanagement.model.Task;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static String exportTasksToCsv(List<Task> tasks) throws IOException {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Title", "Description", "Completed"))) {
            for (Task task : tasks) {
                csvPrinter.printRecord(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
            }
        }
        return writer.toString();
    }

    public static List<Task> importTasksFromCsv(String csv) throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new StringReader(csv))) {
            List<CSVRecord> records = reader.readAll();
            for (CSVRecord record : records) {
                Task task = new Task();
                task.setId(Long.parseLong(record.get("ID")));
                task.setTitle(record.get("Title"));
                task.setDescription(record.get("Description"));
                task.setCompleted(Boolean.parseBoolean(record.get("Completed")));
                tasks.add(task);
            }
        }
        return tasks;
    }
}
