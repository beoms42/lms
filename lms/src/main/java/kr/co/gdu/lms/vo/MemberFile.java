package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class MemberFile {
	private int memberFileNo;
	private String loginId;
	private String memberFileName;
	private String memberFileOriginName;
	private String memberFileType;
	private long memberFileSize;
	private String memberFileCreateDate;
}
