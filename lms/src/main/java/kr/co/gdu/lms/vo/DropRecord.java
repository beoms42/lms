package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class DropRecord { //중탈 이력
	private int educationNo;
	private String dropRecordDate;
	private String dropRecordReason;
}
