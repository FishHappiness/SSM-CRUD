package com.cvte.crud.controller;

import com.cvte.crud.bean.Article;
import com.cvte.crud.bean.Msg;
import com.cvte.crud.dao.ArticleMapper;
import com.cvte.crud.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @ResponseBody
    @RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
    public Msg deleteArticle(@PathVariable("ids")String ids){
        //批量删除
        if(ids.contains("-")){
            List<String> del_ids = new ArrayList<String>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(string);
            }
            articleService.deleteBatch(del_ids);
        }else{
            articleService.deleteArticle(ids);
        }
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value="/emp/{articleId}",method=RequestMethod.PUT)
    public Msg saveArticle(Article article, HttpServletRequest request){
        System.out.println("请求体中的值："+request.getParameter("title"));
        System.out.println("将要更新的文章数据："+article);
        articleService.updateArticle(article);
        return Msg.success();
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Msg getArticle(@PathVariable("id")String id){
        Article article = articleService.getArticle(id);
        return Msg.success().add("art", article);
    }

    /**
     * 文章信息保存
     * @return
     */
    @RequestMapping(value="/emp",method=RequestMethod.POST)
    @ResponseBody
    public Msg saveAuthor(@Valid Article article, BindingResult result){
            articleService.saveArticle(article);
            return Msg.success();
    }

    /**
     * 查询文章数据（分页查询）
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getArtWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Article> arts = articleService.getAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo即可。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(arts, 5);
        return Msg.success().add("pageInfo", page);
    }
}
