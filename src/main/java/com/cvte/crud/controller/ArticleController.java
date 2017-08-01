package com.cvte.crud.controller;

import java.util.ArrayList;
import java.util.List;
import com.cvte.crud.bean.Article;
import com.cvte.crud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cvte.crud.bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    /**
     * 根据id查询文章,支持单个删除和批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/art/{ids}",method=RequestMethod.DELETE)
    public Msg deleteArticle(@PathVariable("ids")String ids){
        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<Integer>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            articleService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            articleService.deleteArticle(id);
        }
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value="/art/{articleId}",method=RequestMethod.PUT)
    public Msg updateArticle( Article article){
        articleService.updateArticle(article);
        return Msg.success();
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @RequestMapping(value="/art/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Msg getArticle(@PathVariable("id")Integer id){
        Article article = articleService.getArticle(id);
        return Msg.success().add("art", article);
    }

    /**
     * 文章信息保存
     * @param article
     * @return
     */
    @RequestMapping(value="/art",method=RequestMethod.POST)
    @ResponseBody
    public Msg saveArticle(Article article){
            articleService.saveArticle(article);
            return Msg.success();
    }

    /**
     * 查询文章数据（分页查询）
     * @param pn
     * @return
     */
    @RequestMapping("/arts")
    @ResponseBody
    public Msg getArtsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Article> arts = articleService.getAll();

        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(arts, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 测试ResquestBody
     */
    @RequestMapping(value ="/saveArt", method = RequestMethod.POST)
    @ResponseBody
    public void saveArt(@RequestBody List<Article> arts){
        System.out.println("test.....................");
    }
}
