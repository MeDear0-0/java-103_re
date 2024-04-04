package com.example.int103_studenttest;

import java.util.Collection;
import java.util.Iterator;

public interface CommentablePlus999 extends Commentable {
    default String inspect999() {
        StringBuilder result = new StringBuilder();
        for (CommentPlus999 comment : this) {
            result.append(comment.getContent()).append("\n");
        }
        return result.toString();
    }

    boolean addComment(String message, Grade999 grade);

    boolean removeComment(String message);

    Iterator<CommentPlus999> iterator();

    void sort();

    Collection<String> extract(Grade999 grade);
}