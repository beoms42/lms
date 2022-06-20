package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Community {
	private int communityNo;
	private String communityTitle;
	private String communityContent;
	private String loginId;
	private String createDate;
	private String updateDate;
	private String communityPw;
}
