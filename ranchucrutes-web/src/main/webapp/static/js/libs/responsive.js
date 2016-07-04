(function($, document, window, viewport){

    var responsive = function() {

       /* if( viewport.is("<sm") ) {
            $("#imgLogo").attr("src","/static/img/logo_mini.png")

        }

        if( viewport.is("md") ) {
            $("#imgLogo").attr("src","/static/img/logo.png")
        }

        if( viewport.is(">md") ) {
            $("#imgLogo").attr("src","/static/img/logo.png")
        }*/
    }

  // Executes once whole document has been loaded
  $(document).ready(function() {
	  	responsive();
        //console.log( 'Current breakpoint:', viewport.current() );

  });


  // Executes each time window size changes
  $(window).bind('resize', function() {
      viewport.changed(function(){
    	responsive();
        //console.log( 'Current breakpoint:', viewport.current() );

      });
  });

})(jQuery, document, window, ResponsiveBootstrapToolkit);
