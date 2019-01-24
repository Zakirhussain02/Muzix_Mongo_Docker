package com.stackroute.repository;

import com.stackroute.domain.Muzix;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//muzix repo for connecting with DB

@Repository
public interface MuzixRepository extends MongoRepository<Muzix,Integer> {
//    @Query("SELECT m FROM Muzix m WHERE m.trackName= ?1")
    List<Muzix> findBytrackName(String trackName);

}

