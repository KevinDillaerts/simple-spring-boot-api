package be.dillaerts.funiversity2.domain;

import java.util.UUID;

public class Course {
    private final String id;
    private final String name;
    private final int studyPoints;

    private final Professor professor;

    public Course(String name, int studyPoints, Professor professor) {
        this.id = UUID.randomUUID().toString();
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
