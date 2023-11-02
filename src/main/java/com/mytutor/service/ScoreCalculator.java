package com.mytutor.service;

import com.mytutor.model.Answer;
import com.mytutor.model.Question;
import com.mytutor.model.Tutor;
import com.mytutor.model.TutorResult;

import java.util.Map;

public class ScoreCalculator {

    public TutorResult calculate (Tutor tutor, Map<Integer, Answer> answers) {
        int score;

        score = tutor.getQuestionnaires().getQuestions().stream().mapToInt(question -> {
            Answer answer = answers.get(question.getQuestionId());
            if (Question.Type.SINGLE == question.getType() && answer.getSelectedOptions().size() > 1) {
                return 0;
            }
            return answer.getSelectedOptions().stream().mapToInt(Question.Option:: getPoint).sum();
        }).sum();

        return new TutorResult(score, answers.values());
    }
}
