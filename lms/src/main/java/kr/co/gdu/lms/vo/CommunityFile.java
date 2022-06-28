package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class CommunityFile {
	private int communityFileNo;
	private int communityNo;
	private String loginId;
	private String communityFileName;
	private String communityFileType;
	private String communityFileOriginName;
	private long communityFileSize;
	private String communityFileCreateDate;
}
