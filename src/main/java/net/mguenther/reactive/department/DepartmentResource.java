package net.mguenther.reactive.department;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import net.mguenther.reactive.ExceptionMapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/departments")
public class DepartmentResource {

    private final DepartmentManager departments;

    private final ExceptionMapper exceptionMapper;

    @Inject
    public DepartmentResource(final DepartmentManager departments, final ExceptionMapper exceptionMapper) {
        this.departments = departments;
        this.exceptionMapper = exceptionMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Department> getDepartments() {
        return departments.findAll();
    }

    @GET
    @Path("/{departmentName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getDepartment(@PathParam("departmentName") final String departmentId) {
        return departments.findByName(departmentId)
                .onItem().transform(department -> Response.ok(department).build())
                .onFailure().recoverWithItem(exceptionMapper::handle);
    }
}
