package com.example.pfeApi.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CourseDto dto){
        return this.courseService.save(dto);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return this.courseService.getAll();
    }
    @GetMapping("/getAllByClient/{id}")
    public ResponseEntity<?> getAllByClient(@PathVariable("id")Integer id){
        return this.courseService.getAllByClient(id);
    }
    @GetMapping("/getAllByMentor/{id}")
    public ResponseEntity<?> getAllBYMentor(@PathVariable("id")Integer id ){
        return this.courseService.getAllByMentor(id);
    }
    @GetMapping("/getAllByDate/{date}")
    public ResponseEntity<?> getAllByDate(@PathVariable("date")String date){
        return this.courseService.getByDate(date);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        return this.courseService.delete(id);
    }
}
