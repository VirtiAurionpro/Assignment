package com.aurionpro.model;

import java.util.List;
import java.util.stream.Collectors;

public class BookList {
	private String bookID;
	private String title;
	private String author;
	private double price;
	private String genre;
	private String publication;
	private String status;
	static int counter = 99;

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		BookList.counter = counter;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "  bookID = " + bookID + "\n" + "  title = " + title + "\n" + "  author = " + author + "\n"
				+ "  price = " + price + "\n" + "  genre = " + genre + "\n" + "  publication = " + publication + "\n"
				+ "  status = " + status;

	}

	public BookList() {
		super();
	}

	public BookList(String bookID, String title, String author, double price, String genre, String publication,
			String status) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.price = price;
		this.setGenre(genre);
		this.publication = publication;
		this.status = status;
	}

	public String generatebookID() {
		counter += 1;
		return "BOOK" + counter;
	}

	public boolean checkBook(String name, List<BookList> books) {
		for (BookList book : books) {
			if (book.title == name) {
				System.out.println("Book has already been added.");
				return false;
			}
		}
		return true;
	}

	public void issueBook(String id, List<BookList> books) {
		for (BookList book : books) {
			if (book.bookID.equals(id)) {
				if (book.status.equals("Available")) {
					book.status = "Issued";
					System.out.println(book);
					break;
				}
				System.out.println("This book has already been issued.");
				break;
			}
		}
	}

	public void displayAvailable(List<BookList> books) {
		for (BookList book : books) {
			if (book.status.equalsIgnoreCase("Available"))
				System.out.println(book);
		}
	}

	public void displayIssue(List<BookList> books) {
		for (BookList book : books) {
			if (book.status.equalsIgnoreCase("Issued"))
				System.out.println(book);
		}
	}

	public void returnBook(String id, List<BookList> books) {
		for (BookList book : books) {
			if (book.bookID.equals(id)) {
				book.status = "Available";
				System.out.println(book);
				break;
			}
		}
	}

	public static void FilterAuthor(String authorInput, List<BookList> books) {
		List<BookList> filteredItems = books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(authorInput))
				.collect(Collectors.toList());
//		filteredItems.forEach(System.out::println);
		filteredItems.forEach(item -> {
			System.out.println(item);
			System.out.println();
		});
	}

	public static void FilterGenre(String genreInput, List<BookList> books) {
		List<BookList> filteredItems = books.stream().filter(book -> book.getGenre().equalsIgnoreCase(genreInput))
				.collect(Collectors.toList());
		filteredItems.forEach(item -> {
			System.out.println(item);
			System.out.println();
		});
	}

}
