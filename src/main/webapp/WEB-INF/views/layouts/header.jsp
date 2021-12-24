<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header id="header">
	<div class="row">
		<div class="span4">
			<h1>
				<a class="logo" href="/EcoBikeRental/home"> 
					<span>EcoBikeRental - Open Your Road</span> 
					<img src="<c:url value="assets/img/logo-eco-bike-rental.png" />" alt="EcoBikeRental" width="60%" height="60%">
				</a>
			</h1>
		</div>
		<div class="span8" style="text-align: center;">
			<div class="title" style="margin-top: 20px;">
				<h1 style="color: green;">Automated Bike Rental System</h1>
			</div>
		</div>
		<div class="span8" style="text-align: center;">
			<p>
				<br> <strong> Customer Service (24/7) : 0987 654 321 </strong><br>
				<br>
			</p>
		</div>
	</div>
</header>

<div class="navbar">
	<div class="navbar-inner">
		<div class="container" style="background-color: limegreen">
			<a data-target=".nav-collapse" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<div class="nav-collapse">
				<ul class="nav">
					<li class="menu"><a href="/EcoBikeRental/" style="text-shadow:  0 0 white; color: black;"><b>Home</b></a></li>
					<li class="menu"><a href="/EcoBikeRental/list-dock" style="text-shadow:  0 0 white; color: black;"><b>List Dock</b></a></li>
					<li class="menu"><a href="/EcoBikeRental/rent-bike" style="text-shadow:  0 0 white; color: black;"><b>Rent Bike</b></a></li>
					<li class="menu"><a href="/EcoBikeRental/current-bike-status" style="text-shadow:  0 0 white; color: black;"><b>Curent Bike Status</b></a></li>
					<li class="menu"><a href="/EcoBikeRental/return-bike" style="text-shadow:  0 0 white; color: black;"><b>Return Bike</b></a></li>
				</ul>
				<form action="#" class="navbar-search pull-right" style="margin-right: 10px">
					<input type="text" placeholder="Search" class="search-query span2">
				</form>
			</div>
		</div>
	</div>
</div>
