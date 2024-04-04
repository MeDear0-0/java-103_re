package com.example.int103_studenttest;

import java.util.Comparator;
import java.util.function.Predicate;

public class CommentPlus999 extends Comment {
    final Grade999 grade999;

    public static final Comparator<CommentPlus999> GRADE999_COMPARATOR = Comparator.comparing(comment -> comment.grade999);

    public CommentPlus999(String message, Grade999 grade) {
        super(message);
        this.grade999 = grade != null ? grade : Grade999.NONE;
    }

    public static Predicate<CommentPlus999> match999(Grade999 grade) {
        return comment -> comment.grade999 == grade;
    }

    @Override
    protected String getContent() {
        return super.getContent() + " Grade: " + grade999;
    }
}
