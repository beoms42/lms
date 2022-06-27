package kr.co.gdu.lms.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.mapper.MemberFileMapper;
import kr.co.gdu.lms.mapper.StudentMapper;
import kr.co.gdu.lms.mapper.TeacherMapper;
import kr.co.gdu.lms.vo.Dept;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.MemberFile;
import kr.co.gdu.lms.vo.Position;
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
		log.debug(CF.GDH+"MemberService.getStudentOne memberFileList : "+memberFile+CF.RS);
		
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
	
	// 멤버파일 폴더에서 삭제하기
	public void removeMemberFile(String path, String memberFileName) {
		log.debug(CF.GDH+"MemberService.removeMemberFile memberFileName : " + memberFileName + CF.RS);
		log.debug(CF.GDH+"MemberService.removeMemberFile path : " + path + CF.RS);
		
		// 폴더 내에서 파일을 삭제
		File f = new File(path + memberFileName);
		f.delete();
	}
	
	
	// 멤버파일 수정하기
	public int modifyMemberFile(String loginId, String memberFileName, MultipartFile insertMemberFile, String path) {
		log.debug(CF.GDH+"MemberService.modifyMemberFile loginId : " + loginId + CF.RS);
		log.debug(CF.GDH+"MemberService.modifyMemberFile path : " + path + CF.RS);
		
		// 사진
		MemberFile memberFile =  new MemberFile();
		
		// DB사진삭제
		int dmRow = memberFileMapper.deleteMemberFile(loginId);
		log.debug(CF.GDH+"MemberService.updateMemberFile dmRow : " + dmRow + CF.RS);
		if(dmRow == 1) {
			// DB사진입력
			String originalFileName = insertMemberFile.getOriginalFilename();
			String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
			// 파일을 저장할 때, 사용할 중복되지 않는 새로운 이름 필요(UUID API사용)
			String fileName = UUID.randomUUID().toString();
			fileName = fileName.replace("-", "");
			fileName = fileName + ext;
			memberFile.setMemberFileOriginName(originalFileName);
			memberFile.setMemberFileName(fileName);
			memberFile.setMemberFileType(insertMemberFile.getContentType());
			memberFile.setMemberFileSize(insertMemberFile.getSize());
			log.debug(CF.GDH+"MemberService.updateMemberFile memberFile : " + memberFile + CF.RS);

			int imRow = memberFileMapper.insertMemberFile(memberFile);
			log.debug(CF.GDH+"MemberService.updateMemberFile imRow : " + imRow + CF.RS);
				if(imRow == 1) {
					try {
						insertMemberFile.transferTo(new File(path+fileName));
					} catch (Exception e) {
						e.printStackTrace();
						// 새로운 예외 발생시켜줌 -> Transactional 작동
						throw new RuntimeException(); // RuntimeExceiption은 예외처리를 하지 않아도 컴파일.
					}
					return imRow;
				} 
			return imRow;
		}
		return dmRow;
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
	public Map<String,Object> getManagerOne(String loginId) {
		 
		Map<String,Object> managerMap = managerMapper.selectManagerOne(loginId);
		 
		 log.debug(CF.PSH+"MemberService.getManagerOne :"+loginId+CF.RS);
		 return managerMap;
	}
		 
		// 부서리스트
		public List<Dept> getDeptList() {
			List<Dept> deptList = new ArrayList<Dept>();
			deptList = managerMapper.selectDept();
			log.debug(CF.PSH+"MemberService.getManagerOne :"+deptList+CF.RS);
			return deptList;
		}
		 
		// 직급 리스트
		public List<Position> getPositions(){
			List<Position> positionList = new ArrayList<Position>();
			positionList = managerMapper.selectPosition();
			log.debug(CF.PSH+"MemberService.getManagerOne :"+positionList+CF.RS);
			return positionList;
		}
		
		// 매니저 정보 수정하기
		public int modifyManager(Manager manager) {
			log.debug(CF.PSH+"MemberService.modifyManager :"+manager+CF.RS);
			int row = managerMapper.updateManager(manager);
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
		 	 log.debug(CF.PSH+"MemberService.getTeacherList :"+list+CF.RS);
			 return list;
		 }
		 
		 
	 // 강사 상세보기
	 public Map<String, Object> getTeacherOne(String loginId) {
		 // 로그인ID 디버깅
		 log.debug(CF.GDH+"MemberService.getTeacherOne loginId:"+loginId+CF.RS);
		 
		 // 강사정보 Mapper 연결
		 Teacher teacher = teacherMapper.selectTeacherOne(loginId);
		 log.debug(CF.PSH+"MemberService.getTeacherOne teacher:"+teacher+CF.RS);
		 
		 // 강사파일 Mapper 연결
		 MemberFile teacherFile = memberFileMapper.selectMemberFile(loginId);
		 log.debug(CF.GDH+"MemberService.getTeacherOne teacherFile:"+teacherFile+CF.RS);
		 
		 // 강사정보와 강사파일 맵에 담기
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 returnMap.put("teacher", teacher);
		 returnMap.put("teacherFile", teacherFile);
		 
		 return returnMap;
	 }
		 
		// 강사 정보 수정하기
		public int modifyTeacher(Teacher teacher) {
			log.debug(CF.PSH+"MemberService.modifyTeacher :"+teacher+CF.RS);
			int row = teacherMapper.updateTeacher(teacher);
			return row;
		}
			
		 // 강사 회원탈퇴
			public int deleteTeacher(String loginId) {
				int row =0;
				row = teacherMapper.deleteTeacher(loginId);
				log.debug(CF.PSH+"MemberService.deleteTeacher 삭제:"+loginId+CF.RS);
				return row;
				}
			
			// 사진 한장 불러오기
			public String getMemberFileOne(String loginId) {
				MemberFile mem = memberFileMapper.selectMemberFile(loginId);
				String fileName = mem.getMemberFileName();
				return fileName;
			}
	}
