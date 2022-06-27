package kr.co.gdu.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class ReferenceForm {
	private String lectureName;
	private String referenceTitle;
	private String referenceContent;
	private List<MultipartFile> referenceFileList;
}