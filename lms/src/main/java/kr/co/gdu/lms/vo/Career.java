package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Career { // 경력
	private String loginId;
	private String companyName;
	private int longServiceYear;
	private String service;
	private int positionNo;
}
