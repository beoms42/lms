package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Login {
	private String loginId;
	private String loginPw;
	private int level;
	private int loginActive;
	private String lastLoginDate;
	private String createDate;
	private String updateDate;
}
