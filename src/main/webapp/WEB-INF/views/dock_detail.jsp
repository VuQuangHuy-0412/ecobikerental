<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Dock Detail</title>
<body>
	<div class="row">
		<div class="span5">
			<div class="well well-small">
				<figure>
					<img src="${ dock.image }" style="height: 270px; width: 300px;" alt="${ dock.name }">
					<figcaption style="margin-top: 5px; text-align: center;">${ dock.name }</figcaption>
				</figure>
			</div>
		</div>
		<div class="span7">
			<div class="well well-small row" style="background-color: cornflowerblue;">
				<h3>Dock Information</h3>
			</div>
			<div class="container-fluid">
                <div class="">
                    <div class="ibox-content">
                        <table class="table table-bordered">
                        <tbody>

                        <tr>
                            <td class="span3">
                                <label><b>Name</b></label>
                            </td>
                            <td><c:out value="${ dock.name }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Address</b></label>
                            </td>
                            <td><c:out value="${ dock.address }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Area</b></label>
                            </td>
                            <td><c:out value="${ dock.province }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Number of available bikes</b></label>
                            </td>
                            <td>
                            	<table class="table table-bordered">
                        		<tbody>
                        			<c:forEach var="numberOfCategory" items="${ numberOfBikeCategory }">
                        				<tr>
	                        				<td class="span1"><c:out value="${ numberOfCategory.numbers }"></c:out></td>
	                        				<td><c:out value="${ numberOfCategory.bikeCategory.name }"></c:out></td>
	                        			</tr>        				
                        			</c:forEach>
                        		</tbody>
                        		</table>
							</td>
                        </tr>
                        
                        <tr>
                            <td class="span3">
                                <label><b>Number of points</b></label>
                            </td>
                            <td><c:out value="${ dock.pointNumber }"></c:out></td>
                        </tr>
                        
                        <tr>
                            <td class="col-sm-2">
                                <label><b>Number of empty points</b></label>
                            </td>
                            <td><c:out value="${ dock.pointNumber - listBikes.size() }"></c:out></td>
                        </tr>
                        
                        </tbody>
                        </table>
                	</div>
        		</div>
        	</div>
		</div>
	</div>
	<div class=row>
		<div class="span12">
			<div class="well well-small row" style="background-color: cornflowerblue;">
				<h3 class="span3">Available Bikes</h3>
			</div>
			<div class="container-fluid">
                <div class="">
                    <div class="ibox-content">
                        <table class="table table-bordered">
                        <tbody>
                        	<tr>
							  <th class="span2" style="text-align: center;">ID</th>
							  <th class="span3" style="text-align: center;">Type</th>
							  <th class="span2" style="text-align: center;">Point</th>
							  <th class="span3" style="text-align: center;">Status</th>
							  <th class="span2" style="text-align: center;">Detail</th>
							</tr>
							<c:choose>
								<c:when test="${ listBikes.size() == 0 }">
									<tr><td colspan="5" style="text-align: center;">No bike in this dock.</td></tr>
								</c:when>
								<c:when test="${ listBikes.size() > 0 }">
									<c:forEach var="bike" items="${ listBikes }">
										<tr>
										  	<td style="text-align: center;"><c:out value="${ bike.bikeId }"></c:out></td>
										  	<td style="text-align: left;">
										  		<c:forEach var="category" items="${ numberOfBikeCategory }">
										  			<c:if test="${ category.bikeCategory.categoryId == bike.categoryId }">
										  				<c:out value="${ category.bikeCategory.name }"></c:out>
										  			</c:if>
										  		</c:forEach>
									  		</td>
										  	<td style="text-align: center;"><c:out value="${ bike.point }"></c:out></td>
										  	<td style="text-align: right;"><c:out value="${ bike.status } %"></c:out></td>
											<td style="text-align: center;"><a class="icon-eye-open" href="/EcoBikeRental/bike-detail?bikeId=<c:out value="${ bike.bikeId }" />"></a></td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
                        </tbody>
                        </table>
                    </div>
            	</div>
        	</div>
		</div>
	</div>
</body>