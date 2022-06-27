package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class NoticeFile {
	private int noticeFileNo;
	private int noticeNo;
	private String loginId;
	private String noticeFileName;
	private String noticeFileOriginName;
	private String noticeFileType;
	private long noticeFileSize;
	private String noticeFileCreateDate;
}
