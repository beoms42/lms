package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class AssignmentSubmit {
	private int assignmentExamNo;
	private int educationNo;
	private String assignmentSignfileURL;
	private String assignmentSubmitContent;
	private String createDate;
	private String updateDate;
	private String assignmentSubmitScore;
	private String loginId;
	private String assignmentFileName;
	private String assignmentFileOriginName;
	private String assignmentFileType;
}
