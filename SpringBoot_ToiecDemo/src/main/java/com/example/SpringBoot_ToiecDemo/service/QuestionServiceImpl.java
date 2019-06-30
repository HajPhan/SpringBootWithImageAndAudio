package com.example.SpringBoot_ToiecDemo.service;

import com.example.SpringBoot_ToiecDemo.model.QuestionEntity;
import com.example.SpringBoot_ToiecDemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean saveQuestion(QuestionEntity entity) {
        this.questionRepository.save(entity);
        return true;
    }

    @Override
    public Iterable<QuestionEntity> getAllToeic() {
        return this.questionRepository.findAll();
    }

    @Override
    public QuestionEntity getToeicById(int id) {
        return this.questionRepository.findById(id).get();
    }
}
