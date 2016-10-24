package models;

import lombok.Data;


@Data
public class Book {
	private String bookTitle;
	private String bookISBN;
	private String dateFrom;
	private String dateTo;

	public Book() {
		this.bookISBN = new RandomString(15).nextString().toUpperCase();
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
}
