var home = function() {
  return {
	  	init: function(){

/*
            RanchucrutesWS.listAllEspecialidade(function(especialidades){
            $('#idEspecialidade').html("<option/>");

                $.each(especialidades, function(index) {
                    $('#idEspecialidade').append($("<option/>", {
                        value: especialidades[index].id,
                        text: especialidades[index].nome
                    }));
                });
                $('#idEspecialidade').select2({placeholder:"Selecione uma especialidade"});

            });

            RanchucrutesWS.listAllConvenio(function(convenios){
                $('#planoSaude').html("<option/>");
                $.each(convenios, function(index) {
                    $('#planoSaude').append($("<option/>", {
                        value: convenios[index].id,
                        text: convenios[index].nome
                    }));
                });
                $('#planoSaude').select2({placeholder:"Selecione um plano de saúde"});



            });*/


            $('#planoSaude').change(function(){
                  home.getCategoriasByIdConvenio($("#planoSaude").select2('val'));
             });

            $('#modalVideoInstitucional')
              .on('show.bs.modal', function (e) {
                    $("#modalVideoInstitucional .modal-body").html("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/NZiwUl8DhFQ\" frameborder=\"0\" allowfullscreen></iframe>");
            }).on('hide.bs.modal', function (e) {
                    $("#modalVideoInstitucional .modal-body").html("");
            });


        },
        getCategoriasByIdConvenio : function (idConvenio){
            RanchucrutesWS.getCategoriasByIdConvenio(idConvenio, function(categorias){
                $('#idCategoria').html("<option/>");
                $.each(categorias, function(index) {
                   $('#idCategoria').append($("<option/>", {
                       value: categorias[index].id,
                       text: categorias[index].nome
                   }));

                });
                $('#idCategoria').select2({placeholder:"Selecione a categoria"});
            });
        }
  }
}();


$(document).ready(function(){
  home.init();
});


