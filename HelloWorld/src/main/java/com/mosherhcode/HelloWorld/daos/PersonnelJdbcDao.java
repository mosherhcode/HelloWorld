package com.mosherhcode.HelloWorld.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.mosherhcode.HelloWorld.models.Personnel;

@Component
public class PersonnelJdbcDao implements PersonnelDao {

	JdbcTemplate jdbcTemplate;
	
	public PersonnelJdbcDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate; 
	}
	
	@Override
	public List<Personnel> getAllPersonnel() {
		String sql = "SELECT * FROM personnel ORDER BY username";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		List<Personnel> personnelList = new ArrayList<Personnel>(0);
		
		while(results.next()) {
			personnelList.add(mapRowToPersonnel(results));
		}
		
		return personnelList;
	}
	
	@Override
	public Personnel getPersonnelById(String id) {
		String sql = "SELECT * FROM personnel WHERE personnel_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, UUID.fromString(id));
		
		if(results.next()) {
			return mapRowToPersonnel(results);
		}
		
		return null;
	}
	
	@Override
	public Personnel addPersonnel(Personnel personnel) {
		String sql = 	"INSERT INTO personnel (username, first_name, last_name, email, date_of_birth, eye_color) "
						+ "VALUES (?,?,?,?,?,?) RETURNING personnel_id";
		String pId = jdbcTemplate.queryForObject(sql, UUID.class, 
				personnel.getUsername(), 
				personnel.getFirstName(),
				personnel.getLastName(),
				personnel.getEmail(),
				personnel.getDateOfBirth(),
				personnel.getEyeColor()
				).toString();
		
		return getPersonnelById(pId);
	}

	private Personnel mapRowToPersonnel(SqlRowSet results) {
		Personnel personnel = new Personnel();
		personnel.setPersonnelId(results.getString("personnel_id"));
		personnel.setUsername(results.getString("username"));
		personnel.setFirstName(results.getString("first_name"));
		personnel.setLastName(results.getString("last_name"));
		personnel.setEmail(results.getString("email"));
		personnel.setDateOfBirth(results.getDate("date_of_birth") == null ? results.getDate("date_of_birth").toLocalDate() : null);
		personnel.setEyeColor(results.getString("eye_color"));
		return personnel;
	}

	

}
