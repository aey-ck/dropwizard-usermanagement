package com.ema.ema.application;

import com.ema.ema.dao.EmployeeDAO;
import com.ema.ema.dao.ProjectDAO;
import com.ema.ema.model.Employee;
import com.ema.ema.model.Project;
import com.ema.ema.resource.EmployeeResource;
import com.mongodb.client.AggregateIterable;
import io.dropwizard.Configuration;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class EmaApplication extends io.dropwizard.Application<Configuration> {

    private static Scanner scanner = new Scanner(System.in);
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static ProjectDAO projectDAO  = new ProjectDAO();

    public void run(Configuration configuration, io.dropwizard.setup.Environment environment) throws Exception {
        environment.jersey().register(new EmployeeResource());
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Main");
        new EmaApplication().run(args);
        showMenu();
    }

    private static void showMenu() {
        System.out.print("\n\nPlease select the option : \n 1. Add Employee \t 2. Update Employee \t 3. View Employee \t 4. Delete Employee \n");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                addEmployee();
                break;
            case 2:
                updateEmployee();
                break;
            case 3:
                viewEmployee();
                break;
            case 4:
                deleteEmployee();
                break;
            default:
                break;
        }

    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter " + name + "'s designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter " + name + "'s age: ");
        int age = scanner.nextInt();

        Employee employee = new Employee(name, designation, "", age);
        ObjectId id = employeeDAO.addEmployee(employee);

        System.out.print("Enter total number of projects " + name + " has worked: ");
        int noOfProjects = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= noOfProjects; i++) {
            System.out.print("Enter Project " + i + " name: ");
            String projectName = scanner.nextLine();

            System.out.print("Enter Project " + i + " type: ");
            String projectType = scanner.nextLine();

            Project project = new Project(projectName, projectType, id);
            projectDAO.addProject(project);

        }
        showExitWarning();
    }

    private static void updateEmployee() {
        System.out.print("Enter employee Id: ");
        String empId = scanner.nextLine();

        System.out.print("Enter updated employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter " + name + "'s updated designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter " + name + "'s updated age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Employee employee = new Employee(name, designation, empId, age);


        employeeDAO.updateEmployee(employee);
        showExitWarning();

    }

    private static void viewEmployee() {
        System.out.print("Enter employee Id: \n");
        String empId = scanner.nextLine();
        Employee employee = employeeDAO.viewEmployee(empId);

        System.out.println(employee.getName());

            List<Project> ProjectList = (List<Project>) employee.getLookup();
            String projects = ProjectList.size() == 0 ? "No projects yet." : "";

            for (Project item :
                    ProjectList) {
                if (ProjectList.size() == 1) {
                    projects += item.getName();
                } else if (ProjectList.indexOf(item) == ProjectList.size() - 1) {
                    projects += "and " + item.getName() + ".";
                } else {
                    projects += item.getName() + ", ";
                }
            }

            System.out.print("\n"+employee.getName()+", "+employee.getDesignation()+", "+employee.getAge()+"\n");
            System.out.print(employee.getName()+" has worked in : \n"+projects+"\n \n");

        showExitWarning();
    }


    private static void deleteEmployee() {
        System.out.print("Enter employee Id: ");
        String empId = scanner.nextLine();
        employeeDAO.removeEmployee(empId);
        showExitWarning();
    }


    private static void showExitWarning() {
        System.out.println("Done! Do you want to continue(y|n)? :");
        String exitStatus = scanner.nextLine();

        if ("y".equals(exitStatus)) {
            showMenu();
        }
    }


}
