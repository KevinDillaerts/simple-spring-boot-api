package be.dillaerts.funiversity2.api;

import be.dillaerts.funiversity2.domain.dtos.CreateProfessorDTO;
import be.dillaerts.funiversity2.domain.dtos.ProfessorDTO;
import be.dillaerts.funiversity2.service.ProfessorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessorDTO> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorDTO createProfessor(@RequestBody CreateProfessorDTO createProfessorDTO) {
        return professorService.createProfessor(createProfessorDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDTO getProfessorById(@PathVariable String id) {
        return professorService.getProfessorById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDTO updateProfessor(@PathVariable String id, @RequestBody CreateProfessorDTO createProfessorDTO) {
        return professorService.updateProfessor(
                new ProfessorDTO().setId(id).setFirstName(createProfessorDTO.getFirstName()).setLastName(createProfessorDTO.getLastName()));
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDTO patchProfessor(@PathVariable String id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        return professorService.patchProfessor(id, patch);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public void NoSuchElementException(NoSuchElementException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }
}
