
var idAgendamentoSelecionado = 0;
var Solicitacao = function() {
    return {

        init: function () {
			
			
			  $('.btnRejeitar').click(function() {
				  idAgendamentoSelecionado = $(this).val();
				
			});
			
			  $('.btnAprovar').click(function() {
				  idAgendamentoSelecionado = $(this).val();
			});
			
			  
        	 $(".btnRejeitar").confirm({
        	        title:"Rejeitar Solicitação",
        	        text:"Deseja realmente rejeitar essa consulta ?",
        	        confirm: function(button) {
        	        	console.log(idAgendamentoSelecionado)
        	        	Solicitacao.rejeitar(idAgendamentoSelecionado);
        	        },
        	        cancel: function(button) {

        	        },
        	        confirmButton: "Sim",
        	        cancelButton: "Não"
        	    });

        	    $(".btnAprovar").confirm({
        	            title:"Confirmar Solicitação",
        	            text:"Confirma essa consulta ?",
        	            confirm: function(button) {
            	        	console.log(idAgendamentoSelecionado)
        	            	Solicitacao.aprovar(idAgendamentoSelecionado);
        	            },
        	            cancel: function(button) {
        	            },
        	            confirmButton: "Sim",
        	            cancelButton: "Não"
        	        });
           
        },
        rejeitar: function (idAgendamento) {
            Utils.waiting();
            $.ajax({
                url: '/profissional/solicitacao/rejeitar',
                type: 'post',
                data: "idAgendamento="+idAgendamento,
                success: function(data){
                	  location.reload();
                },
                error: function(data){
                    console.log("Error: " + data);
                }
            });


        },
        aprovar: function (idAgendamento) {
            Utils.waiting();
            $.ajax({
                url: '/profissional/solicitacao/confirmar',
                type: 'post',
                data: "idAgendamento="+idAgendamento,
                success: function(data){
                	  location.reload();
                },
                error: function(data){
                    console.log("Error: " + data);
                }
            });


        }
    }
}();























