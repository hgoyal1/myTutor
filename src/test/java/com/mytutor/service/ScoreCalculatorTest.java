package com.mytutor.service;

import com.mytutor.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class ScoreCalculatorTest {

    static ScoreCalculator scoreCalculator;

    @BeforeAll
    public static void init() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    void calculate() {

        // Given a tutor with default questionnaires and answer set
        Tutor tutor = new Tutor();
        Questionnaires questionnaires = getQuestionnaires();
        tutor.setQuestionnaires(questionnaires);

        Map<Integer, Answer> answers = buildAnswers(questionnaires);

        // When tutor calculate score service is called
        TutorResult result = scoreCalculator.calculate(tutor, answers);

        // Then the returned Score is same as expected score
        Assertions.assertEquals(5, result.getScore());
    }

    @Test
    void testCalculateWhenUserSelectedMultipleChoiceForSingleType() {
        // Given a tutor with default questionnaires and answer set
        Tutor tutor = new Tutor();
        Questionnaires questionnaires = getQuestionnaires();
        tutor.setQuestionnaires(questionnaires);

        Map<Integer, Answer> answers = buildAnswers(questionnaires);

        // Select multiple options for single choice question
        Question question = questionnaires.getQuestions().stream().filter(ques -> ques.getType() == Question.Type.SINGLE).findAny().get();
        Answer answer = new Answer(question.getQuestionId(), Arrays.asList(question.getOptions().get(1), question.getOptions().get(2)));
        answers.put(2, answer);

        // When tutor calculate score service is called
        TutorResult result = scoreCalculator.calculate(tutor, answers);

        // Then the returned Score is same as expected score and should not include score from single choice question
        Assertions.assertEquals(3, result.getScore());
    }

    @Test
    void testCalculateUserAnsweredOnlyMultipleChoiceQuestion() {
        // Given a tutor with default questionnaires and answer set
        Tutor tutor = new Tutor();
        Questionnaires questionnaires = new Questionnaires();
        questionnaires.addQuestions(buildMultipleChoiceQuestion());
        tutor.setQuestionnaires(questionnaires);

        Map<Integer, Answer> answers = buildAnswers(questionnaires);

        // When tutor calculate score service is called
        TutorResult result = scoreCalculator.calculate(tutor, answers);

        // Then the returned Score is same as expected score and should not include score from single choice question
        Assertions.assertEquals(3, result.getScore());
    }

    @Test
    void testCalculateUserAnsweredOnlySingleChoiceQuestion() {
        // Given a tutor with default questionnaires and answer set
        Tutor tutor = new Tutor();
        Questionnaires questionnaires = new Questionnaires();
        questionnaires.addQuestions(buildSingleChoiceQuestion());
        tutor.setQuestionnaires(questionnaires);

        Map<Integer, Answer> answers = buildAnswers(questionnaires);

        // When tutor calculate score service is called
        TutorResult result = scoreCalculator.calculate(tutor, answers);

        // Then the returned Score is same as expected score and should not include score from single choice question
        Assertions.assertEquals(2, result.getScore());
    }

    private Map<Integer, Answer> buildAnswers(Questionnaires questionnaires) {

        return questionnaires.getQuestions().stream()
                .map(this::getAnswer)
                .collect(Collectors.toMap(Answer::getQuestionId, Function.identity()));
    }

    private Answer getAnswer(Question question) {
        if (Question.Type.MULTIPLE == question.getType()) {
            question.getOptions().remove(3);
            List<Question.Option> selectedOptions = question.getOptions();
            return new Answer(question.getQuestionId(), selectedOptions);
        } else {
            return new Answer(question.getQuestionId(), Collections.singletonList(question.getOptions().get(2)));
        }
    }

    private Questionnaires getQuestionnaires() {
        Questionnaires questionnaires = new Questionnaires();

        questionnaires.addQuestions(buildMultipleChoiceQuestion());
        questionnaires.addQuestions(buildSingleChoiceQuestion());

        return questionnaires;
    }

    private Question buildSingleChoiceQuestion() {
        List<Question.Option> optionList = singleQuestionOptionList();
        return new Question(Question.Type.SINGLE, 2, "How much overall tutoring experience do you have?", optionList);
    }

    private List<Question.Option> singleQuestionOptionList() {
        List<Question.Option> optionList = new ArrayList<>();
        optionList.add(new Question.Option(1, "0-1 years", 0));
        optionList.add(new Question.Option(2, "1-2 years", 1));
        optionList.add(new Question.Option(3, "3 or more years", 2));
        optionList.add(new Question.Option(4, "None", 0));

        return optionList;
    }

    private Question buildMultipleChoiceQuestion() {

        List<Question.Option> optionList = multipleQuestionOptionList();

        return new Question(Question.Type.MULTIPLE, 1, "What kind of tutoring experience do you have?", optionList);
    }

    private List<Question.Option> multipleQuestionOptionList() {

        List<Question.Option> optionList = new ArrayList<>();
        optionList.add(new Question.Option(1, "Online tutoring", 1));
        optionList.add(new Question.Option(2, "Home schooling", 1));
        optionList.add(new Question.Option(3, "After school club", 1));
        optionList.add(new Question.Option(4, "None", 0));

        return optionList;
    }
}