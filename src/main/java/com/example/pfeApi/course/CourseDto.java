package com.example.pfeApi.course;

import lombok.Data;

import java.sql.Date;

@Data
public class CourseDto {
    private Integer mentor ;
    private Integer client;
    private Date date ;
    private String type ;
}
