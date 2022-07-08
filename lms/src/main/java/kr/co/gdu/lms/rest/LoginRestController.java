package kr.co.gdu.lms.rest;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.service.LoginService;
import kr.co.gdu.lms.vo.Login;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class LoginRestController {

	@Autowired LoginService loginService;
	
	
	// 바꾸는 비밀번호와 비밀번호 변경 이력 비교
	@PostMapping("/lastLoginPwCheck")
	public String lastLoginPwCheck(Login login) {
		log.debug(CF.PHW+"LoginRestController.lastLoginPwCheck.post login : "+login+CF.RS );
		
		String pwCheckType = loginService.lastLoginPwCheck(login);
		log.debug(CF.PHW+"LoginRestController.lastLoginPwCheck.post pwCheckType : "+pwCheckType+CF.RS );
		
		String pwCheck = String.valueOf(pwCheckType); // String 형변환
		log.debug(CF.PHW+"LoginRestController.lastLoginPwCheck.post pwCheck : "+pwCheck+CF.RS );
		
		if(pwCheck.equals("1")) { // 최근 비밀번호가 바꾸려는 비밀번호랑 일치함
			return "false"; 
		}
		
		return "";
	}
	
	// email 중복체크
	@GetMapping("/email")
	public String email(@RequestParam(value="email") String email
						, @RequestParam(value="addChk") String addChk) {
		
		log.debug(CF.OHI+"LoginRestController.email : "+email+CF.RS);
		log.debug(CF.OHI+"LoginRestController.addChk : "+addChk+CF.RS);
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		int count = loginService.emailCheck(email, addChk);
		log.debug(CF.OHI+"LoginRestController.email.get count : "+count+CF.RS);
		
		if(count >= 1) { // 중복된 이메일이 있다면
			return "false"; // false값 리턴
		}
		
		return email;
	}
	
	// id 중복체크
	@GetMapping("/id")
	public String id(@RequestParam(value="id") String id) {
		
		// id 중복 체크해서 중복된 아이디 개수 받아오기
		int count = loginService.idCheck(id);
		log.debug(CF.OHI+"LoginRestController.id.get count : "+count+CF.RS);
		
		if(count == 1) { // 중복된 아이디가 있다면
			return "false"; // false값 리턴
		}
		
		return id;
	}
	
	//주소 검색
	@GetMapping("/searchAddr")
	public String searchAddr(@RequestParam(value="keyword") String keyword) {
		
		//OPEN API 호출 URL 정보 설정
		String confmKey = "devU01TX0FVVEgyMDIyMDYyMDA4NTcxNDExMjcwNDY="; // 인증키
		String resultType = "json"; // json타입으로 넣기
		int countPerPage = 100000000;
		StringBuffer sb = null; // return 타입 선언
		
		try {
			String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+confmKey+"&resultType="+resultType;
			
			URL url = new URL(apiUrl); // 인터넷상 자원 참조하기 위해
			//UTF-8로 인코딩해서 묶어서 보내기 위해 br에 담기
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		
			sb = new StringBuffer();
			
			String tempStr = null;
			while((tempStr = br.readLine()) != null) { // 한줄씩 null아니면 tempStr에 담기
				
				sb.append(tempStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString(); // return 타입 string이라 object -> string으로 형 변환해주기
	}
	
}
