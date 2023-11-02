package com.mytutor.model;

import java.util.UUID;

public class Tutor {

    String tutorId = UUID.randomUUID().toString();

    Questionnaires questionnaires;
    int score = 0;

    public Questionnaires getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Questionnaires questionnaires) {
        this.questionnaires = questionnaires;
    }
}
