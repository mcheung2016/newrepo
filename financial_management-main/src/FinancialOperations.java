import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.Map;

public class FinancialOperations {

    public static double getHourlyRateSum(List<Employee> employees) {
        double sum = 0;
        for (Employee e : employees) {
            sum += e.getHourlyRate();
        }
        return sum;
    }

    public static double getHourlyRateSumStream(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::getHourlyRate).sum();
    }

    public static double sumDepartmentSalary(List<Employee> employees, String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .mapToDouble(Employee::getSalary).sum();
    }

    public static double getAverageSalaryByDepartment(List<Employee> employees, String department) {
        OptionalDouble average =  employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .mapToDouble(Employee::getSalary).average();
        double finalAverage = 0.00;
        if (average.isPresent()) {
            finalAverage = average.getAsDouble();
        }
        return finalAverage;

        // This method below works too, because using .OrElse(0.00) to check the null.
        /*
        return employees.stream()
            .filter(e -> e.getDepartment().equals(department))
            .mapToDouble(Employee::getSalary
            .average()
            .orElse(0.00)
        */
    }

    public static double sumDepartmentHourlyRate(List<Employee> employees, String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .mapToDouble(Employee::getHourlyRate).sum();
    }

    public static double getAverageHourlyRateByDepartment(List<Employee> employees, String department) {
        OptionalDouble average = employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .mapToDouble(Employee::getHourlyRate).average();
        double finalAverage = 0.00;
        if (average.isPresent()) {
            finalAverage = average.getAsDouble();
        }
        return finalAverage;
    }

    public static Map<String, Double> getTotalSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)
                ));
    }

    public static Map<String, Double> getAverageSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)
                ));
    }

    public static Map<String, Double> getTotalHourlyRateByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getHourlyRate)
                ));
    }

    public static Map<String, Double> getAverageHourlyRateByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getHourlyRate)
                ));
    }

    public static int getNumSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static int getSumOfEmployeeIdDigits(List<Employee> employees) {
        return employees.stream()
                .mapToInt(Employee::getId)
                .map(FinancialOperations::getNumSum).sum();
    }
}
