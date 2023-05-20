package com.example.pfeApi.course;

import com.example.pfeApi.user.User;
import com.example.pfeApi.user.UserRepository;
import com.example.pfeApi.utils.API;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceimp implements CourseService{
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    @Override
    public ResponseEntity<?> save(CourseDto dto) {
        Optional<User> client = userRepository.findById(dto.getClient());
        if (client.isPresent()){
            Optional<User> mentor = userRepository.findById(dto.getMentor());
            if (mentor.isPresent()){
                var course = Course.builder()
                        .client(client.get())
                        .mentor(mentor.get())
                        .date(dto.getDate())
                        .type(dto.getType())
                        .build();
                courseRepository.save(course);
                return API.getResponseEntity("course save successfully",HttpStatus.OK);
            }else {
                return API.getResponseEntity("no mentor matching this call", HttpStatus.BAD_REQUEST);
            }
        }else {
            return API.getResponseEntity("no client matching this call", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllByClient(Integer idClient) {
    Optional<User> client = userRepository.findById(idClient);
    if (client.isPresent()){
        return ResponseEntity.ok(this.courseRepository.findAllByClient(client.get()));
    }else {
        return API.getResponseEntity("no client match this call",HttpStatus.OK);
    }
    }

    @Override
    public ResponseEntity<?> getAllByMentor(Integer idMentor) {
        Optional<User> client = userRepository.findById(idMentor);
        if (client.isPresent()){
            return ResponseEntity.ok(this.courseRepository.findAllByMentor(client.get()));
        }else {
            return API.getResponseEntity("no mentor match this call",HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.courseRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Course> course = this.courseRepository.findById(id);
        if (course.isPresent()){
            return ResponseEntity.ok(course.get());
        }
        return API.getResponseEntity("no course matching this call", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Course> course = this.courseRepository.findById(id);
        if (course.isPresent()){
            this.courseRepository.delete(course.get());
            return API.getResponseEntity("course deleted successfully",HttpStatus.OK);
        }
        else return API.getResponseEntity("no course matching this call",HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getByDate(String date) {
        return ResponseEntity.ok(this.courseRepository.findAllByDate(new Date(date)));
    }
}
