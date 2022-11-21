package be.dillaerts.funiversity2.api;

import be.dillaerts.funiversity2.domain.dtos.CourseDTO;
import be.dillaerts.funiversity2.domain.dtos.CreateCourseDTO;
import be.dillaerts.funiversity2.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(params = "studyPoints")
    public List<CourseDTO> getAllCourses(@RequestParam int studyPoints) {
        return courseService.getFilteredCourses(studyPoints);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO createNewCourse(@RequestBody CreateCourseDTO createCourseDTO) {
        return courseService.createNewCourse(createCourseDTO);
    }
}
