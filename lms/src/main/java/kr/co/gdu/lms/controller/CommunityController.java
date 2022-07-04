package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.CommunityService;
import kr.co.gdu.lms.vo.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommunityController {
	@Autowired private CommunityService communityService;
	
	// 희원 - modifyCommunity 액션
	@PostMapping("/loginCheck/modifyCommunity")
	public String modifyCommunity(HttpServletRequest request
								, CommunityForm communityForm
								, @RequestParam (name="communityNo") int communityNo
								, @RequestParam (name="communityPw") String communityPw) {
		
		String path = request.getServletContext().getRealPath("/file/communityFile/");
		log.debug(CF.PHW+"CommunityController.modifyCommunity.post path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityController.modifyCommunity.post communityNo : "+communityNo+CF.RS );
		log.debug(CF.PHW+"CommunityController.modifyCommunity.post communityPw : "+communityPw+CF.RS );
		
		// if문 분기하기 row=1시 어디로 보내고 0시 어디로 보내고...
		int row = communityService.modifyCommunity(communityForm, path, communityNo, communityPw);
		log.debug(CF.PHW+"CommunityController.modifyCommunity.post row : "+row+CF.RS );
		
		return "redirect:/loginCheck/getCommunityOne?communityNo="+communityNo;
	}
	
	
	// 희원 - modifyCommunity 폼
	@GetMapping("/loginCheck/modifyCommunity")
	public String modifyCommunity(@RequestParam (name="communityNo") int communityNo
								, Model model) {
		
		log.debug(CF.PHW+"CommunityController.modifyCommunity.get communityNo : "+communityNo+CF.RS );
		Map<String, Object> map = new HashMap<>();
		map.put("communityNo", communityNo);
		
		Map<String, Object> updateMap = communityService.modifyCommunity(map);
		log.debug(CF.PHW+"CommunityController.modifyCommunity.get updateMap : "+updateMap+CF.RS );
		
		model.addAttribute("communityNo", updateMap.get("communityNo"));
		model.addAttribute("communityMember", updateMap.get("communityMember"));
		model.addAttribute("communityFileList", updateMap.get("communityFileList"));
		
		return "community/modifyCommunity";
	}
	
	// 희원 - removeCommunity 액션
	@PostMapping("/loginCheck/removeCommunity")
	public String removeCommunity(HttpServletRequest request
								, @RequestParam (name="communityNo") int communityNo
								, @RequestParam (name="communityPw") String communityPw) {
		
		String path = request.getServletContext().getRealPath("/file/communityFile/");
		log.debug(CF.PHW+"CommunityController.removeCommunity.post path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityController.removeCommunity.post communityNo : "+communityNo+CF.RS );
		log.debug(CF.PHW+"CommunityController.removeCommunity.post communityPw : "+communityPw+CF.RS );
		
		Community community = new Community();
		community.setCommunityNo(communityNo);
		community.setCommunityPw(communityPw);		
		
		int row = communityService.removeCommunity(communityNo, communityPw, path);
		log.debug(CF.PHW+"CommunityController.removeCommunity.post row : "+row+CF.RS );
		
		return "redirect:/loginCheck/getCommunityListByPage";
	}
	
	
	// 희원 - removeCommunity 폼
	@GetMapping("/loginCheck/removeCommunity")
	public String removeCommunity(Model model
								, @RequestParam(name="communityNo") int communityNo) {
		
		log.debug(CF.PHW+"CommunityController.removeCommunity.get communityNo : "+communityNo+CF.RS );
		
		model.addAttribute("communityNo", communityNo);
		
		return "community/removeCommunity";
	}
	
	
	// 희원- addCommunity 액션
	@PostMapping("/loginCheck/addCommunity")
	public String addCommunity(HttpServletRequest request
							, CommunityForm communityForm) {
		
		String path = request.getServletContext().getRealPath("/file/communityFile/");
		log.debug(CF.PHW+"CommunityController.addCommunity.post path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityController.addCommunity.post communityForm : "+communityForm+CF.RS );
		
		List<MultipartFile> communityFileList = communityForm.getCommunityFileList();
		if(communityFileList != null && communityFileList.get(0).getSize() > 0) {
			for(MultipartFile mf : communityFileList) {
				log.debug(CF.PHW+"CommunityController.addCommunity.post mf.getOriginalFilename() : "+mf.getOriginalFilename()+CF.RS );
			}
		}
		communityService.addCommunity(communityForm, path);
		return "redirect:/loginCheck/getCommunityListByPage";
	}
	
	// 희원 - addCommunity 폼
	@GetMapping("/loginCheck/addCommunity")
	public String addCommunity() {
		return "community/addCommunity";
	}
	
	// 희원 - get방식 communityOne 호출
	@GetMapping("/loginCheck/getCommunityOne")
	public String getCommunityOne(Model model
								, @RequestParam (name="communityNo") int communityNo) {
		
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get communityNo : "+communityNo+CF.RS );
		
		Map<String, Object> map = new HashMap<>();
		map.put("communityNo", communityNo);
		
		Map<String, Object> returnMap = communityService.getCommunityOne(map);
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get communityNo : "+returnMap.get("communityNo")+CF.RS );
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get communityFileList : "+returnMap.get("communityFileList")+CF.RS );
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get communityMember : "+returnMap.get("communityMember")+CF.RS );
		
		model.addAttribute("communityNo", returnMap.get("communityNo"));
		model.addAttribute("communityFileList", returnMap.get("communityFileList"));
		model.addAttribute("communityMember", returnMap.get("communityMember"));
		
		return "community/getCommunityOne";
				
	}
	
	// 희원 - getCommunityListByPage 폼
	@GetMapping("/loginCheck/getCommunityListByPage")
	public String getCommunityList(Model model
								, @RequestParam (name="currentPage", defaultValue = "1") int currentPage
								, @RequestParam (name="rowPerPage", defaultValue = "10") int rowPerPage) {
		
		log.debug(CF.PHW+"CommunityController.getCommunityList.get currentPage : "+currentPage+CF.RS );
		log.debug(CF.PHW+"CommunityController.getCommunityList.get rowPerPage : "+rowPerPage+CF.RS );
		
		Map<String, Object> map = communityService.getCommunityList(currentPage, rowPerPage);
		
		log.debug(CF.PHW+"CommunityController.getCommunityList.get communityList : "+map.get("communityList")+CF.RS );
		log.debug(CF.PHW+"CommunityController.getCommunityList.get lastPage : "+map.get("lastPage")+CF.RS );

		model.addAttribute("communityList", map.get("communityList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "community/getCommunityListByPage";
	}
	
	
	// 영인 - get방식 qnaList호출
	@GetMapping("/loginCheck/getQnaListByPage")
	public String getQnaList(Model model) {
		List<Qna> qnaList = communityService.getQnaList();
		
		log.debug(CF.JYI+"CommunityController.qnaList.get qnaList : "+qnaList+CF.RS);
		
		model.addAttribute("qnaList", qnaList);
		return "community/getQnaListByPage";
	}
}
