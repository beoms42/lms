package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class License {
	private int licenseNo;
	private String licenseName;
	private String loginId;
	private String getLicenseDate;
	private String licenseAgency;
	private String createDate;
	private String updateDate;
}
