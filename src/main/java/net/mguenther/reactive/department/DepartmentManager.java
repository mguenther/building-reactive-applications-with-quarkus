package net.mguenther.reactive.department;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class DepartmentManager {

    private final Map<String, Department> cachedDepartments;

    public DepartmentManager() {
        this.cachedDepartments = new HashMap<>();
    }


    public Multi<Department> findAll() {
        return Multi.createFrom().items(cachedDepartments.values().stream());
    }

    public Uni<Department> findByName(final String departmentName) {
        return Uni
                .createFrom().optional(get(departmentName))
                .onItem().ifNull().failWith(() -> new DepartmentNotFoundException(departmentName));
    }

    private Optional<Department> get(final String departmentName) {
        return Optional.ofNullable(cachedDepartments.get(departmentName));
    }

    @PostConstruct
    public void populateCache() {
        cachedDepartments.put("B.A 1", new Department(
                "B.A 1",
                "Department for the Misuse of Muggle Artefacts",
                "British Ministry of Magic"));
        cachedDepartments.put("B.M 1", new Department(
                "B.M 1",
                "Department of Magical Law Enforcement",
                "British Ministry of Magic"));
        cachedDepartments.put("B.M 2", new Department(
                "B.M 2",
                "Department of Magical Accidents and Catastrophes",
                "British Ministry of Magic"));
    }
}
