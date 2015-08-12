var cadastro = function() {
  return {
        init: function(){

            RanchucrutesWS.listAllEspecialidade(function(especialidades){
            $('#idEspecialidade').html("<option/>");

                $.each(especialidades, function(index) {
                    $('#idEspecialidade').append($("<option/>", {
                        value: especialidades[index].id,
                        text: especialidades[index].nome
                    }));
                });
                for (var i = 0; i < especSelected.length; i++){
                    $("#idEspecialidade option[value=" + especSelected[i] + "]").attr('selected','selected');
                }
                $('#idEspecialidade').chosen({no_results_text:'Oops, especialidade não encontrada!'});

            });

        },
  }
}();


$(document).ready(function(){
  cadastro.init();
});


