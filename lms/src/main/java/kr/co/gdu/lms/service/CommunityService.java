package kr.co.gdu.lms.service;

import java.io.File;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.CommunityMapper;
import kr.co.gdu.lms.vo.CommunityForm;
import kr.co.gdu.lms.vo.CommunityMember;
import kr.co.gdu.lms.vo.Community;
import kr.co.gdu.lms.vo.CommunityComment;
import kr.co.gdu.lms.vo.CommunityFile;
import kr.co.gdu.lms.vo.Qna;
import kr.co.gdu.lms.vo.Recommend;
import kr.co.gdu.lms.vo.RecommendForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class CommunityService {
	@Autowired private CommunityMapper communityMapper;
	
	// 희원 - 게시글 작성날짜, 오늘날짜 비교해서 게시판에 new 아이콘 띄우기
	public List<Integer> getCreateDateAndToday(){
		List<Integer> todayList = communityMapper.selectCreateDateAndToday(); 
		return todayList;
	}
	
	// 희원 - Community리스트 추천수 많은 5개 게시글 가져오기
	public List<RecommendForm> getRecommendList(){
		List<RecommendForm> recommendList = communityMapper.selectRecommendList();
		return recommendList;
	}
	
	// 희원 - recommandAction 게시물 추천 중복 확인 및 추천하기 
	public int recommandAction(int communityNo, String loginId) {
		
		log.debug(CF.PHW+"CommunityService.recommandAction.communityNo : "+communityNo+CF.RS );
		log.debug(CF.PHW+"CommunityService.recommandAction.loginId : "+loginId+CF.RS );
		
		//유효성체크까지해야하니까 리턴값은 void
		Recommend recommend = new Recommend();
		recommend.setCommunityNo(communityNo);
		recommend.setLoginId(loginId);
		
		int checkRow = communityMapper.selectRecommendCheck(recommend);
		log.debug(CF.PHW+"CommunityService.recommandAction.checkRow : "+checkRow+CF.RS );
		
		// 0일때
		if(checkRow == 0) {
			communityMapper.insertRecommend(recommend);
			log.debug(CF.PHW+"CommunityService.recommandAction.checkRow : 추천 성공!"+CF.RS );
		}
		
		return checkRow;
	}
	
	// 희원 - getRecommendCnt 게시물 추천수 가져오기
	public int getRecommendCnt(int communityNo) {
		int recommendCnt = communityMapper.selectRecommendCnt(communityNo);
		log.debug(CF.PHW+"CommunityService.modifyCommunityComment.recommendCnt : "+recommendCnt+CF.RS );
		
		return recommendCnt;
	}
	
	// 희원 - modifyCommunityComment 
	public void modifyCommunityComment(CommunityComment communityComment) {
		int row = communityMapper.updateCommunityComment(communityComment);
		log.debug(CF.PHW+"CommunityService.modifyCommunityComment.row : "+row+CF.RS );
	}
	
	// 희원 - addCommunityComment
	public void addCommunityComment(CommunityComment communityComment) {
		
		int row = communityMapper.insertCommunityComment(communityComment);
		log.debug(CF.PHW+"CommunityService.addCommunityComment.row : "+row+CF.RS );
	}
	
	// 희원 - removeCommunityComment
	public void removeCommunityComment(CommunityComment communityComment) {
		
		int row = communityMapper.deleteCommunityComment(communityComment);
		log.debug(CF.PHW+"CommunityService.removeCommunityComment.row : "+row+CF.RS );
		
	}
	
	// 희원 -  modifyCommunity 수정 중 파일 삭제(ajax)
	public int removeCommunityFileOne(String path, int communityFileNo, String communityFileName) {
		
		log.debug(CF.PHW+"CommunityService.removeCommunityFileOne.path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityService.removeCommunityFileOne.communityFileNo : "+communityFileNo+CF.RS );
		log.debug(CF.PHW+"CommunityService.removeCommunityFileOne.communityFileName : "+communityFileName+CF.RS );
		
		File file = new File(path+communityFileName);
		if(file.exists()) {
			file.delete();
		}
		
		return communityMapper.deleteCommunityFileOne(communityFileNo);
	}
	
	// 희원 - modifyCommunity 수정
	public int modifyCommunity(CommunityForm communityForm, String path, int communityNo, String communityPw) {
		
		log.debug(CF.PHW+"CommunityService.modifyCommunity.communityForm : "+communityForm+CF.RS );
		log.debug(CF.PHW+"CommunityService.modifyCommunity.path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityService.modifyCommunity.communityNo : "+communityNo+CF.RS );
		log.debug(CF.PHW+"CommunityService.modifyCommunity.communityPw : "+communityPw+CF.RS );
		
		Community community = new Community();
		community.setCommunityTitle(communityForm.getCommunityTitle());
		community.setCommunityContent(communityForm.getCommunityContent());
		community.setCommunityNo(communityNo);
		community.setCommunityPw(communityPw);
		
		int row = communityMapper.updateCommunity(community);
		log.debug(CF.PHW+"CommunityService.modifyCommunity.row : "+row+CF.RS );
		
		if(communityForm.getCommunityFileList() != null && communityForm.getCommunityFileList().get(0).getSize() > 00 && row == 1) {
			for(MultipartFile mf : communityForm.getCommunityFileList()) {
				CommunityFile communityFile = new CommunityFile();
				
				String originName = mf.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf("."));
				
				String fileName = UUID.randomUUID().toString();
				fileName = fileName.replace("-", "");
				fileName = fileName + ext;
				
				communityFile.setCommunityNo(community.getCommunityNo());
				communityFile.setCommunityFileName(fileName);
				communityFile.setCommunityFileType(mf.getContentType());
				communityFile.setCommunityFileSize(mf.getSize());
				communityFile.setCommunityFileOriginName(originName);
				communityFile.setLoginId(communityForm.getLoginId());
				log.debug(CF.PHW+"CommunityService.addCommunity.communityFile : "+communityFile+CF.RS );
				
				communityMapper.insertCommunityFile(communityFile);

				try {
					mf.transferTo(new File(path + fileName));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		
		return row;
	}
	
	
	// 희원 - modifyCommunity 폼
	public Map<String, Object> modifyCommunity(Map<String, Object> map){
		
		int communityNo = (int)map.get("communityNo");
		log.debug(CF.PHW+"CommunityService.modifyCommunity.communityNo : "+communityNo+CF.RS );
		
		CommunityMember communityMember = communityMapper.selectCommunityOne(communityNo);
		List<CommunityFile> communityFileList = communityMapper.selectCommunityFileOne(communityNo);
		
		map.put("communityNo", communityNo);
		map.put("communityMember", communityMember);
		map.put("communityFileList", communityFileList);
		log.debug(CF.PHW+"CommunityService.modifyCommunity.map : "+map+CF.RS );
		
		return map;
	}
	
	// 희원 - removeCommunity 
	public int removeCommunity(int communityNo, String communityPw, String path) {
		List<String> communityFileList = communityMapper.selectCommunityfileNameList(communityNo);
		
		log.debug(CF.PHW+"CommunityService.removeCommunity.communityNo : "+communityNo+CF.RS );
		log.debug(CF.PHW+"CommunityService.removeCommunity.communityPw : "+communityPw+CF.RS );
		log.debug(CF.PHW+"CommunityService.removeCommunity.path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityService.removeCommunity.communityFileList : "+communityFileList+CF.RS );
		
		for(String filename : communityFileList) {
			File f = new File(path+filename);
			if(f.exists()) {
				f.delete();
			}
		}
		
		communityMapper.deleteCommunityFileList(communityNo); // 커뮤니티 파일 삭제
		communityMapper.deleteRecommendByCommunityNo(communityNo); // 커뮤니티 추천수 삭제
		communityMapper.deleteCommunityCommentByCommunityNo(communityNo); // 커뮤니티 댓글 삭제
		int row = communityMapper.deleteCommunity(communityNo, communityPw); // 커뮤니티 게시글 삭제
		
		return row;
		
	}
	
	
	// 희원 - addCommunity
	public void addCommunity(CommunityForm communityForm, String path) {
		log.debug(CF.PHW+"CommunityService.addCommunity.path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityService.addCommunity.communityForm : "+communityForm+CF.RS );
		
		Community community = new Community();
		community.setCommunityTitle(communityForm.getCommunityTitle());
		community.setCommunityContent(communityForm.getCommunityContent());
		community.setLoginId(communityForm.getLoginId());
		community.setCommunityPw(communityForm.getCommunityPw());
		
		int row = communityMapper.insertCommunity(community);
		log.debug(CF.PHW+"CommunityService.addCommunity.community.getCommunityNo() : "+community.getCommunityNo()+CF.RS );
		log.debug(CF.PHW+"CommunityService.addCommunity.community.row : "+row+CF.RS );
		
		if(communityForm.getCommunityFileList() != null && communityForm.getCommunityFileList().get(0).getSize() > 0 && row == 1) {
			log.debug(CF.PHW+"CommunityService.addCommunity.if : 첨부된 파일이 있습니다. "+CF.RS );
			for(MultipartFile mf : communityForm.getCommunityFileList()) {
				CommunityFile communityFile = new CommunityFile();
				
				String originName= mf.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf("."));
				
				String fileName = UUID.randomUUID().toString();
				fileName = fileName.replace("-","");
				fileName = fileName + ext;
				
				communityFile.setCommunityNo(community.getCommunityNo());
				communityFile.setCommunityFileName(fileName);
				communityFile.setCommunityFileType(mf.getContentType());
				communityFile.setCommunityFileSize(mf.getSize());
				communityFile.setCommunityFileOriginName(originName);
				communityFile.setLoginId(communityForm.getLoginId());
				log.debug(CF.PHW+"CommunityService.addCommunity.communityFile : "+communityFile+CF.RS );
				
				communityMapper.insertCommunityFile(communityFile);

				try {
					mf.transferTo(new File(path + fileName));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
	}
	
	// 희원 - communityOne & communityCommentList
	public Map<String, Object> getCommunityAndCommentList(Map<String, Object> map) {
		
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList map : "+map+CF.RS ); // 디버깅
		
		int commentStartRow = ((int)map.get("commentCurrentPage") - 1) * (int)map.get("commentRowPerPage");
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentStartRow : "+commentStartRow+CF.RS ); // 디커깅
		
		Map<String,Object> paramMap = new HashMap<>(); // paramMap에 communityNo, commentStartRow, commentRowPerPage 담기
		paramMap.put("communityNo", map.get("communityNo"));
		paramMap.put("commentStartRow", commentStartRow);
		paramMap.put("commentRowPerPage", map.get("commentRowPerPage"));
		
		CommunityMember communityMember = communityMapper.selectCommunityOne((int)map.get("communityNo")); // 커뮤니티 게시글 가져오기(게시글 작성 멤버 사진파일name 포함)
		List<CommunityFile> communityFileList = communityMapper.selectCommunityFileOne((int)map.get("communityNo")); // 커뮤니티 게시글 파일(리스트) 가져오기
		List<CommunityComment> communityCommentList = communityMapper.selectCommunityCommentList(paramMap); // 커뮤니티 게시글 댓글 리스트 가져오기
		int commentTotalCount = communityMapper.countCommunityComment((int)map.get("communityNo")); // 커뮤니티 게시글 총 개수 가져오기
		
		int commentLastPage = commentTotalCount/(int)paramMap.get("commentRowPerPage"); // 커뮤니티 댓글 lastPage 구하기
		
		if(commentTotalCount % (int)paramMap.get("commentRowPerPage") != 0) { // commentTotalCount % commentRowPerPage 나머지가 0이 아닐때
			commentLastPage++;
		} 
		
		// 디버깅
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList communityMember : "+communityMember+CF.RS ); 
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList communityFileList : "+communityFileList+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList communityCommentList : "+communityCommentList+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentTotalCount : "+commentTotalCount+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentLastPage : "+commentLastPage+CF.RS );
		
		Map<String,Object> returnMap = new HashMap<>(); // returnMap에 return한 값 넣기
		returnMap.put("communityNo", paramMap.get("communityNo")); // communityNo(게시글번호)
		returnMap.put("communityMember", communityMember); // communityMember(vo)
		returnMap.put("communityFileList", communityFileList); // 커뮤니티 파일 리스트
		returnMap.put("communityCommentList", communityCommentList); // 커뮤니티 댓글 리스트
		returnMap.put("commentCurrentPage", map.get("commentCurrentPage")); // commentCurrentPage
		returnMap.put("commentLastPage", commentLastPage); // commentLastPage
		
		return returnMap;
	}
	
	// 희원 - communityList
	public Map<String, Object> getCommunityList(int currentPage, int rowPerPage){
		int startRow = (currentPage - 1) * rowPerPage;
		
		log.debug(CF.PHW+"CommunityService.getCommunityList. currentPage : "+currentPage+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityList. rowPerPage : "+rowPerPage+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityList. startRow : "+startRow+CF.RS );
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		List<Community> communityList = communityMapper.selectCommunityList(map);
		
		int totalCount = communityMapper.countCommunityList();
		int lastPage = (int)Math.ceil((double)totalCount/(double)rowPerPage);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("communityList", communityList);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
		
	}
	
	// 영인 - qna리스트
	public List<Qna> getQnaList() {
		List<Qna> qnaList = communityMapper.selectQnaList();
		
		return qnaList;
	}
	
}
