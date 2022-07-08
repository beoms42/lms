package kr.co.gdu.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	// 희원 - Recommand Action - get방식
	@GetMapping("/loginCheck/recommandAction")
	public String recommandAction(@RequestParam (name = "communityNo") int communityNo
								, HttpSession session) {
		
		String loginId = (String) session.getAttribute("sessionId"); // 현재 세션의 로그인아이디얻어오기
		
		log.debug(CF.PHW+"CommunityController.recommandAction.get loginId : "+loginId+CF.RS );
		log.debug(CF.PHW+"CommunityController.recommandAction.get communityNo : "+communityNo+CF.RS );
		
		int checkRow = communityService.recommandAction(communityNo, loginId);
		log.debug(CF.PHW+"CommunityController.recommandAction.get checkRow : "+checkRow+CF.RS );
		
		if(checkRow == 1) {
			return "redirect:/loginCheck/getCommunityOne?communityNo="+communityNo+"&checkRow="+checkRow;
		} 
		
		return "redirect:/loginCheck/getCommunityOne?communityNo="+communityNo;
	}
	
	// 희원 - modifyCommunityComment 액션
	@PostMapping("/loginCheck/modifyCommunityComment")
	public String modifyCommunityComment(CommunityComment communityComment) {
		log.debug(CF.PHW+"CommunityController.modifyCommunityComment.get communitycomment : "+communityComment+CF.RS );
		
		communityService.modifyCommunityComment(communityComment);
		
		return "redirect:/loginCheck/getCommunityOne?communityNo="+communityComment.getCommunityNo();
	}
	
	// 희원 - addCommunityComment 액션
	@PostMapping("/loginCheck/addCommunityComment")
	public String addCommunityComment(CommunityComment communityComment) {
		log.debug(CF.PHW+"CommunityController.removeCommunityComment.get communitycomment : "+communityComment+CF.RS );
		
		communityService.addCommunityComment(communityComment);
		
		return "redirect:/loginCheck/getCommunityOne?communityNo="+communityComment.getCommunityNo();
	}
	
	// 희원 - removeCommunityComment 액션
	@GetMapping("/loginCheck/removeCommunityComment")
	public String removeCommunityComment(CommunityComment communityComment) {
		log.debug(CF.PHW+"CommunityController.removeCommunityComment.get communityComment : "+communityComment+CF.RS );
		
		communityService.removeCommunityComment(communityComment);
		
		return "redirect:/loginCheck/getCommunityOne?communityNo="+communityComment.getCommunityNo();
			
		}
	
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
		
		// if문 분기하기 row=1시 어디로 보내고 0시 어디로 보내고..
		int row = communityService.modifyCommunity(communityForm, path, communityNo, communityPw);
		if(row == 1) {
			return "redirect:/loginCheck/getCommunityOne?communityNo="+communityNo;
		} else {
			return "redirect:/loginCheck/modifyCommunity?communityNo="+communityNo;
		}
		
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
		
		if(row == 1) {
			return "redirect:/loginCheck/getCommunityListByPage";
		} else {
			return "redirect:/loginCheck/remove"
					+ "Community?communityNo="+communityNo;
		}
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
								, @RequestParam (name="communityNo") int communityNo
								, @RequestParam (name="commentCurrentPage", defaultValue = "1") int commentCurrentPage
								, @RequestParam (name="commentRowPerPage", defaultValue = "10") int commentRowPerPage
								, @RequestParam (name="checkRow", defaultValue = "0") int checkRow) {
								
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get communityNo : "+communityNo+CF.RS ); // 디버깅
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get checkRow : "+checkRow+CF.RS ); // 디버깅
		
		Map<String, Object> map = new HashMap<>(); // map에 communityNo, commentCurrentPage, commentRowPerPage 담기
		map.put("communityNo", communityNo);
		map.put("commentCurrentPage", commentCurrentPage);
		map.put("commentRowPerPage", commentRowPerPage);
		
		Map<String, Object> returnMap = communityService.getCommunityAndCommentList(map); // service단에서 returnMap 값 가져오기
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get returnMap : "+returnMap+CF.RS ); // 디버깅
		
		int recommendCnt = communityService.getRecommendCnt(communityNo);
		log.debug(CF.PHW+"CommunityController.getCommunityOne.get recommendCnt : "+recommendCnt+CF.RS ); // 디버깅
		
		// view단으로 전달할 값 담기
		model.addAttribute("communityNo", returnMap.get("communityNo")); 
		model.addAttribute("communityMember", returnMap.get("communityMember")); 
		model.addAttribute("communityFileList", returnMap.get("communityFileList"));
		model.addAttribute("communityCommentList", returnMap.get("communityCommentList"));
		model.addAttribute("commentCurrentPage", returnMap.get("commentCurrentPage"));
		model.addAttribute("commentLastPage", returnMap.get("commentLastPage"));
		model.addAttribute("recommendCnt", recommendCnt); // 추천수 
		model.addAttribute("checkRow", checkRow); // 추천중복여부
		
		
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

		List<RecommendForm> recommendList = communityService.selectRecommendList();
		log.debug(CF.PHW+"CommunityController.getCommunityList.get recommendList : "+recommendList+CF.RS );
		
		
		model.addAttribute("communityList", map.get("communityList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("recommendList", recommendList);
		
		
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
