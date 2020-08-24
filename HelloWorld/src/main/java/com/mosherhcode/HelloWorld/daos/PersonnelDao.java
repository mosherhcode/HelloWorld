package com.mosherhcode.HelloWorld.daos;

import java.util.List;

import com.mosherhcode.HelloWorld.models.Personnel;

public interface PersonnelDao {

	public List<Personnel> getAllPersonnel();

	public Personnel addPersonnel(Personnel personnel);

	public Personnel getPersonnelById(String id);

}
