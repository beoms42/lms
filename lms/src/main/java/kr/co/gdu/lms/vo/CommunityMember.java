package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class CommunityMember {

	private int communityNo;
	private String communityTitle;
	private String communityContent;
	private String communityPw;
	private String loginId;
	private String memberFileName;
	private String createDate;
	private String updateDate;
	
}
