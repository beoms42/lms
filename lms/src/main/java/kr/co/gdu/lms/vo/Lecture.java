package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Lecture {
	private String lectureName;
	private String teacher;
	private String manager;
	private String lectureStartDate;
	private String lectureEndDate;
	private String lectureRoomName;
	private int lectureStudentCapacity;
	private String loginId;
	private String lectureActive;
	private String createDate;
	private String updateDate;
}
