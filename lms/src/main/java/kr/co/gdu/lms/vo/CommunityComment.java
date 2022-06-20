package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class CommunityComment { //커뮤니티 댓글
	private String loginId;
	private String createDate;
	private String communityCommentContent;
	private int communityNo;
	private String updateDate;
}
