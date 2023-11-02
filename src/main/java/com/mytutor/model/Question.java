package com.mytutor.model;

import java.util.List;

public class Question {

    public Question(Type type, int questionId, String questionText, List<Option> options) {
        this.type = type;
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
    }

    Type type;

    int questionId;

    String questionText;
    List<Option> options;
    public Type getType() {
        return type;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Option> getOptions() {
        return options;
    }

    public static class Option {
        int order;
        String optionText;
        int point;

        public Option(int order, String optionText, int point) {
            this.order = order;
            this.optionText = optionText;
            this.point = point;
        }

        public int getOrder() {
            return order;
        }

        public String getOptionText() {
            return optionText;
        }

        public int getPoint() {
            return point;
        }
    }

    public enum Type {
        SINGLE, MULTIPLE
    }
}
