package net.mguenther.reactive.department;

import static java.lang.String.format;

public class DepartmentNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE_TEMPLATE = "There is no department with ID '%s'.";

    public DepartmentNotFoundException(final String departmentId) {
        super(format(ERROR_MESSAGE_TEMPLATE, departmentId));
    }
}
