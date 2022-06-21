package kr.co.gdu.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AssignmentExam {
	private int assignmentExamNo;
	private String lectureName;
	private String assignmentExamTitle;
	private String assignmentExamContent;
	private String assignmentDeadLine;
	private String createDate;
	private List<MultipartFile> assignmentFileList;
	private String loginId;

}
