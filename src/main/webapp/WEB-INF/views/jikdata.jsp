<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
function hun(no){
	$('#showData').empty();

	$.ajax({
		type:"get",
		url:"gogek",
		data:{"num":no},
		dataType:"json",
		success:function(gogekData){
			var str = "관리고객";
			str += "<table board='1'>";
			str += "<tr><th>고객번호</th><th>고객명</th><th>고객전화</th></tr>";
			var list = gogekData.datas;	//배열의 대표명 적용
			$(list).each(function(index,obj){
				str += "<tr>";
				str += "<td>" + obj["gogek_no"] + "</td>";
				str += "<td>" + obj["gogek_name"] + "</td>";
				str += "<td>" + obj["gogek_tel"] + "</td>";
				str += "</tr>";
			});
			str += "<tr><td colspan='3'> 고객수: "+$(list).length+" 명 </td></tr>";
			str += "</table>";
			$("#showData").append(str);
		},
		error:function(){
			$("#showData").text("에러 발생!");
		}
	});
}

</script>
</head>
<body>
<h2>* 직원 목록  *</h2>
<table border="1">
	<tr><td>직원번호</td><td>직원명</td><td>부서전화</td><td>직급</td><td>관리고객</td></tr>
	<c:forEach var="s" items="${data}">
		<tr>
			<td>${s.jikwon_no }</td>
			<!-- 
			<td><a href="gogek?num=${s.jikwon_no})">${s.jikwon_name }</a></td>
			 -->
			 <td><a href="javascript:hun('${s.jikwon_no}')">${s.jikwon_name }</a></td>
			<td>${s.buser_tel }</td>
			<td>${s.jikwon_jik }</td>
			<td>${s.gogeksu }</td>
		</tr>	
	</c:forEach>
</table>
<br>
<div id="showData">aaa</div>
</body>
</html>

