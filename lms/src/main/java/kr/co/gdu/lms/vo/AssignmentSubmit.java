package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class AssignmentSubmit {
	private int assignmentExamNo;
	private int educationNo;
	private String assignmentSignfileName;
	private String assignmentSignfileOriginName;
	private String assignmentSignfileType;
	private int assignmentSignfileSize;
	private String createDate;
	private String updateDate;
	private int assignmentSubmitScore;
}
