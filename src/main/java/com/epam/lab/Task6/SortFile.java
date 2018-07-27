package com.epam.lab.Task6;

import com.epam.lab.Task3.OldCards;

import java.util.Comparator;

public class SortFile implements Comparator<OldCards> {

    @Override
    public int compare(OldCards o1, OldCards o2) {
        return o1.getYear() - o2.getYear();
    }
}
