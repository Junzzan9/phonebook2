<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
//request 속 데이터 꺼내기 (getAttribute) pList (형변환시켜야함)
List<PersonVo> personList = (List<PersonVo>) request.getAttribute("pList");

System.out.println("jsp =========================");
System.out.println(personList);

/*
int age = (int) request.getAttribute("age");
String name = (String) request.getAttribute("name");

System.out.println(age + ", " + name);
*/
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전화번호 리스트</h1>
	<p>입력한 정보 내역입니다</p>
	<%
	for (int i = 0; i < personList.size(); i++) {
	%>
	<table border="1">
		<tr>
			<td>이름</td>
			<td><%=personList.get(i).getName()%></td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td><%=personList.get(i).getHp()%></td>
		</tr>
		<tr>
			<td>회사</td>
			<td><%=personList.get(i).getCompany()%></td>
		</tr>
		<tr>
			<td><form action="/phonebook2/pbc" method="get">
					<input type="hidden" name="action" value="uform"> 
					<input type="hidden" name="id" value="<%=personList.get(i).getPersonId()%>">
					<button type="submit">[수정폼]</button>
				</form>
			</td>
			<td><form action="/phonebook2/pbc" method="get">
					<input type="hidden" name="action" value="delete"> 
					<input type="hidden" name="id" value="<%=personList.get(i).getPersonId()%>">
					<button type="submit">[삭제]</button>
				</form>
			</td>
		</tr>
	</table>
	<br>
	<%
	}
	%>
	
	<form action="/phonebook2/pbc" method="get">
					<input type="hidden" name="action" value="wform"> 
					<button type="submit">[신규번호 등록하기]</button> 
	</form>
</body>
</html>