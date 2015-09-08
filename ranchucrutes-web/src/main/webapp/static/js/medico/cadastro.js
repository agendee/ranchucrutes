var cadastro = function() {
  return {
        init: function(){
            cadastro.initValidate();
            cadastro.initComboEspecialidade();


        },
        initComboEspecialidade: function(){
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

                    /*$('#idEspecialidade')
                    .chosen({no_results_text:'Oops, especialidade não encontrada!'})
                    .change( function(){
                       //nada
                    } );*/
                    $('#idEspecialidade').select2({placeholder:"Selecione um plano de saúde"});

                    $("#idEspecialidade").next(".select2").find(".select2-selection").focus(function() {
                        $("#idEspecialidade").select2("open");
                    });

                });
        },
        initValidate: function(){
                 $('#formCadastro').formValidation({
                        excluded: ':disabled',
                        message: 'Valor inválido',
                        locale: 'pt_BR',
                        icon: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            celular:{
                                validators: {
                                        regexp: {
                                            regexp: /^([0-9]+)$/,
                                            message: 'Número de celular inválido'
                                        }
                                }
                            },
                            ddd:{
                                validators: {
                                        regexp: {
                                            regexp: /^([0-9]+)$/,
                                            message: 'Número de DDD inválido'
                                        }
                                }
                            },
                            crm:{
                                validators: {
                                        regexp: {
                                            regexp: /^([0-9]+)$/,
                                            message: 'Número de CRM inválido'
                                        }
                                }
                            },
                            cpf: {
                                validators: {
                                    callback: {
                                        message: 'Cpf Inválido',
                                        callback: function(value, validator) {
                                            return Utils.validarCPF(value);
                                        }
                                    }
                                }
                            },
                            idEspecialidade: {
                                validators: {
                                    callback: {
                                        message: 'Selecione uma especialidade',
                                        callback: function(value, validator) {
                                            return value != '';
                                        }
                                    }
                                }
                            },
                            confirmacao: {
                                validators: {
                                    callback: {
                                        message: 'Confirmação da senha inválida.',
                                        callback: function(value, validator) {
                                            return $("input[name='senha']").val() == value;
                                        }
                                    }
                                }
                            }

                        }
                }).on('err.field.fv', function(e, data) {
                    if (data.fv.getSubmitButton()) {
                        data.fv.disableSubmitButtons(false);
                    }
                }).on('success.field.fv', function(e, data) {
                    if (data.fv.getSubmitButton()) {
                        data.fv.disableSubmitButtons(false);
                    }
                }).on('success.form.fv', function(e) {
                    Utils.waiting();
                });
        }
  }
}();


$(document).ready(function(){
  cadastro.init();
});


