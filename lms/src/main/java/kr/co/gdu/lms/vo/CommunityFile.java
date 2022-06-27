package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class CommunityFile {
	private int communityFileNo;
	private int communityNo;
	private String communityFileNmae;
	private String communityFileType;
	private String communityFileOriginName;
	private int communityFileSize;
	private String communityFilecreateDate;
}
