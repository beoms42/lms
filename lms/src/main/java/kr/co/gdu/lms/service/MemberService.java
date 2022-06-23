package kr.co.gdu.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.mapper.MemberFileMapper;
import kr.co.gdu.lms.mapper.StudentMapper;
import kr.co.gdu.lms.mapper.TeacherMapper;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.MemberFile;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class MemberService {
	@Autowired private StudentMapper studentMapper;
	@Autowired private MemberFileMapper memberFileMapper;
	
	// 학생정보 상세보기
	public Map<String, Object> getStudentOne(String loginId) {
		// 로그인ID 디버깅
		log.debug(CF.GDH+"MemberService.getStudentOne loginId : "+loginId+CF.RS);
		
		// 학생정보 Mapper연결
		Student student = studentMapper.selectStudentOne(loginId);
		log.debug(CF.GDH+"MemberService.getStudentOne student : "+student+CF.RS);
		
		// 학생파일 Mapper연결
		MemberFile memberFile = memberFileMapper.selectMemberFile(loginId);
		log.debug(CF.GDH+"====================MemberService.getStudentOne memberFileList : "+memberFile+CF.RS);
		
		// 학생정보와 학생파일리스트 맵에 담기
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("student", student);
		returnMap.put("memberFile", memberFile);
		
		
		return returnMap;
} 

	// 학생 목록 리스트
	 public List<Student> getStudentList() {
		 List<Student> list = studentMapper.selectStudentList();
		 log.debug(CF.PSH+"MemberService.getStudentList :"+CF.RS);
		 return list;   
	 }
	
	// 학생정보 수정하기
	public int modifyStudent(Student student) {
		log.debug(CF.GDH+"MemberService.modifyStudent student : " + student + CF.RS);
		return studentMapper.updateStudent(student);
	}
	
	// 학생정보 삭제하기
	public int removeStudent(Login login) {
		log.debug(CF.GDH+"MemberService.removeStudent login : " + login + CF.RS);
		
		int activeRow = studentMapper.updateStudentActive(login);
		log.debug(CF.GDH+"MemberService.removeStudent activeRow : " + activeRow + CF.RS);
		
		return studentMapper.deleteStudent(login);
	}
	
	// 비밀번호 확인하기
	public int getPwCheck(Login login) {
		log.debug(CF.GDH+"MemberService.getPwCheck login : " + login + CF.RS);
		
		int row = studentMapper.pwCheck(login);
		log.debug(CF.GDH+"MemberService.getPwCheck row : " + row + CF.RS);
		
		return row;
	}
	
	// 멤버파일 수정하기
	public int modifyMemberFile(String loginId, String memberFileName) {
		log.debug(CF.GDH+"MemberService.modifyMemberFile loginId : " + loginId + CF.RS);
		log.debug(CF.GDH+"MemberService.modifyMemberFile memberFileName : " + memberFileName + CF.RS);
		
		// 파일에 담긴 사진 지우기(경로)
		
		// 사진 업데이트
		MemberFile memberFile =  new MemberFile();
		
		int row = memberFileMapper.updateMemberFile(memberFile);
		log.debug(CF.GDH+"MemberService.updateMemberFile row : " + row + CF.RS);
		
		return row;
	}
	
	
	@Autowired  private ManagerMapper managerMapper;
	@Autowired  private TeacherMapper teacherMapper;
	
	// 매니저 리스트
		 public List<Manager> getManagerList() {
			 List<Manager> list = managerMapper.selectManagerList();
			 log.debug(CF.PSH+"MemberService.getManagerList :"+CF.RS);
			 return list;   
		 }
		 
		 
		// 매니저 정보 상세보기
		 public Manager getManagerOne(String loginId) {
		 	Manager manager = new Manager();
		 	manager = managerMapper.selectManagerOne(loginId);
		 	log.debug(CF.PSH+"MemberService.getManagerOne :"+loginId+CF.RS);
		 	return manager;
		 }
		 
		 // 매니저 정보 수정하기
		 public int modifyManager(Manager loginId) {
			 	int row = 0;
			    row = managerMapper.updateManager(loginId);
			 	log.debug(CF.PSH+"MemberService.modifyManager :"+loginId+CF.RS);
			    return row;
			}
		 
		 // 매니저 회원탈퇴
		 public int deleteManager(String loginId) {
			 int row = 0;
			 row = managerMapper.deleteManager(loginId);
		 	log.debug(CF.PSH+"MemberService.deleteManager :"+loginId+CF.RS);
			 return row;
		 }
		 
		
		 // 강사 리스트
		 public List<Teacher> getTeacherList() {
			 List<Teacher> list = teacherMapper.selectTeacherList();
		 	 log.debug(CF.PSH+"MemberService.getTeacherList :"+CF.RS);
			 return list;
		 }
		 
		 
		// 강사 상세보기
		 public Teacher getTeacherOne(String loginId) {
			 	Teacher teacher = new Teacher();
			 	teacher = teacherMapper.selectTeacherOne(loginId);
			 	 log.debug(CF.PSH+"MemberService.getTeacherOne :"+loginId+CF.RS);
			 	return teacher;
			}
		 
		 // 강사 정보 수정하기
			public int modifyTeacher(Teacher loginId) {
			 	int row = 0;
			    row = teacherMapper.updateTeacher(loginId);
			    log.debug(CF.PSH+"MemberService.modifyTeacher :"+loginId+CF.RS);
				return row;

			}
			
		 // 강사 회원탈퇴
			public int deleteTeacher(String loginId) {
				int row =0;
				row = teacherMapper.deleteTeacher(loginId);
				log.debug(CF.PSH+"MemberService.deleteTeacher :"+loginId+CF.RS);
				return row;

				}
			
			// 사진 한장 불러오기
			public String selectMemberFileOne(String loginId) {
				MemberFile mem = memberFileMapper.selectMemberFile(loginId);
				String fileName = mem.getMemberFileName();
				return fileName;
			}
	}
			 



