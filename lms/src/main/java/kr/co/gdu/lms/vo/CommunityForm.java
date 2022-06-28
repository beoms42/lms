package kr.co.gdu.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CommunityForm {

	private String communityTitle;
	private String communityContent;
	private String loginId;
	private String communityPw;
	private List<MultipartFile> communityFileList;
	
	
	
}
