package be.dillaerts.funiversity2.service;

import be.dillaerts.funiversity2.domain.Professor;
import be.dillaerts.funiversity2.domain.ProfessorRepository;
import be.dillaerts.funiversity2.domain.dtos.CreateProfessorDTO;
import be.dillaerts.funiversity2.domain.dtos.ProfessorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.getProfessors().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProfessorDTO createProfessor(CreateProfessorDTO createProfessorDTO) {
        return toDto(professorRepository.addProfessor(new Professor(createProfessorDTO.getFirstName(), createProfessorDTO.getLastName())));
    }


    public ProfessorDTO updateProfessor(ProfessorDTO professorDTO) {
        return toDto(professorRepository.updateProfessor(professorDTO));
    }

    public ProfessorDTO getProfessorById(String id) {
        return toDto(professorRepository.getProfessorById(id));
    }

    public ProfessorDTO toDto(Professor professor) {
        return new ProfessorDTO().setId(professor.getId()).setFirstName(professor.getFirstName()).setLastName(professor.getLastName());
    }

    public ProfessorDTO patchProfessor(String id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Professor professorToPatch = professorRepository.getProfessorById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(professorToPatch, JsonNode.class));
        return toDto(professorRepository.updateProfessor(objectMapper.treeToValue(patched, ProfessorDTO.class)));
    }
}
