package com.example.SpringBoot_ToiecDemo.service;

import com.example.SpringBoot_ToiecDemo.model.QuestionEntity;

public interface QuestionService {
    boolean saveQuestion(QuestionEntity entity);

    Iterable<QuestionEntity> getAllToeic();

    QuestionEntity getToeicById(int id);
}
