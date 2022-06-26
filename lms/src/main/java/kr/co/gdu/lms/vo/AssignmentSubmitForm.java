package kr.co.gdu.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class AssignmentSubmitForm {
	private String assignmentSubmitContent;
	private List<MultipartFile> assignmentSubmitFileList;
	private String loginId;
}
