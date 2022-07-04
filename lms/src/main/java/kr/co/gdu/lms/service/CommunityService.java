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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class CommunityService {
	@Autowired private CommunityMapper communityMapper;
	

	// 희원 -  modifyCommunity 수정 중 파일 삭제(ajax)
	public int deleteCommunityFileOne(String path, int communityFileNo, String communityFileName) {
		
		log.debug(CF.PHW+"CommunityService.deleteCommunityFileOne.path : "+path+CF.RS );
		log.debug(CF.PHW+"CommunityService.deleteCommunityFileOne.communityFileNo : "+communityFileNo+CF.RS );
		log.debug(CF.PHW+"CommunityService.deleteCommunityFileOne.communityFileName : "+communityFileName+CF.RS );
		
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
		
		communityMapper.deleteCommunityFileList(communityNo);
		int row = communityMapper.deleteCommunity(communityNo, communityPw);
		
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
	
	// 희원 - communityOne
	public Map<String, Object> getCommunityAndCommentList(Map<String, Object> map) {
		
		int communityNo = (int)map.get("communityNo");
		int commentCurrentPage = (int)map.get("commentCurrentPage");
		int commentRowPerPage = (int)map.get("commentRowPerPage");
		int commentStartRow = (commentCurrentPage - 1) * commentRowPerPage;
		
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList communityNo : "+communityNo+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentCurrentPage : "+commentCurrentPage+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentRowPerPage : "+commentRowPerPage+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentStartRow : "+commentStartRow+CF.RS );
		
		Map<String,Object> paramMap = new HashMap<>();
		map.put("communityNo", communityNo);
		map.put("commentStartRow", commentStartRow);
		map.put("commentRowPerPage", commentRowPerPage);
		
		CommunityMember communityMember = communityMapper.selectCommunityOne((int)map.get("communityNo"));
		List<CommunityFile> communityFileList = communityMapper.selectCommunityFileOne((int)map.get("communityNo"));
		List<CommunityComment> communityCommentList = communityMapper.selectCommunityCommentList(paramMap);
		int commentTotalCount = communityMapper.countCommunityComment();
		int commentLastPage = (int)Math.ceil((double)commentTotalCount/(double)map.get("commentRowPerPage"));
		
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList communityMember : "+communityMember+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList communityFileList : "+communityFileList+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList communityCommentList : "+communityCommentList+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentTotalCount : "+commentTotalCount+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityAndCommentList commentLastPage : "+commentLastPage+CF.RS );
		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap .put("communityNo", communityNo);
		returnMap .put("communityMember", communityMember);
		returnMap .put("communityFileList", communityFileList);
		returnMap .put("communityCommentList", communityCommentList);
		returnMap .put("commentCurrentPage", commentCurrentPage);
		returnMap .put("commentLastPage", commentLastPage);
		
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
