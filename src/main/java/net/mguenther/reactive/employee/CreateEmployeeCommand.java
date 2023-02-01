package net.mguenther.reactive.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEmployeeCommand {

    private final String givenName;

    private final String lastName;

    private final String email;

    private final String department;

    @JsonCreator
    public CreateEmployeeCommand(@JsonProperty("givenName") String givenName,
                                 @JsonProperty("lastName") String lastName,
                                 @JsonProperty("email") String email,
                                 @JsonProperty("department") String department) {
        this.givenName = givenName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }
}
