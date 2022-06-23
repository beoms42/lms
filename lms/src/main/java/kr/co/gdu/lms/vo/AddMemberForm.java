package kr.co.gdu.lms.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddMemberForm { 
	private String loginId;
	private String loginPw;
	private String name;
	private String gender;
	private String birth;
	private String email;
	private String phone;
	private String address;
	private String detailAddr;
	private MultipartFile customFile;
	private String graduate;
	private String military;
	private String addChk;
	private String level;
	private int deptNo;
	private int positionNo;
}
