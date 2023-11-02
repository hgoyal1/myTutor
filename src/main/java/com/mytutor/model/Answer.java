package com.mytutor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answer {

    int questionId;
    List<Question.Option> selectedOptions = new ArrayList<>();

    public Answer(int questionId, List<Question.Option> selectedOptions) {
        this.questionId = questionId;
        this.selectedOptions = selectedOptions;
    }

    public int getQuestionId() {
        return questionId;
    }

    public List<Question.Option> getSelectedOptions() {
        return Collections.unmodifiableList(selectedOptions);
    }
}
