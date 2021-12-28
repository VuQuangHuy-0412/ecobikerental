<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<title>Insert Card Information</title>
<body>
	<div class="row">
		<div class="span12" style="margin-left: 40px;">
			<div class="well well-small row" style="background-color: cornflowerblue;">
				<h3>Insert Your Card Infomation</h3>
			</div>
			<div class="container-fluid row">
				<div class="span12">
					<form method="GET" action="/EcoBikeRental/process-return" class="" id="formCard">
						<label for="cardCode" style="text-align: left;"><b>Insert Card Code: </b></label><br>
						<input type="text" placeholder="Insert Barcode" class="span6" id="cardCode" name="cardCode" required><br>
						<br>
						<label for="owner" style="text-align: left;"><b>Insert Owner: </b></label><br>
						<input type="text" placeholder="Insert Barcode" class="span6" id="owner" name="owner" required>
						
						<input type="hidden" id="bikeId" name="bikeId" value="${ bikeId }" >
						<input type="hidden" id="dockId" name="dockId" value="${ dockId }" >
						<input type="hidden" id="point" name="point" value="${ point }" >
						<input type="hidden" id="refundAmount" name="refundAmount" value="${ refundAmount }" >
					</form>
				</div>
            </div>
			<div class="row">
				<div class="span4">&nbsp;</div>
				<div class="span4">
					<button type="submit" form="formCard" style="background-color: darkgray;"><b>Payment</b></button>
				</div>
				<div class="span2">&nbsp;</div>
			</div>
		</div>
	</div>
</body>