package com.example.int103_studenttest;

import java.util.Collection;
import java.util.Iterator;

public interface Commentable extends Iterable<CommentPlus999> {
    default boolean addComment(String message) {
        return addComment(message, null);
    }

    boolean addComment(String message, Grade999 grade);

    boolean removeComment(String message);

    Iterator<CommentPlus999> iterator();

    Collection<String> extract(Grade999 grade);

    void sort();
}