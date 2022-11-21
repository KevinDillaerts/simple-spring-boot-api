package be.dillaerts.funiversity2.domain.dtos;

import be.dillaerts.funiversity2.domain.Professor;

public class CourseDTO {
    private final String id;
    private final String name;
    private final int studyPoints;

    private final Professor professor;

    public CourseDTO(String id, String name, int studyPoints, Professor professor) {
        this.id = id;
        this.name = name;
        this.studyPoints = studyPoints;
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return studyPoints;
    }

    public Professor getProfessor() {
        return professor;
    }
}
