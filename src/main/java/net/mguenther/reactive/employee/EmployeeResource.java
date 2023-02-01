package net.mguenther.reactive.employee;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import net.mguenther.reactive.ExceptionMapper;
import net.mguenther.reactive.department.Department;
import net.mguenther.reactive.department.DepartmentManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
@ApplicationScoped
public class EmployeeResource {

    private final DepartmentManager departments;

    private final ExceptionMapper exceptionMapper;

    @Inject
    public EmployeeResource(final DepartmentManager departments, final ExceptionMapper exceptionMapper) {
        this.departments = departments;
        this.exceptionMapper = exceptionMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Employee> getEmployees() {
        return Employee.streamAll();
    }

    @GET
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getEmployee(@PathParam("employeeId") final String employeeId) {
        return Employee.findById(employeeId)
                .onItem().ifNull().failWith(() -> new EmployeeNotFoundException(employeeId))
                .onItem().transform(employee -> Response.ok(employee).build())
                .onFailure().recoverWithItem(exceptionMapper::handle);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createEmployee(final CreateEmployeeCommand command) {
        return Uni.combine()
                .all()
                .unis(Employee.accept(command), departments.findByName(command.getDepartment()))
                .combinedWith(this::merge)
                .map(outgoingEmployee -> Response.ok(outgoingEmployee).status(Response.Status.CREATED).build())
                .onFailure().recoverWithItem(exceptionMapper::handle);
    }

    private OutgoingEmployee merge(final Employee employee, final Department department) {
        return new OutgoingEmployee(
                employee.employeeId,
                employee.givenName,
                employee.lastName,
                employee.email,
                department.getName(),
                department.getDescription(),
                department.getCompany()
        );
    }

    @DELETE
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> deleteEmployee(@PathParam("employeeId") final String employeeId) {
        return Panache
                .withTransaction(() -> Employee.deleteById(employeeId))
                .map(deleted -> deleted
                        ? Response.ok().status(Response.Status.NO_CONTENT).build()
                        : Response.ok().status(Response.Status.NOT_FOUND).build())
                .onFailure().recoverWithItem(exceptionMapper::handle);
    }
}
