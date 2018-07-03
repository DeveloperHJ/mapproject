package com.project.pickplace.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pickplace.dao.PinDAO;
import com.project.pickplace.dto.PinInfoDTO;


@RestController
@RequestMapping("/pin")
public class PinController {
	private static final Logger logger = LoggerFactory.getLogger(PinController.class);
	
	@Autowired
	@Qualifier("pinDAOImplXML")
	PinDAO pindao;
	
	//RestController 테스트
	@RequestMapping("/hello")
	public String hello() {
		return "hello!";
	}
	
	//핀등록
	@RequestMapping(value="/insert", method=POST)
	public ResponseEntity<String> insert(@RequestBody PinInfoDTO pindto)
	{
		System.out.println("insert POST... : " + pindto.toString());
		logger.info("insert POST ...");
		ResponseEntity<String> responseEntity = null;
		logger.info("글쓰기 : " + pindto.toString());
		
		try {
			pindao.insert(pindto);
			responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}
}
