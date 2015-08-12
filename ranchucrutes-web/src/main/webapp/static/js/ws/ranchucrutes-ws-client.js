var RanchucrutesWS = function() {
  return {
	  	getMedicoById: function(id, callback){
            RanchucrutesWS.ajax('http://rest.marcmed.com.br/medico/' + id,
                'get',
                function(medico){
                    callback(medico);
                });
	    },
	    listAllEspecialidade: function(callback){
            RanchucrutesWS.ajax('http://rest.marcmed.com.br/especialidade/all',
                'get',
                function(especialidades){
                    callback(especialidades);
                });
        },
        listAllConvenio: function(callback){
            RanchucrutesWS.ajax('http://rest.marcmed.com.br/convenio/all',
                'get',
                function(convenios){
                    callback(convenios);
                });
        },
        getCategoriasByIdConvenio: function(idConvenio, callback){
            RanchucrutesWS.ajax('http://rest.marcmed.com.br/convenio/categoria/' + idConvenio,
                'get',
                function(categoria){
                    callback(categoria);
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
