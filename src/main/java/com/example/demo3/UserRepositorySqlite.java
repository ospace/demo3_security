package com.example.demo3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//http://asfirstalways.tistory.com/299
//https://docs.spring.io/spring-security/site/docs/4.0.x/reference/html/appendix-schema.html
/* 화면표시(CLI mode): https://www.sqlite.org/cli.html
 * .mode column
 * .header on
 */
@Component
public class UserRepositorySqlite {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void init() {
		jdbcTemplate.update("CREATE TABLE user ( id text PRIMARY KEY, pwd text NOT NULL)");
		jdbcTemplate.update("CREATE TABLE user_role ( id text, name text NOT NULL)");
	}
	
	public void add(User user) {
		//users.put(user.getId(), user);
		jdbcTemplate.update("INSERT INTO user(id, pwd) VALUES(?,?)", user.getId(), user.getPwd());
		for(UserRole it : user.getRoles()) {
			jdbcTemplate.update("INSERT INTO user_role (id, name) VALUES(?,?)", user.getId(), it.getName());
		}
	}
	
	public User get(String id) {
		return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public List<UserRole> getUserRole(String id) {
		return (List<UserRole>) jdbcTemplate.query("SELECT * FROM user_role WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<UserRole>(UserRole.class));
	}
}
