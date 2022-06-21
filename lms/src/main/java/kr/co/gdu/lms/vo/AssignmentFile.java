package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class AssignmentFile {
	private int assignmentFileNo;
	private int assignmentExamNo;
	private String loginId;
	private String assignmentFileName;
	private String assignmentFileOriginName;
	private String assignmentFileType;
	private long assignmentFileSize;
	private String assignmentFileCreateDate;
}
