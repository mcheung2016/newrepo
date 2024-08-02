import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Employee> readCSV(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double salary = Double.parseDouble(data[2]);
                String position = data[3];
                String department = data[4];
                double hourlyRate = Double.parseDouble(data[5]);
                employees.add(new Employee(id, name, position, department, salary, hourlyRate));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
