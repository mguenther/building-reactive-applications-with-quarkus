package net.mguenther.reactive;

import net.mguenther.reactive.department.DepartmentNotFoundException;
import net.mguenther.reactive.employee.EmployeeNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import java.util.Map;

@ApplicationScoped
public class ExceptionMapper {

    private static final Map<Class<? extends Exception>, Response.Status> EXCEPTION_TO_STATUS_CODE = Map.of(
            EmployeeNotFoundException.class, Response.Status.NOT_FOUND,
            DepartmentNotFoundException.class, Response.Status.NOT_FOUND,
            PersistenceException.class, Response.Status.CONFLICT
    );

    public Response handle(final Throwable cause) {
        final var status = EXCEPTION_TO_STATUS_CODE.getOrDefault(cause.getClass(), Response.Status.INTERNAL_SERVER_ERROR);
        return Response.status(status).build();
    }
}
