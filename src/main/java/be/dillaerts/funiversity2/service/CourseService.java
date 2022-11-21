package be.dillaerts.funiversity2.service;

import be.dillaerts.funiversity2.domain.Course;
import be.dillaerts.funiversity2.domain.CourseRepository;
import be.dillaerts.funiversity2.domain.ProfessorRepository;
import be.dillaerts.funiversity2.domain.dtos.CourseDTO;
import be.dillaerts.funiversity2.domain.dtos.CreateCourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    public CourseService(CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.getCourses().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CourseDTO createNewCourse(CreateCourseDTO createCourseDTO) {
        int studyPoints = createCourseDTO.getStudyPoints();
        if (studyPoints < 1 || studyPoints > 6) {
            throw new IllegalArgumentException("Study points need to be between 1 and 6 (inclusive)");
        }
        Course newCourse = new Course(createCourseDTO.getName(), studyPoints, professorRepository.getProfessorById(createCourseDTO.getProfessorID()));
        return toDTO(courseRepository.createCourse(newCourse));
    }

    public CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getStudyPoints(), course.getProfessor());
    }

    public List<CourseDTO> getFilteredCourses(int studyPoints) {
        return getAllCourses().stream().filter(courseDTO -> courseDTO.getStudyPoints() == studyPoints).collect(Collectors.toList());
    }
}
