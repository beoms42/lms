package kr.co.gdu.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import kr.co.gdu.lms.vo.Lecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class AssignmentService {
	@Autowired private AssignmentMapper assignmentmapper;
	@Autowired private AssignmentfileMapper assignmentfilemapper;

	public	List<AssignmentExam> getAssignmentExam(String lectureName) {
		
		// 디버깅
		log.debug(CF.GMC + "getAssignmentExam.AssignmentService lectureName" + lectureName + CF.RS);

		List<AssignmentExam> assignmentExamList = assignmentmapper.selectAssignmentExam(lectureName);


		return assignmentExamList;

	}

	public Map<String, Object> getAssignmentOne(Map<String, Object> paramMap) {
		log.debug(CF.GMC + "AssignmentService.addAssignment.param assignmentExamNo : "
				+ paramMap.get("assignmentExamNo") + CF.RS);
		log.debug(
				CF.GMC + "AssignmentService.addAssignment.param sessionMemberId : " + paramMap.get("loginId") + CF.RS);

		// 리스트
		List<AssignmentExam> assignmentList = assignmentmapper.selectAssignmentOne((int) paramMap.get("assignmentExamNo"));
		List<AssignmentFile> assignmentListFile = assignmentfilemapper.selectAssinmetFile(paramMap);
		for (AssignmentFile f : assignmentListFile) {
			log.debug(CF.GMC + "AssignementService.getAssignmentOne" + f.getAssignmentExamNo() + CF.RS);
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("assignmentList", assignmentList);
		returnMap.put("assignmentListFile", assignmentListFile);
		return returnMap;
	}


	public Map<String,Object> getEducation(@RequestParam(name = "sessionMemberId") String loginId) {
		Map<String,Object> returnMap = assignmentmapper.selectEducation(loginId);
		return returnMap;
	}


	public void addAssignmentSubmit(AssignmentSubmit assignmentSubmit) {
		assignmentmapper.insertAssignmentSubmit(assignmentSubmit);
	}

	public Map<String, Object> getAssignmentSubmit(Map<String, Object> map) {
		List<AssignmentSubmit> assignmentSubmit = assignmentmapper.selectAssignmentSubmit(map);
		List<AssignmentSubmit> assignmentSubmitTeacher = assignmentmapper.selectAssignmentSubmitTeacher(map);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("assignmentSubmit", assignmentSubmit);
		returnMap.put("assignmentSubmitTeacher", assignmentSubmitTeacher);
		return returnMap;
	}

	public void addAssignmentSubmitFile(AssignmentSubmitForm assignmentSubmitForm, String path,
			@RequestParam(name = "assignmentExamNo") int assignmentExamNo) {

		log.debug(CF.GMC + "AssignmentService.addAssignment.param path : " + path + CF.RS);
		log.debug(CF.GMC + "AssignmentService.addAssignment.param assignmentfileList : "
				+ assignmentSubmitForm.getAssignmentSubmitFileList() + CF.RS);

		String loginId = assignmentSubmitForm.getLoginId();
		// 과제가 하나 이상이고 입력도 성공했을때
		if (assignmentSubmitForm.getAssignmentSubmitFileList() != null
				&& assignmentSubmitForm.getAssignmentSubmitFileList().get(0).getSize() > 0
				&& assignmentSubmitForm.getAssignmentSubmitFileList().size() > 0) {

			log.debug(CF.GMC + "AssignmentService.addAssignmentExam : " + "첨부된 파일이 있습니다" + CF.RS);
			for (MultipartFile mf : assignmentSubmitForm.getAssignmentSubmitFileList()) {
				AssignmentFile assignmentFile = new AssignmentFile();
				// 원본 이름
				String originName = mf.getOriginalFilename();

				// TYPE
				String ext = originName.substring(originName.lastIndexOf("."));

				// 중복 방지
				String filename = UUID.randomUUID().toString(); // 16진수 문자열로 만든 절대 중복될 수 없는 문자 만들어줌

				// UUID 새로운 이름
				filename = filename.replace("-", "");
				filename = filename + ext;

				log.debug(CF.GMC + "AssignmentService.addAssignmentExam originName:" + originName + CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam ext: " + ext + CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam filename:" + filename + CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam assignmentExamNo:" + assignmentExamNo + CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam : mf.getSize()" + mf.getSize() + CF.RS);
				log.debug(CF.GMC + "AssignmentService.addAssignmentExam : mf.getContentType()" + mf.getContentType()
						+ CF.RS);

				assignmentFile.setAssignmentExamNo(assignmentExamNo);
				assignmentFile.setAssignmentFileName(filename);
				assignmentFile.setAssignmentFileOriginName(originName);
				assignmentFile.setAssignmentFileSize(mf.getSize());
				assignmentFile.setAssignmentFileType(mf.getContentType());
				assignmentFile.setLoginId(loginId);
				assignmentfilemapper.insertAssingmentfile(assignmentFile);

				// 파일 저장
				try {
					mf.transferTo(new File(path + filename));
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

	public void modifyScore(Map<String, Object> paramMap) {
		assignmentmapper.updateScore(paramMap);
	}

	public void deleteAssignment(int assignmentExamNo) {
		assignmentfilemapper.deleteAssignmentfile(assignmentExamNo);
		assignmentmapper.deleteAssignmentSubmit(assignmentExamNo);
		assignmentmapper.deleteAssignment(assignmentExamNo);
	}

	public List<Lecture> getLectureNameList() {
		List<Lecture> lectureNameList = assignmentmapper.selectLectureNameList();
		return lectureNameList;
	}
}
