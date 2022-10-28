package com.spring.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebmvcApplication { //핵심 메인 실행부

	public static void main(String[] args) { //임베디드 톰캣 (이거 있어야 돌아감)
		SpringApplication.run(WebmvcApplication.class, args); //(DB설정과 포트설정을 잘 해줘야함)


	}

}
