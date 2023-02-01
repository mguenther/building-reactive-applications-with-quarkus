package net.mguenther.reactive.department;

public class Department {

    private final String name;

    private final String description;

    private final String company;

    public Department(final String name, final String description, final String company) {
        this.name = name;
        this.description = description;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCompany() {
        return company;
    }
}
