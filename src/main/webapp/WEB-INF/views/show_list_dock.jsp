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
						<li><a href="products.html"> <span
								class="icon-chevron-right"></span> <c:out value="${ province }" /></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="span9">
			<c:forEach var="province" items="${ provinces }">
				<div class="well well-small">
					<h3><c:out value="${ province }" /></h3>
					<hr class="soften" />
					<div class="row-fluid">
						<div id="newProductCar" class="carousel slide">
							<div class="carousel-inner">
								<div class="item active">
									<ul class="thumbnails">
										<c:forEach var="dock" items="${ docks }">
											<c:if test="${ dock.province == province }">
												<li class="span6" style="height: 350px">
													<div class="thumbnail">
														<h4 style="text-align: center;"><b><c:out value="${ dock.name }" /></b></h4>
														<a class="zoomTool" href="product_details.html" title="add to cart">
															<span class="icon-search"></span>DETAIL
														</a> 
														<a href="#" class="tag"></a> 
														<a href="product_details.html">
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