package com.example.int103_studenttest;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class CommentList999 implements CommentablePlus999 {
    private final LinkedList<CommentPlus999> comments999 = new LinkedList<>();

    @Override
    public boolean addComment(String message, Grade999 grade) {
        if (message == null) return false;
        return comments999.add(new CommentPlus999(message, grade));
    }

    @Override
    public boolean removeComment(String message) {
        if (message == null) return false;
        Iterator<CommentPlus999> iterator = comments999.iterator();
        while (iterator.hasNext()) {
            CommentPlus999 comment = iterator.next();
            if (comment.getContent().equals(message)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<CommentPlus999> iterator() {
        return comments999.iterator();
    }

    @Override
    public void sort() {
        Collections.sort(comments999, CommentPlus999.GRADE999_COMPARATOR);
    }

    @Override
    public Collection<String> extract(Grade999 grade) {
        Collection<String> extractedComments = new LinkedList<>();
        for (CommentPlus999 comment : comments999) {
            if (comment.grade999 == grade) {
                extractedComments.add(comment.getContent());
            }
        }
        return extractedComments;
    }
}
