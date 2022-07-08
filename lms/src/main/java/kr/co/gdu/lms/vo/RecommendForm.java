package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class RecommendForm {
	private int communityNo;
	private String loginId;
	private String communityTitle;
	private String createDate;
	private int cnt;

}
