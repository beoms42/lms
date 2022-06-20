package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Textbook {
	private int textbookNo;
	private String textbookName;
	private String textbookPublisher;
	private String textbookWriter;
	private int textbookPrice;
	private int textbookPage;
	private String createDate;
	private String updateDate;
}
