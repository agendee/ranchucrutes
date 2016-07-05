var home = function() {
  return {
	  	init: function(){

            /*
            $('#modalVideoInstitucional')
              .on('show.bs.modal', function (e) {
                    $("#modalVideoInstitucional .modal-body").html("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/NZiwUl8DhFQ\" frameborder=\"0\" allowfullscreen></iframe>");
            }).on('hide.bs.modal', function (e) {
                    $("#modalVideoInstitucional .modal-body").html("");
            });
*/


        // HOME BACKGROUND SLIDESHOW
          $(function(){
            jQuery(document).ready(function() {
            $('#home').backstretch([
               "static/img/home/home1.jpg",
               "static/img/home/home2.jpg",
               "static/img/home/home3.jpg",
               "static/img/home/home4.jpg",
                ],  {duration: 2000, fade: 750});
            });
          });


          // PRELOADER JS
          $(window).load(function(){
              $('.preloader').fadeOut(1000); // set duration in brackets
          });

// HIDE MOBILE MENU AFTER CLIKING ON A LINK
  $('.navbar-collapse a').click(function(){
        $(".navbar-collapse").collapse('hide');
    });


          // jQuery to collapse the navbar on scroll //
          $(window).scroll(function() {
              if ($(".navbar").offset().top > 50) {
                  $(".navbar-fixed-top").addClass("top-nav-collapse");
              } else {
                  $(".navbar-fixed-top").removeClass("top-nav-collapse");
              }
          });






        },

  }
}();


$(document).ready(function(){
  home.init();
});


