import com.cvte.crud.bean.Article;
import com.cvte.crud.dao.ArticleMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {

	@Autowired
	ArticleMapper articleMapper;

	@Autowired
	SqlSession sqlSession;
	@Test
	public void testCRUD(){
		//System.out.println(departmentMapper);
		System.out.println(articleMapper);
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		/*for(int i = 0;i<1000;i++){
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Article(null,uid,"12323","fdfdf",null));
		}
		System.out.println("批量完成");*/
		//mapper.deleteByPrimaryKey(3);
	//	mapper.updateByPrimaryKey(new Article(9,"ccccc" ,"ddddd","hahahhah",null));
		//System.out.println(mapper.selectByExample());
		System.out.println("删除");
		mapper.selectByPrimaryKey(123);
	}
}
