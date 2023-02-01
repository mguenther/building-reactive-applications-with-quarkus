package net.mguenther.reactive.employee;

import static java.lang.String.format;

public class EmployeeNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE_TEMPLATE = "There is no employee with ID '%s'.";

    public EmployeeNotFoundException(final String employeeId) {
        super(format(ERROR_MESSAGE_TEMPLATE, employeeId));
    }
}
