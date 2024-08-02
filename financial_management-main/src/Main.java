import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = CSVReader.readCSV("./src/Financial_Management.csv");

        // Traditional
        System.out.println("Total hourly rate sum: " + FinancialOperations.getHourlyRateSum(employees));

        // Stream method
        System.out.println("Total hourly rate sum: " + FinancialOperations.getHourlyRateSumStream(employees));

        String department = "HR";
        System.out.println("Sum of salaries in " + department + ": " + FinancialOperations.sumDepartmentSalary(employees, department));
        System.out.println("Average of salaries in " + department + ": " + FinancialOperations.getAverageSalaryByDepartment(employees, department));
        System.out.println("Sum of hourly rates in " + department + ": " + FinancialOperations.sumDepartmentSalary(employees, department));
        System.out.println("Average of hourly rates in " + department + ": " + FinancialOperations.getAverageHourlyRateByDepartment(employees, department));

        System.out.println("Total Salary by department: " + FinancialOperations.getTotalSalaryByDepartment(employees));
        System.out.println("Average Salary by department: " + FinancialOperations.getAverageSalaryByDepartment(employees));
        System.out.println("Total hourly rate by department: " + FinancialOperations.getTotalHourlyRateByDepartment(employees));
        System.out.println("Average hourly rate by department: " + FinancialOperations.getAverageHourlyRateByDepartment(employees));

        System.out.println("Sum of employee ID digits: " + FinancialOperations.getSumOfEmployeeIdDigits(employees));

        CSVWriter.writeCSV(employees, "output.csv");

        Map<String, List<Employee>> employeesDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        for (Map.Entry<String, List<Employee>> entry : employeesDepartment.entrySet()) {
            //System.out.println("Department: " + entry.getKey());
            //System.out.println("Employee: " + entry.getValue());
            String departmentName = entry.getKey();
            List<Employee> employeeList = entry.getValue();
            CSVWriter.writeCSV(employeeList, "output_" + departmentName.toLowerCase() + "_employees.csv");
        }

    }
}