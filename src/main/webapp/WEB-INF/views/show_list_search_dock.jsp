<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Dock</title>
<body>
	<div class="row">
		<div id="sidebar" class="span3">
			<div class="well well-small">
				<ul class="nav nav-list">
					<c:forEach var="province" items="${ provinces }">
						<li><a href="#<c:out value="${ province }" />"> <span
								class="icon-chevron-right"></span> <c:out value="${ province }" /></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="span9">
			<div class="well well-small row" style="background-color: cornflowerblue;">
				<h3 class="span3">Filter</h3>
				<div class="span3">
					<form method="GET" action="/EcoBikeRental/search-dock" class="navbar-search pull-right row" style="margin-right: 10px" id="formSearch">
						<input type="text" placeholder="Search" class="search-query span3" id="keyword" name="keyword" >
					</form>
				</div>
				<div class="" class="pull-right">
					<button class="pull-right" type="submit" form="formSearch" value="Submit" style="margin-top: 7px;">Search</button>
				</div>
			</div>
			<c:forEach var="province" items="${ provinces }">
				<div class="well well-small" id="<c:out value="${ province }" />">
					<h3><c:out value="${ province }" /></h3>
					<hr class="soften" />
					<div class="row-fluid">
						<div id="newProductCar" class="carousel slide">
							<div class="carousel-inner">
								<div class="item active">
									<ul class="thumbnails">
										<c:forEach var="dock" items="${ docksByKeyword }">
											<c:if test="${ dock.province == province }">
												<li class="span6" style="height: 350px">
													<div class="thumbnail">
														<h4 style="text-align: center;"><b><c:out value="${ dock.name }" /></b></h4>
														<a class="zoomTool" href="/EcoBikeRental/dock-detail?dockId=<c:out value="${ dock.dockId }" />" title="add to cart">
															<span class="icon-search"></span> DETAIL
														</a> 
														<a href="#" class="tag"></a> 
														<a href="/EcoBikeRental/dock-detail?dockId=<c:out value="${ dock.dockId }" />">
															<img src="<c:out value="${ dock.image }" />" style="height: 270px" alt="<c:out value="${ dock.name }" />">
														</a>
													</div>
												</li>	
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>