package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Dept { //부서
	private int deptNo;
	private String deptName;
	private String deptLocation;
	private String createDate;
	private String updateDate;
}
