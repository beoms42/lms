package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class AssignmentExam {
	private int assignmentExamNo;
	private String lectureName;
	private String assignmentExamTitle;
	private String assignmentExamContent;
	private String assignmentDeadline;
	private String createDate;
}
