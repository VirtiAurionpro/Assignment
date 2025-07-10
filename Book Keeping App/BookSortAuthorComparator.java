package com.aurionpro.model;

import java.util.Comparator;

public class BookSortAuthorComparator implements Comparator<BookList> {

	@Override
	public int compare(BookList o1, BookList o2) {
		return o1.getAuthor().compareTo(o2.getAuthor());
	}

}
