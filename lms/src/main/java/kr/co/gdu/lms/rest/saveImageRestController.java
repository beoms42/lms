package kr.co.gdu.lms.rest;

    
import java.io.File;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class saveImageRestController {
	
	@PostMapping("/saveImage")
	public String saveImage(@RequestParam(value="file", required=true) MultipartFile file) {
		log.debug(file.getContentType());
		return null;
	}
}
