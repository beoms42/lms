package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.AddMemberForm;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;

@Mapper
public interface LoginMapper {
	// 회원가입 거절
	int updateAddMemberActiveDenied(String loginId);
	
	// 회원가입 승인
	int updateAddMemberActive(String loginId);
	
	// 매니저 회원가입 승인리스트
	List<Manager> selectAddManagerList();
	
	// 강사 회원가입 승인리스트
	List<Teacher> selectAddTeacherList();
	
	// 학생 회원가입 승인리스트
	List<Student> selectAddStudentList();

	// 바꾸는 비밀번호와 비밀번호 변경 이력 비교
	String lastLoginPwCheck(Login login);
	
	// 학생, 강사, 매니저 비밀번호 변경 이력 테이블 삽입
	int insertPwRecord(Login login);
	
	// 학생, 강사, 매니저 비밀번호 변경
	int updatePw(Login login);
	
	// 학생, 매니저, 강사 비밀번호 찾기(확인)
	int selectAllLoginPw(Map<String, Object> map);

	// 학생, 매니저, 강사 아이디 찾기
	String selectAllLoginId(Map<String, Object> map);
	
	// 로그인 - 로그인 아이디와 비밀번호 확인하고 맞다면 해당 level 출력 
	Login loginAndSelectLevel(Login loginTest);
		
	// 로그인 - 마지막 로그인날짜 업데이트
	int updateLastLoginDate(Login loginTest);
	
	// id 중복 체크 위해 id 리스트 받기
	int selectIdCnt(String id);
	
	// 매니저 회원가입
	int insertMember(AddMemberForm addMemberForm);
	
	// 회원가입시 로그인 테이블에도 추가
	int insertLogin(AddMemberForm addMemberForm);
	
}
