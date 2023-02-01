package net.mguenther.reactive.employee;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Employee extends PanacheEntityBase {

    @Id
    public String employeeId;

    public String givenName;

    public String lastName;

    public String email;

    public String departmentId;

    public Employee() {
    }

    public Employee(final String employeeId, final String givenName, final String lastName, final String email, final String departmentId) {
        this.employeeId = employeeId;
        this.givenName = givenName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
    }

    public static Uni<Employee> accept(final CreateEmployeeCommand command) {
        final String employeeId = UUID.randomUUID().toString();
        return Uni
                .createFrom()
                .item(new Employee(employeeId, command.getGivenName(), command.getLastName(), command.getEmail(), command.getDepartment()))
                .flatMap(employee -> Panache.withTransaction(employee::persist).replaceWith(employee));
    }
}
