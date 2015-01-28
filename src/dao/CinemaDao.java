package dao;

import java.util.List;
import model.Cinema;

public interface CinemaDao {
	public List<Cinema> findAll();							//查找所有的电影院
	public Cinema find(String column,String value);			//根据影院名称等信息查找电影院
}
