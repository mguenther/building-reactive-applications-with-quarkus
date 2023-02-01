package net.mguenther.reactive.employee;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepositoryBase<Employee, String> {

    public Uni<Employee> findByEmail(final String email) {
        return find("email", email).firstResult();
    }
}
