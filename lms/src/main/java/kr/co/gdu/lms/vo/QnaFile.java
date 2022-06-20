package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class QnaFile {
	private int qnaFileNo;
	private int qnaNo;
	private String loginId;
	private String qnaFileName;
	private String qnaFileOriginName;
	private String qnaFileType;
	private int qnaFileSize;
	private String createDate;
}
