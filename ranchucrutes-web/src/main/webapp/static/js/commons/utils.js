var Utils = function() {
  return {
	  	findCep: function(cep, callback){
	  		$.ajax({
				url: 'ajax/getCEP',
				type: 'post',
				data: "cep="+cep,
				success: function(data){
					callback(data);
				},
				error: function(){
					throw "Erro ao buscar o cep";
				}
			});
	    },
	    findRazaoSocial: function(cnpj,callback){
	  		$.ajax({
				url: 'ajax/findRazaoSocialByCnpj',
				type: 'post',
				data: "cnpj="+cnpj,
				success: function(cliente){
					if (!cliente.error){
						callback(cliente.razaoSocial);
					}else{
						throw cliente.error;
					}
				},
				error: function(){
					throw "Erro ao buscar a razao social";
				}
			});
	    },
	    existFilialCep: function(cep,callback){
	  		$.ajax({
				url: 'ajax/cepTemFilial',
				type: 'post',
				data: "cep="+cep,
				success: function(r){
					callback(r.result);
				},
				error: function(){
					throw "Erro ao buscar filial para o cep";
				}
			});
	    },
	    validarCNPJ: function(cnpj) {

	        cnpj = cnpj.replace(/[^\d]+/g,'');

	        if(cnpj == ''){
	        	return false;
	        }

	        if (cnpj.length != 14){
	        	return false;
	        }

	        // Elimina CNPJs invalidos conhecidos
	        if (cnpj == "00000000000000" ||
	            cnpj == "11111111111111" ||
	            cnpj == "22222222222222" ||
	            cnpj == "33333333333333" ||
	            cnpj == "44444444444444" ||
	            cnpj == "55555555555555" ||
	            cnpj == "66666666666666" ||
	            cnpj == "77777777777777" ||
	            cnpj == "88888888888888" ||
	            cnpj == "99999999999999"){
	        	return false;
	        }


	        // Valida DVs
	        tamanho = cnpj.length - 2
	        numeros = cnpj.substring(0,tamanho);
	        digitos = cnpj.substring(tamanho);
	        soma = 0;
	        pos = tamanho - 7;
	        for (i = tamanho; i >= 1; i--) {
	          soma += numeros.charAt(tamanho - i) * pos--;
	          if (pos < 2)
	                pos = 9;
	        }
	        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	        if (resultado != digitos.charAt(0)){
	        	return false;
	        }


	        tamanho = tamanho + 1;
	        numeros = cnpj.substring(0,tamanho);
	        soma = 0;
	        pos = tamanho - 7;
	        for (i = tamanho; i >= 1; i--) {
	          soma += numeros.charAt(tamanho - i) * pos--;
	          if (pos < 2)
	                pos = 9;
	        }
	        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	        if (resultado != digitos.charAt(1)){
	        	return false;
	        }

	        return true;
	    },
	    validarCPF: function (cpf) {
	    	var numeros, digitos, soma, i, resultado, digitos_iguais;
	        digitos_iguais = 1;
	        if (cpf.length < 11)
	              return false;
	        for (i = 0; i < cpf.length - 1; i++)
	              if (cpf.charAt(i) != cpf.charAt(i + 1))
	                    {
	                    digitos_iguais = 0;
	                    break;
	                    }
	        if (!digitos_iguais)
	              {
	              numeros = cpf.substring(0,9);
	              digitos = cpf.substring(9);
	              soma = 0;
	              for (i = 10; i > 1; i--)
	                    soma += numeros.charAt(10 - i) * i;
	              resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	              if (resultado != digitos.charAt(0))
	                    return false;
	              numeros = cpf.substring(0,10);
	              soma = 0;
	              for (i = 11; i > 1; i--)
	                    soma += numeros.charAt(11 - i) * i;
	              resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	              if (resultado != digitos.charAt(1))
	                    return false;
	              return true;
	              }
	        else
	            return false;
	   },
	   findCidades: function (uf, callback){
		   $.ajax({
				url: 'ajax/getCidades',
				type: 'post',
				data: "uf="+uf,
				success: function(data){
					callback(data);
				},
				error: function(){
					throw "Erro ao buscar as cidades";
				}
			});
	   },
	   waitingSearch: function(){
		   waitingDialog.show('Pesquisando aguarde...', {dialogSize: 'sm', progressType: 'warning'});
	   },
	   waitingClose: function(){
		   waitingDialog.hide();
	   },
	   confirm: function(message, successCallback){
	   		bootbox.dialog({
              message: message,
              title: 'Confirmação',
              buttons: {
                success: {
                  label: "Confirmar",
                  className: "btn-success",
                  callback: successCallback
                },
                danger: {
                  label: "Cancelar",
                  className: "btn-danger",
                  callback: function() {

                  }
                },
              }
            });
	   },
	   numberOnly:function(campo){
		   $(campo).val($(campo).val().replace(/[^0-9]/g, ''));
	   }

  }
}();
