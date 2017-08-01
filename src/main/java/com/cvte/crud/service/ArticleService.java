package com.cvte.crud.service;

import com.cvte.crud.bean.Article;
import com.cvte.crud.bean.ArticleExample;
import com.cvte.crud.dao.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> getAll() {
        return articleMapper.selectByExample(null);
    }

    /**
     * 文章保存
     * @param article
     */
    public void saveArticle(Article article) {
        articleMapper.insertSelective(article);
    }

    /**
     * 按照文章id查询文章
     * @param id
     * @return
     */
    public  Article getArticle(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }

    /**
     * 文章更新
     * @param article
     */
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * 文章删除
     * @param id
     */
    public void deleteArticle(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {

        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        //delete from xxx where emp_id in(1,2,3)
        criteria.andArticleIdIn(ids);
        articleMapper.deleteByExample(example);
    }
}
