package com.aurionpro.model;

import java.util.Comparator;

public class BookSortTitleComparator implements Comparator<BookList> {

	@Override
	public int compare(BookList o1, BookList o2) {
		// TODO Auto-generated method stub
		return o2.getTitle().compareTo(o1.getTitle());
	}

}
