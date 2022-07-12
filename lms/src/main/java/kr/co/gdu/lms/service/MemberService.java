package kr.co.gdu.lms.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.AdminMapper;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.mapper.MemberFileMapper;
import kr.co.gdu.lms.mapper.StudentMapper;
import kr.co.gdu.lms.mapper.TeacherMapper;
import kr.co.gdu.lms.vo.Admin;
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
   @Autowired private TeacherMapper teacherMapper;
   @Autowired private ManagerMapper managerMapper;
   @Autowired private AdminMapper adminMapper;
   @Autowired private MemberFileMapper memberFileMapper;
   
   public void modifyEmploymentByStudent(List<String> employment) {
	   
	   log.debug(CF.LCH+"MemberService.modifyEmploymentByStudent employment : "+employment+CF.RS);
	   for(String s : employment) {
		   
		   String[] employmentAry = s.split(",");
		   log.debug(CF.LCH+"MemberService.modifyEmploymentByStudent employmentAry : "+employmentAry+CF.RS);
		   
		   Student student = new Student();
		   student.setEmployment(employmentAry[1]);
		   student.setLoginId(employmentAry[0]);
		   log.debug(CF.LCH+"MemberService.modifyEmploymentByStudent student : "+student+CF.RS);
		   
		   studentMapper.updateEmploymentByStudent(student);
	   }
   }
   
   // 학생들 취업 상태 리스트
   public List<Object> getEmploymentList(Map<String, Object> paramMap) {
	   log.debug(CF.LCH+"MemberService.getEmploymentList paramMap : "+paramMap+CF.RS);
	   int employmentStartRow = ((int)paramMap.get("employmentCurrentPage")-1) * (int)paramMap.get("employmentRowPerPage");
	   log.debug(CF.LCH+"MemberService.getEmploymentList employmentStartRow : "+employmentStartRow+CF.RS);
	   paramMap.put("employmentStartRow", employmentStartRow);
	   
	   List<Map<String, Object>> returnMap = studentMapper.selectEmploymentList(paramMap);
	   log.debug(CF.LCH+"MemberService.getEmploymentList returnMap : "+returnMap+CF.RS);
	   
	   int employmentTotalCount = studentMapper.selectEmploymentTotalRow((String)paramMap.get("lectureName"));
	   log.debug(CF.LCH+"MemberService.getEmploymentList employmentTotalCount : "+employmentTotalCount+CF.RS);
	   
	   int employmentLastPage = employmentTotalCount / (int)paramMap.get("employmentRowPerPage");
	   
	   if(employmentTotalCount % (int)paramMap.get("employmentRowPerPage") != 0) {
		   employmentLastPage += 1;
	   }
	   
	   log.debug(CF.LCH+"MemberService.getEmploymentList employmentLastPage : "+employmentLastPage+CF.RS);
	   
	   List<Object> returnList = new ArrayList<Object>();
	   returnList.add(returnMap);
	   returnList.add(employmentLastPage);
	   
	   log.debug(CF.LCH+"MemberService.getEmploymentList returnList : "+returnList+CF.RS);
	   
	   return returnList;
   }
   
   // 회원정보 상세보기
   public Map<String, Object> getMemberOne(Login login) {
      // 세션을 통해 로그인ID 받아오기
      String loginId = login.getLoginId();
      log.debug(CF.GDH+"MemberService.getMemberOne loginId : "+loginId+CF.RS);
      
      // 세션을 통해 로그인LV 받아오기
      int level = login.getLevel();
      log.debug(CF.GDH+"MemberService.getMemberOne level : "+level+CF.RS);
      
      // 정보담을 Map만들기(분기별 회원정보 맵에 담기)
      Map<String, Object> returnMap = new HashMap<String, Object>();
      
      if(level==1) {
         // 학생정보 Mapper연결
         Student member = studentMapper.selectStudentOne(loginId);
         log.debug(CF.GDH+"MemberService.getStudentOne student : "+member+CF.RS);
         returnMap.put("member", member);
      } else if(level==2) {
         // 강사정보 Mapper 연결
         Teacher member = teacherMapper.selectTeacherOne(loginId);
         log.debug(CF.GDH+"MemberService.getTeacherOne teacher:"+member+CF.RS);
         returnMap.put("member", member);
      } else if(level==3) {
         // 매니저정보 Mapper연결
         Manager member = managerMapper.selectManagerOne(loginId);
         log.debug(CF.GDH+"MemberService.getManagerOne manager :"+member+CF.RS); 
         returnMap.put("member", member);
      } else {
    	 // 관리자정보 Mapper연결
    	 Admin member = adminMapper.selectAdminOne(loginId);
    	 log.debug(CF.GDH+"MemberService.getManagerOne admin :"+member+CF.RS);
    	 returnMap.put("member", member);
      }
      
      // 회원사진파일 Mapper연결
      MemberFile memberFile = memberFileMapper.selectMemberFile(loginId);
      log.debug(CF.GDH+"MemberService.getStudentOne memberFileList : "+memberFile+CF.RS);
      // 회원사진파일 Map에 담기
      returnMap.put("memberFile", memberFile);
      
      return returnMap;
   } 
   
   // 학생정보 수정하기
   public int modifyStudent(Student student) {
      log.debug(CF.GDH+"MemberService.modifyStudent student : " + student + CF.RS);
      int row = studentMapper.updateStudent(student);
      return row;
   }
   
   // 학생정보 삭제하기
   public int removeStudent(Login login) {
      log.debug(CF.GDH+"MemberService.removeStudent login : " + login + CF.RS);
      
      int activeRow = studentMapper.updateStudentActive(login);
      log.debug(CF.GDH+"MemberService.removeStudent activeRow : " + activeRow + CF.RS);
      
      return studentMapper.deleteStudent(login.getLoginId());
   }
   
   // 비밀번호 확인하기
   public int getPwCheck(Login login) {
      log.debug(CF.GDH+"MemberService.getPwCheck login : " + login + CF.RS);
      
      int row = studentMapper.pwCheck(login);
      log.debug(CF.GDH+"MemberService.getPwCheck row : " + row + CF.RS);
      
      return row;
   }
   
   // 비밀번호 수정하기
   public int modifyPw(Login login) {
	   log.debug(CF.GDH+"MemberService.moidfyPw login : " + login + CF.RS);
	   
	   int row = memberFileMapper.updateMemberPw(login);
	   log.debug(CF.GDH+"MemberService.moidfyPw row : " + row + CF.RS);
	   
	   return row;
   }
   
   // 멤버파일 수정하기
   public int modifyMemberFile(String loginId, String memberFileName, MultipartFile insertMemberFile, String path) {
      log.debug(CF.GDH+"MemberService.modifyMemberFile loginId : " + loginId + CF.RS);
      log.debug(CF.GDH+"MemberService.modifyMemberFile path : " + path + CF.RS);
      
      // 폴더 내에서 파일을 삭제
      File f = new File(path + memberFileName);
      f.delete();
      
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
         memberFile.setLoginId(loginId);
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
      
	// 강사 정보 수정하기
	public int modifyTeacher(Teacher teacher) {
	    log.debug(CF.PSH+"MemberService.modifyTeacher teacher : " + teacher + CF.RS);
	    int row = teacherMapper.updateTeacher(teacher);
	  	return row;
	}
   
    // 매니저 정보 수정하기
   	public int modifyManager(Manager manager) {
        log.debug(CF.PSH+"MemberService.modifyManager :"+manager+CF.RS);
        int row = managerMapper.updateManager(manager);
        return row;
    }
    
   	// 강사 회원탈퇴
    public int removeTeacher(Login login) {
    	log.debug(CF.GDH+"MemberService.removeTeacher login : " + login + CF.RS);
    	
    	int activeRow = teacherMapper.updateTeacherActive(login);
    	log.debug(CF.GDH+"MemberService.removeTeacher activeRow : " + activeRow + CF.RS);
        
    	return teacherMapper.deleteTeacher(login.getLoginId());
    }
       
    // 매니저 회원탈퇴
    public int removeManager(Login login) {
    	log.debug(CF.GDH+"MemberService.removeManager login : " + login + CF.RS);
    	
    	int activeRow = managerMapper.updateManagerActive(login);
    	log.debug(CF.GDH+"MemberService.removeManager activeRow : " + activeRow + CF.RS);
        
    	return managerMapper.deleteManager(login.getLoginId());
    }
         
    // 사진 한장 불러오기
    public String getMemberFileOne(String loginId) {
        MemberFile mem = memberFileMapper.selectMemberFile(loginId);
        String fileName = mem.getMemberFileName();
        return fileName;
    }
         
    // 학생 리스트
    public List<Student> getStudentList() {
        List<Student> list = studentMapper.selectStudentList();
  	   	log.debug(CF.PSH+"MemberService.getStudentList :"+CF.RS);
  	   	return list;   
    }
    
    // 강사 리스트
    public List<Teacher> getTeacherList() {
        List<Teacher> list = teacherMapper.selectTeacherList();
        log.debug(CF.PSH+"MemberService.getTeacherList :"+list+CF.RS);
        return list;
    } 
    
    // 매니저 리스트
    public List<Manager> getManagerList() {
        List<Manager> list = managerMapper.selectManagerList();
        log.debug(CF.PSH+"MemberService.getManagerList :"+CF.RS);
        return list;   
    }
         
          
}