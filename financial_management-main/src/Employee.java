public class Employee {
    int id;
    String name;
    String position;
    String department;
    double salary;
    double hourlyRate;

    public Employee(int id, String name, String position, String department, double salary, double hourlyRate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.hourlyRate = hourlyRate;
    }


    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", department=" + department
                + ", salary=" + salary + ", hourlyRate=" + hourlyRate + "]";
    }
}
