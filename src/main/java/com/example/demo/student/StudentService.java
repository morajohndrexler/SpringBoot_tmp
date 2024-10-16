package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

        private final StudentRepository studentRepository;

        @Autowired
    	public StudentService(StudentRepository studentRepository) {this.studentRepository = studentRepository;}

        public List<Student> getStudents(){return studentRepository.findAll();}

        public void addNewStudent(Student student){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

            if(studentOptional.isPresent()){

                throw new IllegalStateException("Email taken!!");
            }
            studentRepository.save(student);
        }

        public void deleteStudent(Long studentId){
            boolean exist = studentRepository.existsById(studentId);
            if(!exist){
                throw new IllegalStateException("Student with ID : " + studentId + " does not exist!");
            }
            studentRepository.deleteById(studentId);
        }
}
