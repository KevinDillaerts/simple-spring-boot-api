package be.dillaerts.funiversity2.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ProfessorDTO {

    private String id;

    private String firstName;

    private String lastName;


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfessorDTO setId(String id) {
        this.id = id;
        return this;
    }


    public ProfessorDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ProfessorDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String toString() {
        return "ProfessorDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
