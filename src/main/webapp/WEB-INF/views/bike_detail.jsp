<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Bike Detail</title>
<body>
	<div class="row">
		<div class="span5">
			<div class="well well-small">
				<figure>
					<img src="${ bike.category.image }" style="height: 270px; width: 300px;" alt="${ bike.category.name }">
					<figcaption style="margin-top: 5px; text-align: center;">
						<c:out value="${ bike.category.name }" /> - <c:out value="${ bike.bikeId }" />
					</figcaption>
				</figure>
			</div>
		</div>
		<div class="span7">
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
                            <td><c:out value="${ bike.category.name }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Description</b></label>
                            </td>
                            <td><c:out value="${ bike.category.description }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Current Dock</b></label>
                            </td>
                            <td><c:out value="${ bike.dock.name }"></c:out></td>
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
                        </tbody>
                        </table>
                	</div>
        		</div>
        	</div>
			<div class="row">
				<div class="span3">&nbsp;</div>
				<div class="span2">
					<button style="background-color: darkgray;" onclick="window.location.href='/EcoBikeRental/confirm-rent-bike?bikeId=<c:out value="${ bike.bikeId }" />'"><b>Rent This Bike</b></button>
				</div>
				<div class="span2">&nbsp;</div>
			</div>
		</div>
	</div>
</body>