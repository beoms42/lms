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
import kr.co.gdu.lms.vo.Community;
import kr.co.gdu.lms.vo.CommunityFile;
import kr.co.gdu.lms.vo.Qna;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class CommunityService {
	@Autowired private CommunityMapper communityMapper;
	
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
	public Map<String, Object> getCommunityOne(Map<String, Object> map) {
		
		int communityNo = (int)map.get("communityNo");
		log.debug(CF.PHW+"CommunityService.getCommunityOne communityNo : "+communityNo+CF.RS );
		
		Community community = communityMapper.selectCommunityOne(communityNo);
		List<String> communityFileList = communityMapper.selectCommunityFileOne(communityNo);
		
		log.debug(CF.PHW+"CommunityService.getCommunityOne community : "+community+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityOne communityFileList : "+communityFileList+CF.RS );
		
		
		map.put("communityNo", communityNo);
		map.put("community", community);
		map.put("communityFileList", communityFileList);
		
		
		return map;
	}
	
	
	// 희원 - communityList
	public Map<String, Object> getCommunityList(int currentPage, int rowPerPage){
		int startRow = (currentPage - 1)* rowPerPage;
		
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
