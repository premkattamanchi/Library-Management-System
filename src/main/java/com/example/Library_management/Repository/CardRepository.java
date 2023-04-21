package com.example.Library_management.Repository;

import com.example.Library_management.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository  extends JpaRepository<Card,Integer> {
    //<entity,datatype of the primary key of the entity>

}
