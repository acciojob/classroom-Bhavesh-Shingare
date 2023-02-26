package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
@Repository

public class StudentRepository {
    public HashMap<String,Student> studentMap;
    public HashMap<String,Teacher> teacherMap;
    public HashMap<String,List<String>> teacherstudentMap;

    public StudentRepository(){
        this.studentMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        this.teacherstudentMap = new HashMap<>();
    }
    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            studentMap.put(student,studentMap.get(student));
            teacherMap.put(teacher,teacherMap.get(teacher));
        }
        List<String> studentList = new ArrayList<>();
        if(teacherstudentMap.containsKey(teacher))
            studentList=teacherstudentMap.get(teacher);

        studentList.add(student);
        teacherstudentMap.put(teacher,studentList);
    }

    public Student getStudentByName(String name) {
        if(studentMap.containsKey(name)){
            return studentMap.get(name);
        }
        return null;
    }

    public Teacher getTeacherByName(String name) {
        if(teacherMap.containsKey(name))
            return teacherMap.get(name);
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentList = new ArrayList<>();
        if(teacherstudentMap.containsKey(teacher)){
            studentList=teacherstudentMap.get(teacher);
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        List<String> studentsList = new ArrayList<>();
        for(String student : studentMap.keySet()){
            studentsList.add(student);
        }
        return studentsList;
    }


    public void deleteTeacherByName(String teacher) {
        List<String> studentList = new ArrayList<>();
        if(teacherstudentMap.containsKey(teacher)){
            studentList= teacherstudentMap.get(teacher);
            for(String student: studentList){
                if(studentMap.containsKey(student))
                    studentMap.remove(student);
            }
            if(teacherstudentMap.containsKey(teacher))
                teacherstudentMap.remove(teacher);
        }
        if(teacherMap.containsKey(teacher))
            teacherMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        HashSet<String> studentSet = new HashSet<String>();

        for(String teacher: teacherstudentMap.keySet()){
            for(String student: teacherstudentMap.get(teacher)){
                studentSet.add(student);
            }
        }

        for(String student: studentSet){
            if(studentMap.containsKey(student)){
                studentMap.remove(student);
            }
        }
    }
}
