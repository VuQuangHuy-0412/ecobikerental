<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>Not Found Bike</title>
<body>
	<div class="row">
		<div class="span12" style="margin-left: 40px;">
			<div class="well well-small row" style="background-color: coral;">
				<h3>Your barcode is invalid or this bike is being rented. Please choose another bike.</h3>
			</div>
			<div class="container-fluid row">
				<div class="span2">&nbsp;</div>
				<div class="span6">
					<form method="GET" action="/EcoBikeRental/rent-bike-with-barcode" class="" id="formBarcode">
						<label for="barcode" style="text-align: center;"><b>Insert Barcode: </b></label><br>
						<input type="text" placeholder="Insert Barcode" class="span6" id="barcode" name="barcode" >
					</form>
				</div>
            </div>
			<div class="row">
				<div class="span4">&nbsp;</div>
				<div class="span4">
					<button type="submit" form="formBarcode" style="background-color: darkgray;"><b>Rent This Bike</b></button>
				</div>
				<div class="span2">&nbsp;</div>
			</div>
		</div>
	</div>
</body>