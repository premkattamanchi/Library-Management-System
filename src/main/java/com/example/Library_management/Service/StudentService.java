package com.example.Library_management.Service;

import com.example.Library_management.Enums.CardStatus;
import com.example.Library_management.Models.Card;
import com.example.Library_management.Models.Student;
import com.example.Library_management.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    //we need to set all attributes before save into the database
    @Autowired
    StudentRepository studentRepository;
    public String createStudent(Student student){
        Card card=new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);// only saving student entity by cascading effect child entity will be automatically saved
        return "Student and Card added";
    }
}
