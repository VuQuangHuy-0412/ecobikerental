<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Return Bike</title>
<body>
	<div class="row">
		<div class="span12" style="margin-left: 40px;">
			<div class="well well-small row" style="background-color: cornflowerblue;">
				<h3>${ status }</h3>
			</div>
			<div class="row">
				<div class="span3">&nbsp;</div>
				<div class="span4">
					<button style="background-color: darkgray;" onclick="window.location.href='/EcoBikeRental/list-dock'"><b>Return To List Dock</b></button>
				</div>
				<div class="span2">&nbsp;</div>
			</div>
		</div>
	</div>
</body>