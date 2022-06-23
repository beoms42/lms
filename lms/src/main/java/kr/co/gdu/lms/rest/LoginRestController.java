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
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class LoginRestController {

	@Autowired LoginService loginService;
	
	@PostMapping("/idCheck")
	public String idCheck(@RequestParam(value="id") String id) {
		
		// id 중복 체크해서 중복된 아이디 개수 받아오기
		int count = loginService.idCheck(id);
		log.debug(CF.OHI+"LoginRestController.idCheck.post count : "+count+CF.RS);
		
		if(count == 1) { // 중복된 아이디가 있다면
			return "false"; // false값 리턴
			
		}
		
		return id;
	}
	
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
