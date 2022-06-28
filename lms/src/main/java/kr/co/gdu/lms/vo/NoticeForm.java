package kr.co.gdu.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeForm {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String loginId;
	private List<MultipartFile> noticeFileList;
}
