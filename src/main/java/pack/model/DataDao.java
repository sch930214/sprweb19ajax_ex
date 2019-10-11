package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class DataDao extends SqlSessionDaoSupport{
	
	//연결 - dataMapper에 접근 가능
	@Autowired
	public DataDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public List list() throws DataAccessException {
		return getSqlSession().selectList("selectDataAll");
	}
	
	
	public List<JikwonDto> gogekList(String num){
		List<JikwonDto> list = getSqlSession().selectList("selectGogek",num);
		return list;
	}
	
}
