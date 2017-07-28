import com.cvte.crud.bean.Article;
import com.cvte.crud.bean.ArticleExample;
import com.cvte.crud.dao.ArticleMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
    ArticleMapper articleMapper;

	@Autowired
	SqlSession sqlSession;

	@Test
	public void testCRUD(){
		articleMapper.insertSelective(new Article(UUID.randomUUID().toString(),"ssssssss","12323","fdfdf",null));

	}
}
