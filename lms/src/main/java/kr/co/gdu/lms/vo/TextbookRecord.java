package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class TextbookRecord {
	private int educationNo;
	private int lectureSubjectNo;
	private String textbookSignfileName;
	private String textbookSignfileOriginName;
	private String textbookSignfileType;
	private int textbookSignfileSize;
	private String createDate;
}
