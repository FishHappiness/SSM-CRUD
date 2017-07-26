import com.cvte.crud.bean.Article;
import com.cvte.crud.dao.ArticleMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
    ArticleMapper articleMapper;

	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 测试DepartmentMapper
	 */
	@Test
	public void testCRUD(){
		//System.out.println(UUID);
		//articleMapper.insertSelective(new Article(UUID.randomUUID().toString(),"sssss","aaaa","dddd",null));
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		for(int i = 0;i<1000;i++){
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Article(UUID.randomUUID().toString(),uid,"12323","fdfdf",null));
		}

	}
}
