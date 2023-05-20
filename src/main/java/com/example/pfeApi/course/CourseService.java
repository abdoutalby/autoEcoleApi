package com.example.pfeApi.course;

import org.springframework.http.ResponseEntity;



public interface CourseService {
    ResponseEntity<?> save(CourseDto dto);
    ResponseEntity<?> getAllByClient(Integer idClient);
    ResponseEntity<?> getAllByMentor(Integer idMentor);

    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> getByDate(String date);

}
