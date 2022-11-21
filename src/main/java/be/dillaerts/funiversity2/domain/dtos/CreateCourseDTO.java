package be.dillaerts.funiversity2.domain.dtos;

public class CreateCourseDTO {
    private final String name;
    private final int studyPoints;
    private final String professorID;

    public CreateCourseDTO(String name, int studyPoints, String professorID) {
        this.name = name;
        this.studyPoints = studyPoints;
        this.professorID = professorID;
    }

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return studyPoints;
    }

    public String getProfessorID() {
        return professorID;
    }
}
