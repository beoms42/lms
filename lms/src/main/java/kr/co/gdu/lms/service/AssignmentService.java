package kr.co.gdu.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.ognl.ASTAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.AssignmentMapper;
import kr.co.gdu.lms.mapper.AssignmentfileMapper;
import kr.co.gdu.lms.vo.AssignmentAddForm;
import kr.co.gdu.lms.vo.AssignmentExam;
import kr.co.gdu.lms.vo.AssignmentFile;
import kr.co.gdu.lms.vo.AssignmentSubmit;
import kr.co.gdu.lms.vo.AssignmentSubmitForm;
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
		log.debug(CF.GMC+"AssignmentService.addAssignment.param sessionMemberId : " + paramMap.get("loginId") + CF.RS);
		List<AssignmentExam> assignmentList = assignmentmapper.selectAssignmentOne((int)paramMap.get("assignmentExamNo"));
		List<AssignmentFile> assignmentListFile = assignmentfilemapper.selectAssinmetFile(paramMap);
		for(AssignmentFile f : assignmentListFile) {
			log.debug(CF.GMC+"AssignementService.getAssignmentOne"+f.getAssignmentExamNo()+CF.RS);
		}
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("assignmentList", assignmentList);
		returnMap.put("assignmentListFile", assignmentListFile);
		return returnMap;
	}
	public int addAssignment(AssignmentAddForm assignmentAddForm,String path)  {
		log.debug(CF.GMC+"AssignmentService.addAssignment.param path : " + path + CF.RS);
		log.debug(CF.GMC+"AssignmentService.addAssignment.param assignmentfileList : " + assignmentAddForm.getAssignmentFileList() +CF.RS);
		
		// 데이터 바인딩
	
		
		int row = assignmentmapper.insertAssignmentExam(assignmentAddForm);
	
		log.debug(CF.GMC + "AssignmentService.addAssignment row" + row + CF.RS);
		int assignmentExamNo = assignmentmapper.selectassignmentExamNo();
		String loginId = assignmentAddForm.getLoginId();
		// 과제가 하나 이상이고 입력도 성공했을때
		if( assignmentAddForm.getAssignmentFileList() != null &&  assignmentAddForm.getAssignmentFileList().get(0).getSize() > 0 
				&&  assignmentAddForm.getAssignmentFileList().size() > 0 
				&& row==1) {
			
			log.debug(CF.GMC + "AssignmentService.addAssignmentExam : "+ "첨부된 파일이 있습니다" + CF.RS);
			for(MultipartFile mf :  assignmentAddForm.getAssignmentFileList()) {
				
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
				
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam originName:" +originName +CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam ext: " +ext+CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam filename:" + filename +CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam assignmentExamNo:" + assignmentExamNo+CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam : mf.getSize()"+mf.getSize() +CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam : mf.getContentType()"+mf.getContentType() +CF.RS);
				
				
				assignmentFile.setAssignmentExamNo(assignmentExamNo);
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
		}else {
			log.debug("파일이 비어있습니다");
		}
		
		return row;
	}
	public int getEducationNo(@RequestParam (name="sessionMemberId") String loginId) {
		int educationNo = assignmentmapper.selectEducationNo(loginId);
		return educationNo;
	}
	public String getLectureName(@RequestParam (name="sessionMemberId")String loginId) {
		String lectureName = assignmentmapper.selectLectureName(loginId);
		return lectureName;
	}
	public void addAssignmentSubmit(AssignmentSubmit assignmentSubmit) {
		assignmentmapper.insertAssignmentSubmit(assignmentSubmit); 
	}
	public List<AssignmentSubmit> getAssignmentSubmit(int assignmentExamNo){
		List<AssignmentSubmit> assignmentSubmit = assignmentmapper.selectAssignmentSubmit(assignmentExamNo);
		return assignmentSubmit;
	}
	public void addAssignmentSubmitFile(AssignmentSubmitForm assignmentSubmitForm
									,String path
									,@RequestParam (name="assignmentExamNo") int assignmentExamNo)  {
		
		
		log.debug(CF.GMC+"AssignmentService.addAssignment.param path : " + path + CF.RS);
		log.debug(CF.GMC+"AssignmentService.addAssignment.param assignmentfileList : " + assignmentSubmitForm.getAssignmentSubmitFileList() +CF.RS);
		
		String loginId = assignmentSubmitForm.getLoginId();
		// 과제가 하나 이상이고 입력도 성공했을때
		if( assignmentSubmitForm.getAssignmentSubmitFileList() != null &&  assignmentSubmitForm.getAssignmentSubmitFileList().get(0).getSize() > 0 
				&&  assignmentSubmitForm.getAssignmentSubmitFileList().size() > 0 ) {
			
			log.debug(CF.GMC + "AssignmentService.addAssignmentExam : "+ "첨부된 파일이 있습니다" + CF.RS);
			for(MultipartFile mf :  assignmentSubmitForm.getAssignmentSubmitFileList()) {
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
				
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam originName:" +originName +CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam ext: " +ext+CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam filename:" + filename +CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam assignmentExamNo:" + assignmentExamNo+CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam : mf.getSize()"+mf.getSize() +CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam : mf.getContentType()"+mf.getContentType() +CF.RS);
				
				
				assignmentFile.setAssignmentExamNo(assignmentExamNo);
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
				
	}
	public void modifiyAssignmentExam(AssignmentExam assignmentExam) {
		assignmentmapper.updateAssignmentExam(assignmentExam);
	}
	public void updateScore(Map<String,Object> paramMap) {
		assignmentmapper.updateScore(paramMap);
	}
	public void deleteAssignment(int assignmentExamNo) {
		assignmentmapper.deleteAssignment(assignmentExamNo);
	}
	
}
