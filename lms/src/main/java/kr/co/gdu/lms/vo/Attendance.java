package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Attendance {
	private int attendanceNo;
	private int scheduleNo;
	private int educationNo;
	private String attendanceRecord;
}
