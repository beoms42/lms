package kr.co.gdu.lms.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.AssignmentMapper;
import kr.co.gdu.lms.mapper.AssignmentfileMapper;
import kr.co.gdu.lms.vo.AssignmentExam;
import kr.co.gdu.lms.vo.AssignmentFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class AssignmentService {
	@Autowired private AssignmentMapper assignmentmapper;
	@Autowired private AssignmentfileMapper assignmentfilemapper;
	public Map<String,Object> getAssignmentExam(Map<String,Object> paramMap){
		// 1) 컨트롤러의 입력값 가공
		int assignmentCurrentPage = (int)paramMap.get("assignmentCurrentPage");
		String lectureName = (String)paramMap.get("lectureName");
		int rowPerPage = (int)paramMap.get("rowPerPage");
		int beginRow = (assignmentCurrentPage - 1) * rowPerPage;
		int assignmentTotal = assignmentmapper.selectAssignmentTotalCount();
		paramMap.put("beginRow", beginRow);

		// 디버깅
		log.debug(CF.GMC+"getAssignmentExam.AssignmentService assignmentCurrentPage"+assignmentCurrentPage+CF.RS);
		log.debug(CF.GMC+"getAssignmentExam.AssignmentService lectureName"+lectureName+CF.RS);
		log.debug(CF.GMC+"getAssignmentExam.AssignmentService rowPerPage"+rowPerPage+CF.RS);
		log.debug(CF.GMC+"getAssignmentExam.AssignmentService beginRow"+beginRow+CF.RS);
		log.debug(CF.GMC+"getAssignmentExam.AssignmentService assignmentTotal : "+assignmentTotal+CF.RS);

		
		int lastPage = 0;
		if((assignmentTotal % rowPerPage) != 0) {
			assignmentTotal += 1; 
		}
		List<AssignmentExam> assignmentExamList = assignmentmapper.selectAssignmentExam(paramMap);
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("assignmentExamList", assignmentExamList);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
		
	}
	public Map<String,Object> getAssignmentOne(Map<String,Object> paramMap){
		log.debug(CF.GMC+"AssignmentService.addAssignment.param assignmentExamNo : " + paramMap.get("assignmentExamNo") + CF.RS);
		log.debug(CF.GMC+"AssignmentService.addAssignment.param assignmentExamNo : " + paramMap.get("sessionMemberId") + CF.RS);
		List<AssignmentExam> assignmentList = assignmentmapper.selectAssignmentOne((int)paramMap.get("assignmentExamNo"));
		List<AssignmentFile> assignmentListFile = assignmentfilemapper.selectAssinmetFile(paramMap);
		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("assignmentList", assignmentList);
		returnMap.put("assignmentListFile", assignmentListFile);
		return returnMap;
	}
	public int addAssignment(AssignmentExam assignmentexam,String path)  {
		log.debug(CF.GMC+"AssignmentService.addAssignment.param path : " + path + CF.RS);
		log.debug(CF.GMC+"AssignmentService.addAssignment.param assignmentfileList : " + assignmentexam +CF.RS);
		
		// NoticeMapper 호출 위해
		// 데이터 바인딩
	
		
		int row = assignmentmapper.insertAssignmentExam(assignmentexam);
		List<MultipartFile> assignmentfileList = assignmentexam.getAssignmentFileList();
	
		log.debug(CF.GMC + "AssignmentService.addAssignment row" + row + CF.RS);
		
		int assingmentExamNo = assignmentexam.getAssignmentExamNo();
		
		String loginId = assignmentexam.getLoginId();
		// 과제가 하나 이상이고 입력도 성공했을때
		if(assignmentfileList != null && assignmentfileList.get(0).getSize() > 0 
				&& assignmentfileList.size() > 0 
				&& row==1) {
			
			log.debug(CF.GMC + "AssignmentService.addAssignmentExam : "+ "첨부된 파일이 있습니다" + CF.RS);
			for(MultipartFile mf : assignmentfileList) {
				
				AssignmentFile assignmentFile = new AssignmentFile();
				
				//원본 이름
				String originName = mf.getOriginalFilename();
				
				//TYPE
				String ext = originName.substring(originName.lastIndexOf("."));
				
				//중복 방지
				String filename = UUID.randomUUID().toString(); // 16진수 문자열로 만든 절대 중복될 수 없는 문자 만들어줌
				
				//UUID 새로운 이름 
				filename = filename.replace("-", "");
				filename = filename + ext;
				
				assignmentFile.setAssignmentExamNo(assingmentExamNo);
				assignmentFile.setAssignmentFileName(filename);
				assignmentFile.setAssignmentFileOriginName(originName);
				assignmentFile.setAssignmentFileSize(mf.getSize());
				assignmentFile.setAssignmentFileType(mf.getContentType());
				assignmentFile.setLoginId(loginId);
				assignmentfilemapper.insertAssingmentfile(assignmentFile);
				
				//파일 저장
				try {
					mf.transferTo(new File(path+filename));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		
		return row;
	}
	
}
