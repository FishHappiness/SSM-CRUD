<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文章列表</title>
	<%
		pageContext.setAttribute("APP_PATH", request.getContextPath());
	%>
	<script type="text/javascript"
			src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<link
			href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
			rel="stylesheet">
	<script
			src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 文章修改的模态框 -->
<div class="modal fade" id="artUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">员工修改</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label">author</label>
						<div class="col-sm-10">
							<p class="form-control-static" id="author_update_static"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">title</label>
						<div class="col-sm-10">
							<input type="text" name="title" class="form-control" id="title_update_input" placeholder="谁的青春不迷茫">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">content</label>
						<div class="col-sm-10">
							<input type="text" name="content" class="form-control" id="content_update_input" placeholder="那年夏天.....">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">publication</label>
						<div class="col-sm-10">
							<input type="text" name="pubTime" class="form-control" id="pubTime_update_input" placeholder="2000-12-20">
							<span class="help-block"></span>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="art_update_btn">更新</button>
			</div>
		</div>
	</div>
</div>

<!-- 文章添加的模态框 -->
<div class="modal fade" id="artAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">文章添加</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label">author</label>
						<div class="col-sm-10">
							<input type="text" name="author" class="form-control" id="author_add_input" placeholder="zhangsan">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">title</label>
						<div class="col-sm-10">
							<input type="text" name="title" class="form-control" id="title_add_input" placeholder="《谁的青春不迷茫》">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">content</label>
						<div class="col-sm-10">
							<input type="text" name="content" class="form-control" id="content_add_input" placeholder="将头发梳成大人模样...">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">publicationTime</label>
						<div class="col-sm-10">
							<input type="text" name="pubTime" class="form-control" id="pubTime_add_input" placeholder="1995-12-23">
							<span class="help-block"></span>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="art_save_btn">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 搭建显示页面 -->
<div class="container">
	<!-- 标题 -->
	<div class="row">
		<div class="col-md-12" style="text-align: center">
			<h1>文章管理系統</h1>
		</div>
	</div>
	<!-- 按钮 -->
	<div class="row">
		<div class="col-md-2 col-md-offset-10">
			<button class="btn btn-primary" id="art_add_modal_btn">新增</button>
			<button class="btn btn-danger" id="art_delete_all_btn">删除</button>
		</div>
	</div>
	<!-- 显示表格数据 -->
	<div class="row">
		<div class="col-md-12">
			<table class="table table-hover" id="arts_table">
				<thead>
				<tr>
					<th>
						<input type="checkbox" id="check_all"/>
					</th>
					<th>#</th>
					<th>Author</th>
					<th>Title</th>
					<th>Content</th>
					<th>PublicationTime</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>

	<!-- 显示分页信息 -->
	<div class="row">
		<!--分页文字信息  -->
		<div class="col-md-6" id="page_info_area"></div>
		<!-- 分页条信息 -->
		<div class="col-md-6" id="page_nav_area"></div>
	</div>
</div>

