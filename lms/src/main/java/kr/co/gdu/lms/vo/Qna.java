package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Qna {
	private int qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private String loginId;
	private String createDate;
	private String qnaDisclosure;
	private String qnaKind;
}
