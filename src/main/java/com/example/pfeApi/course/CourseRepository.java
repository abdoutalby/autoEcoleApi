package com.example.pfeApi.course;

import com.example.pfeApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course , Long> {

    List<Course> findAllByClient(User client);

    List<Course> findAllByMentor(User user);

    List<Course> findAllByDate(Date date);
}
