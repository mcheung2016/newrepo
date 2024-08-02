import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public static void writeCSV(List<Employee> employees, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("id,name,salary,position,department,hourly_rate");
            bw.newLine();
            for (Employee e : employees) {
                bw.write(String.format("%d, %s, %.2f, %s, %s, %.2f",
                        e.id, e.name, e.salary, e.position, e.department, e.hourlyRate));
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
