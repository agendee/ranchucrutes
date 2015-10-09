var RanchucrutesWS = function() {
  return {
	  	getProfissionalById: function(id, callback){
            RanchucrutesWS.ajax('http://rest.agendee.com.br/profissional/' + id,
                'get',
                function(profissional){
                    callback(profissional);
                });
	    },
	    listAllEspecialidade: function(callback){
            RanchucrutesWS.ajax('http://rest.agendee.com.br/especialidade/all',
                'get',
                function(especialidades){
                    callback(especialidades);
                });
        },
        listAllConvenio: function(callback){
            RanchucrutesWS.ajax('http://rest.agendee.com.br/convenio/all',
                'get',
                function(convenios){
                    callback(convenios);
                });
        },
        listAllConvenioCategorias: function(callback){
            RanchucrutesWS.ajax('http://rest.agendee.com.br/convenio/categoria/all',
                'get',
                function(categorias){
                    callback(categorias);
                });
        },
        getCategoriasByIdConvenio: function(idConvenio, callback){
            RanchucrutesWS.ajax('http://rest.agendee.com.br/convenio/categoria/' + idConvenio,
                'get',
                function(categoria){
                    callback(categoria);
                });
        },
        getCep: function(cep, callback){
            RanchucrutesWS.ajax('http://rest.agendee.com.br/cep/' + cep,
                'get',
                function(endereco){
                    callback(endereco);
                });
        },
        ajax:function(url,method,callback){
            $.ajax({
                url: url,
                type: method,
                dataType: 'jsonp',
                cache: false,
                success: function(data){
                    callback(data);
                },
                error: function(jqxhr, textStatus, error){
                    var err = textStatus + ", " + error;
                    console.log( "Request Failed: " + err );

                }
            });

        }
  }
}();
