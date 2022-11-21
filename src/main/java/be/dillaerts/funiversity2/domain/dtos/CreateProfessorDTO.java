package be.dillaerts.funiversity2.domain.dtos;

public class CreateProfessorDTO {
    private final String firstName;
    private final String lastName;

    public CreateProfessorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
