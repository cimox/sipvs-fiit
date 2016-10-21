package models;

import java.util.Date;

public class Book {
	private String bookTitle;
	private String bookISBN;
	private Date dateFrom;
	private Date dateTo;

	public Book(String bookTitle, String bookISBN, Date dateFrom, Date dateTo) {
		this.bookTitle = bookTitle;
		this.bookISBN = bookISBN;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
}
