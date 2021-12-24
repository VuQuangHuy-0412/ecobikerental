<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Confirm Return Bike</title>
<body>
<div class="row">
		<div class="span5">
			<div class="well well-small">
				<figure>
					<img src="${ category.image }" style="height: 270px; width: 300px;" alt="${ category.name }">
					<figcaption style="margin-top: 5px; text-align: center;">
						<c:out value="${ category.name }" /> - <c:out value="${ bike.bikeId }" />
					</figcaption>
				</figure>
			</div>
		</div>
		<div class="span7">
			<div class="well well-small row" style="background-color: cornflowerblue;">
				<h3>Return Bike Information</h3>
			</div>
			<form id="formReturn" method="GET" action="/EcoBikeRental/process-return" onsubmit="return validateForm()">
				<div class="container-fluid">
	                <div class="">
	                    <div class="ibox-content">
	                        <table class="table table-bordered">
	                        <tbody>
	                        	<tr>
		                            <td class="span3">
		                                <label><b>ID</b></label>
		                            </td>
		                            <td>
		                            	<c:out value="${ bike.bikeId }"></c:out>
		                            	<input type="hidden" id="bikeId" name="bikeId" value="${ bike.bikeId }">
	                            	</td>
		                        </tr>
		                        <tr>
		                            <td class="span3">
		                                <label><b>Dock Return</b></label>
		                            </td>
		                            <td>
		                            	<c:out value="${ dock.name } - ${ dock.address }"></c:out>
		                            	<input type="hidden" id="dockId" name="dockId" value="${ dock.dockId }">
	                            	</td>
		                        </tr>
		                        
		                        <tr>
		                            <td class="span3">
		                                <label><b>Status</b></label>
		                            </td>
		                            <td><c:out value="${ bike.status }" /> %</td>
		                        </tr>
		                        
		                        <tr>
		                            <td class="span3">
		                                <label><b>Point Return</b></label>
		                            </td>
		                            <td>
		                            	<input type="number" id="point" name="point" required>
		                            </td>
		                        </tr>
		                        
		                        <tr>
		                            <td class="span3">
		                                <label><b>Deposited Money</b></label>
		                            </td>
		                            <td>
		                            	<c:out value="${ category.price }" /> VND
		                            </td>
		                        </tr>
		                        
		                        <tr>
		                            <td class="span3">
		                                <label><b>Payment Amount</b></label>
		                            </td>
		                            <td>
		                            	<c:out value="${ paymentAmount }" /> VND
		                            </td>
		                        </tr>
		                        
		                        <tr>
		                            <td class="span3">
		                                <label><b>Refund Amount</b></label>
		                            </td>
		                            <td>
		                            	<c:out value="${ category.price - paymentAmount }" /> VND
		                            	<input type="hidden" id="refundAmount" name="refundAmount" value="${ category.price - paymentAmount }">
		                            </td>
		                        </tr>
	                        </tbody>
	                        </table>
						</div>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="span3">&nbsp;</div>
				<div class="span2">
					<button style="background-color: darkgray;" type="submit" form="formReturn"><b>Confirm Return Bike</b></button>
				</div>
				<div class="span2">&nbsp;</div>
			</div>
		</div>
	</div>
</body>
