package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class ReferenceFile {
	private int referenceFileNo;
	private int referenceNo;
	private String referenceFileName;
	private String referenceFileOrginName;
	private String referenceFileType;
	private long referenceFileSize;
	private String createDate;
}
