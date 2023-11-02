package com.mytutor.model;

import java.util.ArrayList;
import java.util.List;

public class Questionnaires {

    List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestions(Question question) {
        this.questions.add(question);
    }
}
