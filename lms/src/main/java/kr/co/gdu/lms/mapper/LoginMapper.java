package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.MemberForm;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;

@Mapper
public interface LoginMapper {
	// 휴먼계정 아이디 출력
	List<Map<String,Object>> selectDormantMemberId();
	
	// 모든 회원 로그인 안한지 며칠 됐는지 날짜 출력
	List<Map<String, Object>> selectDiffDayList();
	
	// 해당 아이디가 비밀번호 변경한 최근 날짜 출력
	String selectPwRecordDate(String loginId);
	
	// 비밀번호 변경이력 업데이트
	int updatePwRecord(String updateDate);
	
	// 마지막 로그인 날짜로부터 며칠이 지났는지
	int selectDiffDay(String loginId);
	
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
	int updateLastLoginDate(String loginId);
	
	// id 중복 체크 위해 id 리스트 받기
	int selectIdCnt(String id);
	
	// 매니저 회원가입
	int insertMember(MemberForm addMemberForm);
	
	// 회원가입시 로그인 테이블에도 추가
	int insertLogin(MemberForm addMemberForm);
	
	// 로그인시 레벨받기
	int selectLevelByLoginId(String loginId);
	
}
