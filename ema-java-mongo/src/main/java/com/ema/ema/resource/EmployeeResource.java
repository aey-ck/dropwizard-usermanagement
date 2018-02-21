package com.ema.ema.resource;

import com.ema.ema.model.Employee;
import com.google.common.collect.Lists;
import io.dropwizard.jersey.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)

public class EmployeeResource {

    List<Employee> employeeArray = Lists.newArrayList();
    static AtomicLong id = new AtomicLong();

    @GET
    public List<Employee> getEmployees() {
        return employeeArray;
    }

    @GET
    @Path("/{id}")
    public Employee getEmployee(@PathParam("id") long id) {

        Employee em = new Employee();
        for (Employee item : employeeArray
                ) {

//            if (item.getId() == id) {
//                em = item;
//            }

        }

        return em;

    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Employee> updateEmployee(Employee employee) {

        Iterator<Employee> employees = employeeArray.iterator();
        while (employees.hasNext()) {
            Employee item = employees.next();
            if (item.getId() == employee.getId()) {
                item.setDesignation(employee.getDesignation());
                item.setName(employee.getName());            }

        }
        return employeeArray;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Employee createEmployee(Employee employee) {
//        String idVal = id.incrementAndGet();
//        employee.setId(idVal);
//        employeeArray.add(employee);
        return employee;
    }

    @DELETE
    @Path("/{id}")
    public List<Employee> deleteEmployee(@PathParam("id") long id) {

        Iterator<Employee> employees = employeeArray.iterator();

        while (employees.hasNext()) {
            Employee item = employees.next();
//            if (item.getId() == id) {
//                employees.remove();
//            }

        }


        return employeeArray;

    }


}
