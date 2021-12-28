<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Confirm Rent Bike</title>
<body>
	<div class="row">
		<div class="span12" style="margin-left: 40px;">
			<div class="well well-small row" style="background-color: cornflowerblue;">
				<h3>Bike Information</h3>
			</div>
			<div class="container-fluid">
                <div class="">
                    <div class="ibox-content">
                        <table class="table table-bordered">
                        <tbody>

                        <tr>
                            <td class="span3">
                                <label><b>ID</b></label>
                            </td>
                            <td><c:out value="${ bike.bikeId }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Barcode</b></label>
                            </td>
                            <td><c:out value="${ bike.barcode }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Type</b></label>
                            </td>
                            <td><c:out value="${ category.name }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Description</b></label>
                            </td>
                            <td><c:out value="${ category.description }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Current Dock</b></label>
                            </td>
                            <td><c:out value="${ dock.name }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Point</b></label>
                            </td>
                            <td><c:out value="${ bike.point }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Status</b></label>
                            </td>
                            <td><c:out value="${ bike.status }" /> %</td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Deposit Money</b></label>
                            </td>
                            <td><c:out value="${ category.price }" /> VND</td>
                        </tr>
                        </tbody>
                        </table>
                	</div>
        		</div>
        	</div>
			<div class="row">
				<div class="span3">&nbsp;</div>
				<div class="span4">
					<button style="background-color: darkgray;" onclick="window.location.href='/EcoBikeRental/add-card-deposit?bikeId=<c:out value="${ bike.bikeId }" />'"><b>Insert Your Card Information</b></button>
				</div>
				<div class="span2">&nbsp;</div>
			</div>
		</div>
	</div>
</body>