package be.dillaerts.funiversity2.domain;

import be.dillaerts.funiversity2.domain.dtos.CreateProfessorDTO;
import be.dillaerts.funiversity2.domain.dtos.ProfessorDTO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProfessorRepository {
    private final ConcurrentHashMap<String, Professor> professors;

    public ProfessorRepository() {
        this.professors = new ConcurrentHashMap<>();
    }

    public Professor addProfessor(Professor professor) {
        professors.put(professor.getId(), professor);
        return professor;
    }

    public Collection<Professor> getProfessors() {
        return professors.values();
    }

    public Professor updateProfessor(ProfessorDTO professorDTO) {
        Professor professorToUpdate = professors.get(professorDTO.getId());
        if (professorToUpdate == null) throw new NoSuchElementException("There is no record for id: " + professorDTO.getId());
        professorToUpdate.setFirstName(professorDTO.getFirstName());
        professorToUpdate.setLastName(professorDTO.getLastName());
        return professorToUpdate;
    }

    public Professor getProfessorById(String id) {
        Professor professorByID = professors.get(id);
        if (professorByID == null) throw new NoSuchElementException("There is no record for id: " + id);
        return professorByID;
    }
}