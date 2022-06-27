package kr.co.gdu.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddNoticeForm {
	private String noticeTitle;
	private String noticeContent;
	private String noticePw;
	private String loginId;
	private List<MultipartFile> noticeFileList;
}
