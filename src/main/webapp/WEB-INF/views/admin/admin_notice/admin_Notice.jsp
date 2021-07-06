<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 관리</title>


<link href="${ pageContext.servletContext.contextPath }/resources/css/style.css/admin/admin_Notice.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.servletContext.contextPath }/resources/css/style.css/admin_index.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:import url="/WEB-INF/views/admin/admincommon/header.jsp" charEncoding="UTF-8"></c:import>
    <c:import url="/WEB-INF/views/admin/admincommon/Main.jsp" charEncoding="UTF-8"></c:import>
    <div class="page">
	    <h2 id="title"> 공지사항 목록 </h2>
	    <br>
	    	<div class="notice_list">
	    	<hr>
			<br>
	    		<table class="notice_Table">
	    			<tr>
	    				<th  width="80px">번호</th>
	    				<th  width="150px">제목</th>
	    				<th  width="500px">내용</th>
	    				<th  width="90px"><input type="checkbox" id="all" value="전체선택" onclick="selectAll();">전체선택</th>
	    			</tr>
	    			<c:if test="${ list != null }">
					<c:forEach var="ad" items="${ list }">
			    		<tr class="click">
			    			<td>${ ad.bNo }</td>
			    			<td style="border-left: 1px solid black" onclick="noticeInfo('${ ad.bNo }')">${ ad.bTitle}</td>
			    			<td style="border-left: 1px solid black" onclick="noticeInfo('${ ad.bNo }')">${ ad.bContent }</td>
			    			<td width="90px" style="border-left: 1px solid black" ><input type="checkbox" name="notice_select" onclick="selectOne();" value="${ ad.bNo }"></td>
			    		</tr>
			
	    			</c:forEach>
	    		</c:if>
	    		<c:if test="${ list == null }">
					<tr>
						<td colspan="6">조회된 리스트가 없습니다.</td>
					</tr>
				</c:if>
	    			
	    		</table>
	    	<br><hr><br>
	    	<div class="buttonArea">
	    		<button id="insert_no">공지 작성</button>
				<button id="delete_no">공지 삭제</button>
	    	</div>
    	</div>
    	
    	<!-- 페이징 -->
			<table id="pagingArea">	
		<!-- 페이징 처리 -->
			<tr align="center" height="20" id="buttonTab">
				<td colspan="6">
				
					<!-- [이전] -->
					<c:if test="${ pi.currentPage <= 1 }">
						<button>&lt;</button>
					</c:if>
					<c:if test="${ pi.currentPage > 1 }">
						<c:url var="before" value="adminBoard.ad">
							<c:param name="page" value="${ pi.currentPage - 1 }"/>
						</c:url>
						<a href="${ before }"><button>&lt;</button></a>
					</c:if>
					
					<!-- 페이지 -->
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
						<c:if test="${ p eq pi.currentPage }">
							<button><font color="red" size="4"><b>${ p }</b></font></button>
						</c:if>
						
						<c:if test="${ p ne pi.currentPage }">
							<c:url var="pagination" value="adminBoard.ad">
								<button><c:param name="page" value="${ p }"/></button>
							</c:url>
							<a href="${ pagination }"><button>${ p }</button></a>
						</c:if>
					</c:forEach>
					
					<!-- [다음] -->
					<c:if test="${ pi.currentPage >= pi.maxPage }">
						<button>&gt;</button>
					</c:if>
					<c:if test="${ pi.currentPage < pi.maxPage }">
						<c:url var="after" value="adminBoard.ad">
							<button><c:param name="page" value="${ pi.currentPage + 1 }"/></button>
						</c:url> 
						<a href="${ after }"><button>&gt;</button></a>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
	
	<script>
		var all = document.getElementById("all");
		var category = document.getElementsByName("notice_select");
		
		function selectAll(){
			if(all.checked){
				for(var i = 0; i < category.length; i++){
					category[i].checked = true;
				}
			} else {
				for(var i = 0; i < category.length; i++){
					category[i].checked = false;
				}
			}
		}
		
		function selectOne(){
			var count = 0;
			
			for(var i = 0; i < category.length; i++){
				if(category[i].checked){
					count++;
				}
			}
			
			if(count != 12){
				all.checked = false;
			} else {
				all.checked = true;
			}
		}
		
		function noticeInfo(notice_no){
			var url ='<%=request.getContextPath()%>/noticeDetailForm.ad?id=' + notice_no;
			window.open(url, 'noticeInfo', 'width=1200px, height=820px');
		}
		
		$('.click').on('mouseover',function(){
			$(this).closest('tr').css({"background":"#efefef85","cursor":"pointer"});
		}).on('mouseout',function(){
			$(this).closest('tr').css({"background":"","color":"","cursor":""});
		});
		
		$('#insert_no').on('click', function(){
			var url ='<%=request.getContextPath()%>/binsertView.bo';
			window.open(url, 'noticeInfo', 'width=1200px, height=820px');
		});
		
		$('#delete_no').on('click', function(){
			var checkArr = [];
			$('input[name="notice_select"]:checked').each(function() {
				checkArr.push($(this).val());
			});
			if (confirm('해당 공지사항을 삭제하시겠습니까?')) {
				$.ajax({
					type: 'post',
					url:'bdelete.bo',
					data:{
						check:checkArr
						
					},
					success:function(data){
						window.location.reload();
					}		
				});
			}
		});
	</script>
</body>
</html>