package co.masterbridge.website.controller;

import co.masterbridge.website.exception.CourseNotExistException;
import co.masterbridge.website.model.Course;
import co.masterbridge.website.model.CourseSearch;
import co.masterbridge.website.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/courses")
public class CourseApiController {

    private CourseService courseService;

    @Autowired
    public CourseApiController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Course> getCourseList(){
        return courseService.getAllCourses();
    }

    @RequestMapping(method = RequestMethod.POST)
    public long createCourse() {
        Course course = courseService.createCourse();
        return course.getCourseId();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{courseId}")
    public Course getCourseById(@PathVariable long id) throws CourseNotExistException {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return course;
        } else {
            throw new CourseNotExistException();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/find")
    public Collection<Course> findCourses(@RequestBody CourseSearch courseSearch) {
        return courseService.findCourses(courseSearch);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{courseId}")
    public long updateCourse(@PathVariable long id) {
        Course course = courseService.getCourseById(id);
        courseService.updateCourse(course);
        return course.getCourseId();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{courseId}")
    public long removeCourse(@PathVariable long id) {
        Course course = courseService.getCourseById(id);
        courseService.updateCourse(course);
        return course.getCourseId();
    }
}
