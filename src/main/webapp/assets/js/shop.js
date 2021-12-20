$(document).ready(function() {
    $("body").fadeIn(400);

	$('#myCarousel').carousel()
	$('#newProductCar').carousel()

	/* Home page item price animation */
	$('.thumbnail').mouseenter(function() {
   		$(this).children('.zoomTool').fadeIn();
	});

	$('.thumbnail').mouseleave(function() {
		$(this).children('.zoomTool').fadeOut();
	});

	// Show/Hide Sticky "Go to top" button
	$(window).scroll(function(){
		if($(this).scrollTop()>200){
			$(".gotop").fadeIn(200);
		}
		else{
			$(".gotop").fadeOut(200);
		}
	});
	// Scroll Page to Top when clicked on "go to top" button
	$(".gotop").click(function(event){
		event.preventDefault();

		$.scrollTo('#gototop', 1500, {
        	easing: 'easeOutCubic'
        });
	});
	
	var listMenu = document.getElementsByClassName("menu");
	var url = location.href;
	var i = 0;
	for (i = 0; i < listMenu.length; i++) {
		var href = listMenu[i].getElementsByTagName("a")[0].href;
		if (url == href) {
			listMenu[i].classList.add("active");
		}
	}
	console.log(i);
});
