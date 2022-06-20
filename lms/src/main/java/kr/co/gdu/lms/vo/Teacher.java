package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class Teacher {
	private String loginId;
	private String teacherName;
	private String teacherBirth;
	private String address;
	private String detailAddr;
	private String teacherPhone;
	private String lastLoginDate;
	private String graduate;
	private String createDate;
	private String updateDate;
}
