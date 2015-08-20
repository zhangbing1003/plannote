<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/web/taglib/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查询商品</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/web/source/css/bootstrap.min.css">
  	<script type="text/javascript" src="${ctx}/web/source/js/jquery.min.js"></script>
  	<script type="text/javascript" src="${ctx}/web/source/js/bootstrap.min.js"></script>
  	
  	<style type="text/css">
  		.hightLight{
  			color: red;
  		}
  	</style>
  </head>
  
  <body>
    <h1>商城搜索</h1>
    <form action="${ctx}/search.html" method="post" id="searchForm" class="form-inline">
    
    <input type="text" name="keyWord" value="${keyWord }" class="form-control" placeholder="输入商品名称">
    <button type="submit" class="btn btn-primary" onclick="searchForm()">搜索</button>
    <button type="submit" class="btn btn-info" onclick="resetForm()">重置</button>
    <input type="hidden" name="claName" value="${claName }">
    <input type="hidden" name="brandName" value="${brandName }">
    <hr>
    <h4>分类</h4>
    <c:forEach items="${claNameList }" var="d" begin="0" end="20" varStatus="s">
    	<button type="button" onclick="searchClaName('${d}')" class="btn btn-info">${d}</button>
    </c:forEach>
    <hr>
    <h4>品牌</h4>
    <c:forEach items="${brandNameList }" var="d" begin="0" end="10" varStatus="s">
    	<button type="button" onclick="searchBrandName('${d}')" class="btn btn-primary">${d}</button>
    </c:forEach>
    <hr>
    <h4>价格</h4>
	   <input type="text" placeholder="￥" value="${sprice }" name="sprice" class="form-control">--
	   <input type="text" placeholder="￥" value="${eprice }" name="eprice" class="form-control">
    <hr>
    <h3>共搜索到数据：${numFound }条</h3>
    
    <div style="float: right;">
    	当前第${currPage }页,共${totalPage }页
    	<button type="button" onclick="toPage(0)" class="btn btn-primary">上一页</button>
    	<button type="button" onclick="toPage(1)" class="btn btn-primary">下一页</button>
    </div>
    <input type="hidden" name="currPage" value="${currPage }">
    <input type="hidden" name="startCount" value="${startCount }">
    <input type="hidden" name="stepPage" value="${stepPage }">
    <input type="hidden" name="totalPage" value="${totalPage }">
    <script type="text/javascript">
	    function toPage(stat){
	    	var currPage = $("input[name='currPage']").val();
	    	var stepPage = $("input[name='stepPage']").val();
	    	var totalPage = $("input[name='totalPage']").val();
	    	var startCount = parseInt(currPage*stepPage);
	    	
	    	if(parseInt(totalPage)  <= parseInt(currPage)){
	    		return;
	    	}
	    	if(stat==0){
	    		if(currPage == 1){
	    			return;
	    		}
	    		$("input[name='currPage']").val(parseInt(currPage) - 1);
	    		startCount = parseInt((currPage-1)*stepPage);
	    		
	    	}else{
	    		$("input[name='currPage']").val(parseInt(currPage) + 1);
	    	}
	    	$("input[name='startCount']").val(startCount);
	    	
	    	$("#searchForm").submit();
	    	
	    }
    	function searchClaName(claName){
    		$("input[name='claName']").val(claName);
    		$("#searchForm").submit();
    	}
    	function searchBrandName(brandName){
    		$("input[name='brandName']").val(brandName);
    		$("#searchForm").submit();
    		
    	}
    	function resetForm(){
    		$("input[name='keyWord']").val("");
    		$("input[name='brandName']").val("");
    		$("input[name='claName']").val("");
    		$("input[name='currPage']").val("1");
	    	$("input[name='startCount']").val("0");
    		$("#searchForm").submit();
    	}
    </script>
    <table class="table table-hover">
    	<tr class="success">
    		<th>商品ID</th>
    		<th>商品名称</th>
    		<th>价格</th>
    		<th>描述</th>
    		<th>分类</th>
    		<th>品牌</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${goodsList }" var="od" varStatus="s">
    		<tr>
    			<td>${od.id }</td>
    			<td>${od.name }</td>
    			<td>${od.price }</td>
    			<td>${od.subtitle }</td>
    			<td>${od.claName }</td>
    			<td>${od.brandName }</td>
    			<td>
    				<button type="button" onclick="deleteIndex('${od.id }')" class="btn btn-danger">删除</button>
    				<button type="button" onclick="toPage(1)" class="btn btn-primary">更新</button>
    				<button type="button" onclick="addCart('${od.id }')" class="btn btn-primary">加入购物车</button>
    			</td>
    		</tr>
    	</c:forEach>
    </table>
    </form>
  </body>
  <script type="text/javascript">
  		function searchForm(){
  			$("#searchForm").submit();
  		}
  	//删除索引
  	function deleteIndex(id){
  		$.post("${ctx}/deleteIndex.html",{'goodsId':id},function(data){
  			if(data == true){
  				alert('删除成功！');
  				window.location.reload();
  			}
  		})
  	}
  	//加入购物车
  	function addCart(id){
	  	$.post("${ctx}/addCart.html",{'goodsId':id},function(data){
	  			if(data == true){
	  				alert('添加成功！');
	  				window.location.reload();
	  			}
	  		})
  		
  	}
  </script>
</html>
