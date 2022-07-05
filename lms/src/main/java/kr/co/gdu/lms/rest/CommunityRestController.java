package kr.co.gdu.lms.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommunityRestController {
	@Autowired CommunityService communityService;
	
	// 커뮤니티 파일 수정시 삭제
	@GetMapping("/removeCommunityfileOne")
	public String removeCommunityfileOne(Model model
										, HttpServletRequest request
										, @RequestParam (name="communityFileNo") int communityFileNo
										, @RequestParam (name="communityFileName") String communityFileName) {
		String path = request.getServletContext().getRealPath("/file/communityFile/");
		
		log.debug(CF.PHW+"CommunityRestController.removeCommunityfileOne.path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityRestController.removeCommunityfileOne.communityFileNo : "+communityFileNo+CF.RS );
		log.debug(CF.PHW+"CommunityRestController.removeCommunityfileOne.communityFileName : "+communityFileName+CF.RS );
		
		int row = communityService.removeCommunityFileOne(path, communityFileNo, communityFileName);
		log.debug(CF.PHW+"CommunityRestController.removeCommunityfileOne.row : "+row+CF.RS );
		
		return row+"";
		
	}
	
}
