package com.example.SpringBoot_ToiecDemo.repository;

import com.example.SpringBoot_ToiecDemo.model.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity,Integer> {
}
