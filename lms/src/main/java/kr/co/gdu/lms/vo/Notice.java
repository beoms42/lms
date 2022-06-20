package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String loginId;
	private String createDate;
	private String updateDate;
}
