package com.aurionpro.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.BookList;
import com.aurionpro.model.BookSortAuthorComparator;
import com.aurionpro.model.BookSortTitleComparator;

public class BookListTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<BookList> books = new ArrayList<>();
		while (true) {
			BookList bookList = new BookList();
			System.out.println("\nWelcome to our book club!!!");
			System.out.println("\n1. Add a new book.\r\n" + "2. Issue a book by ID.\r\n"
					+ "3. Display all available books.\r\n" + "4. Display all issued books.\r\n"
					+ "5. Return a book.\r\n" + "6. Sort Books.\r\n" + "7. Filter Books.\r\n" + "8. Exit.");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				String bookID = bookList.generatebookID();
				System.out.println("Enter Book Title");
				String name = scanner.next();
				boolean check = bookList.checkBook(name, books);
				if (check) {
					System.out.println("Enter Book Author");
					String author = scanner.next();
					System.out.println("Enter Book price");
					double price = scanner.nextDouble();
					System.out.println("Enter book genre");
					String genre = scanner.next();
					System.out.println("Enter book Publication");
					String publication = scanner.next();
					books.add(new BookList(bookID, name, author, price, genre, publication, "Available"));
				}
				break;
			}
			case 2: {
				System.out.println("Enter BookID:");
				String bookId = scanner.next();
				bookList.issueBook(bookId, books);
				System.out.println("Congratulations your book has been issued.");
				break;
			}
			case 3: {
				bookList.displayAvailable(books);
				break;
			}
			case 4: {
				bookList.displayIssue(books);
				break;
			}
			case 5: {
				System.out.println("Please enter book id");
				String bookId = scanner.next();
				bookList.returnBook(bookId, books);
				System.out.println("Thank you returning our book.Hope you enjoyed reading it.");
				break;
			}
			case 6: {
				System.out.println("\ta. Ascending Order of Author\r\n" + "\tb. Descending Order of Title\r\n");
				System.out.println("Enter your choice");
				char choice2 = scanner.next().charAt(0);
				switch (choice2) {
				case 'a': {
					Collections.sort(books, new BookSortAuthorComparator());
					for (BookList book : books)
						System.out.println(book);
					break;
				}
				case 'b': {
					Collections.sort(books, new BookSortTitleComparator());
					for (BookList book : books)
						System.out.println(book);
					break;
				}
				}
				break;
			}
			case 7: {
				System.out.println("\ta. Filter according to author\r\n" + "\tb. Filter according to genre\r\n");
				System.out.println("Enter your choice");
				char choice3 = scanner.next().charAt(0);
				switch (choice3) {
				case 'a': {
					System.out.println("Enter the author name:");
					String authorInput = scanner.next();
					BookList.FilterAuthor(authorInput, books);
					break;
				}
				case 'b': {
					System.out.println("Enter the genre:");
					String genreInput = scanner.next();
					BookList.FilterGenre(genreInput, books);
					break;
				}
				}
				break;
			}
			case 8: {
				return;
			}
			}
		}

	}
}
