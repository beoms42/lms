package kr.co.gdu.lms.vo;




import lombok.Data;

@Data
public class AssignmentAddForm {
	private String lectureName;
	private int educationNo;
	private String assignmentExamTitle;
	private String assignmentExamContent;
	private String assignmentDeadLine;
	private String loginId ;
	
	
}
