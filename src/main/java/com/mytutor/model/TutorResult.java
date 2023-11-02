package com.mytutor.model;

import java.util.Collection;
import java.util.Collections;

public class TutorResult {

    private final int score;
    private final Collection<Answer> originalSubmissions;

    public TutorResult(int score, Collection<Answer> originalSubmissions) {
        this.score = score;
        this.originalSubmissions = originalSubmissions;
    }

    public int getScore() {
        return score;
    }

    public Collection<Answer> getOriginalSubmissions() {
        return Collections.unmodifiableCollection(originalSubmissions);
    }
}