<script type="text/javascript">

    var totalRecord,currentPage;
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function(){
        //去首页
        to_page(1);
    });

    function to_page(pn){
        $.ajax({
            url:"${APP_PATH}/arts",
            data:"pn="+pn,
            type:"GET",
            success:function(result){
                //1、解析并显示员工数据
                build_arts_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);
            }
        });
    }

    function build_arts_table(result){
        //清空table表格
        $("#arts_table tbody").empty();
        var arts = result.extend.pageInfo.list;
        $.each(arts,function(index,item){
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
            var articleId = $("<td></td>").append(item.articleId);
            var author = $("<td></td>").append(item.author);
            var title = $("<td></td>").append(item.title);
            var content = $("<td></td>").append(item.content);
            var publicationTime = $("<td></td>").append(new Date(item.publicationTime).toLocaleString());
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前文章id
            editBtn.attr("edit-id",item.articleId);
            var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的文章id
            delBtn.attr("del-id",item.articleId);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);

            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>").append(checkBoxTd)
                .append(articleId)
                .append(author)
                .append(title)
                .append(content)
                .append(publicationTime)
                .append(btnTd)
                .appendTo("#arts_table tbody");
        });
    }
    //解析显示分页信息
    function build_page_info(result){
        $("#page_info_area").empty();
        $("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+
            result.extend.pageInfo.pages+"页,总"+
            result.extend.pageInfo.total+"条记录");
        totalRecord = result.extend.pageInfo.total;
        currentPage = result.extend.pageInfo.pageNum;
    }
    //解析显示分页条，点击分页要能去下一页....
    function build_page_nav(result){
        //page_nav_area
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");

        //构建元素
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        if(result.extend.pageInfo.hasPreviousPage == false){
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        }else{
            //为元素添加点击翻页的事件
            firstPageLi.click(function(){
                to_page(1);
            });
            prePageLi.click(function(){
                to_page(result.extend.pageInfo.pageNum -1);
            });
        }



        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
        if(result.extend.pageInfo.hasNextPage == false){
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        }else{
            nextPageLi.click(function(){
                to_page(result.extend.pageInfo.pageNum +1);
            });
            lastPageLi.click(function(){
                to_page(result.extend.pageInfo.pages);
            });
        }



        //添加首页和前一页 的提示
        ul.append(firstPageLi).append(prePageLi);
        //1,2，3遍历给ul中添加页码提示
        $.each(result.extend.pageInfo.navigatepageNums,function(index,item){

            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if(result.extend.pageInfo.pageNum == item){
                numLi.addClass("active");
            }
            numLi.click(function(){
                to_page(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页 的提示
        ul.append(nextPageLi).append(lastPageLi);

        //把ul加入到nav
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }

    //清空表单样式及内容
    function reset_form(ele){
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //点击新增按钮弹出模态框。
    $("#art_add_modal_btn").click(function(){
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#artAddModal form");
        //弹出模态框
        $("#artAddModal").modal({
            backdrop:"static"
        });
    });

    //点击保存，保存文章。
    $("#art_save_btn").click(function(){

        //发送ajax请求保存文章
        $.ajax({
            url:"${APP_PATH}/art",
            type:"POST",
            data:$("#artAddModal form").serialize(),
            success:function(result){
                alert(result.code);
                if(result.code == 100){
                    //文章保存成功；
                    //1、关闭模态框
                    $("#artAddModal").modal('hide');
                    //2、来到最后一页，显示刚才保存的数据,发送ajax请求显示最后一页数据即可
                    to_page(totalRecord);
                }else{
                    if(undefined != result.extend.errorFields.empName){
                        //显示名字的错误信息
                        show_validate_msg("#author_add_input", "error", result.extend.errorFields.author);
                    }
                }
            }
        });
    });

    $(document).on("click",".edit_btn",function(){
        //查出文章信息并显示
        getEmp($(this).attr("edit-id"));
        //把员工的id传递给模态框的更新按钮
        $("#art_update_btn").attr("edit-id",$(this).attr("edit-id"));
        $("#artUpdateModal").modal({
            backdrop:"static"
        });
    });
    function getEmp(id){
        $.ajax({
            url:"${APP_PATH}/art/"+id,
            type:"GET",
            success:function(result){
                var artData = result.extend.emp;
                $("#author_update_static").text(artData.author);
                $("#title_update_input").text(artData.title);
                $("#content_update_input").text(artData.content);
                $("#pubTime_update_input").text(artData.pubTime);
            }
        });
    }

    //点击更新，更新文章信息
    $("#art_update_btn").click(function(){
        //发送ajax请求保存更新的文章数据
        $.ajax({
            url:"${APP_PATH}/art/"+$(this).attr("edit-id"),
            type:"PUT",
            data:$("#artUpdateModal form").serialize(),
            success:function(result){
                //1、关闭对话框
                $("#artUpdateModal").modal("hide");
                //2、回到本页面
                to_page(currentPage);
            }
        });
    });

    //单个删除
    $(document).on("click",".delete_btn",function(){
        //1、弹出是否确认删除对话框
        var author = $(this).parents("tr").find("td:eq(2)").text();
        var articleId = $(this).attr("del-id");
        //alert($(this).parents("tr").find("td:eq(1)").text());
        if(confirm("确认删除【"+author+"】吗？")){
            //确认，发送ajax请求删除即可
            $.ajax({
                url:"${APP_PATH}/art/"+articleId,
                type:"DELETE",
                success:function(result){
                    alert(result.msg);
                    //回到本页
                    to_page(currentPage);
                }
            });
        }
    });

    //完成全选/全不选功能
    $("#check_all").click(function(){
        $(".check_item").prop("checked",$(this).prop("checked"));
    });

    //check_item
    $(document).on("click",".check_item",function(){
        //判断当前选择中的元素是否5个
        var flag = $(".check_item:checked").length==$(".check_item").length;
        $("#check_all").prop("checked",flag);
    });

    //点击全部删除，就批量删除
    $("#art_delete_all_btn").click(function(){
        //
        var authors = "";
        var del_idstr = "";
        $.each($(".check_item:checked"),function(){
            //this
            authors += $(this).parents("tr").find("td:eq(2)").text()+",";
            //组装文章id字符串
            del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
        });
        authors = authors.substring(0, authors.length-1);
        //去除删除的id多余的
        del_idstr = del_idstr.substring(0, del_idstr.length-1);
        if(confirm("确认删除【"+authors+"】吗？")){
            //发送ajax请求删除
            $.ajax({
                url:"${APP_PATH}/art/"+del_idstr,
                type:"DELETE",
                success:function(result){
                    alert(result.msg);
                    //回到当前页面
                    to_page(currentPage);
                }
            });
        }
    });
</script>
</body>
</html>