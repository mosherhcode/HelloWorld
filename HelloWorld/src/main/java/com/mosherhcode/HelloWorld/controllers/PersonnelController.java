package com.mosherhcode.HelloWorld.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mosherhcode.HelloWorld.daos.PersonnelDao;
import com.mosherhcode.HelloWorld.models.Personnel;

@RestController
public class PersonnelController {

	@Autowired
	PersonnelDao pDao;
	
	@RequestMapping(path = "/personnel", method = RequestMethod.GET)
	public List<Personnel> getPersonnel(){
		return pDao.getAllPersonnel();
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(path = "/personnel", method = RequestMethod.POST)
	public Personnel addPersonnel(@RequestBody Personnel personnel) {
		return pDao.addPersonnel(personnel);
	}
}
