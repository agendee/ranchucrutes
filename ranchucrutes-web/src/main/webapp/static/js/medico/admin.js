var admin = function() {
  return {
        init: function(){
            admin.initValidate();
            admin.initComboEspecialidade();
            admin.initComboConvenio();
            admin.initComboCategoria();
            admin.initInput();
            admin.initBtnAddCategoria();
            admin.initBtnAddClinica();
        },
        initInput: function(){
            $('[data-toggle="tooltip"]').tooltip();
            $('#valorConsulta').maskMoney({
                prefix:'',
                allowNegative: true,
                thousands:'.',
                decimal:',',
                affixesStay: false
            });
            $("#cep").blur(function(){
                admin.findCep(this.value);
            });

        },
        findCep: function(cep){
            RanchucrutesWS.getCep(cep,function(endereco){
                $('#logradouro').val(endereco.logradouro);
                $('#bairro').val(endereco.bairro);
                $('#localidade').val(endereco.localidade);
                $('#uf').val(endereco.uf);
                $("#formCadastro").formValidation('updateStatus', 'logradouro', 'NOT_VALIDATED')
                    .formValidation('validateField', 'logradouro');
                $("#formCadastro").formValidation('updateStatus', 'bairro', 'NOT_VALIDATED')
                    .formValidation('validateField', 'bairro');
                $("#formCadastro").formValidation('updateStatus', 'localidade', 'NOT_VALIDATED')
                    .formValidation('validateField', 'localidade');
                $("#formCadastro").formValidation('updateStatus', 'uf', 'NOT_VALIDATED')
                    .formValidation('validateField', 'uf');


            });
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
                    $('#idEspecialidade').select2({placeholder:"Selecione uma especialidade"});

                    //BUG DO FOCUS
                    $("#idEspecialidade").next(".select2").find(".select2-selection").focus(function() {
                        $("#idEspecialidade").select2("open");
                    });

             });
        },
        initComboConvenio: function(){
            RanchucrutesWS.listAllConvenio(function(convenios){
                $('#planoSaude').html("<option/>");
                $.each(convenios, function(index) {
                    $('#planoSaude').append($("<option/>", {
                        value: convenios[index].id,
                        text: convenios[index].nome
                    }));
                });
                $('#planoSaude').select2({placeholder:"Selecione um plano de saúde"});
                //BUG DO FOCUS.
                $("#planoSaude").next(".select2").find(".select2-selection").focus(function() {
                    $("#planoSaude").select2("open");
                });


            });


            $('#planoSaude').change(function(){
                  admin.getCategoriasByIdConvenio($("#planoSaude").select2('val'));
            });
        },
        getCategoriasByIdConvenio : function (idConvenio){
            if (idConvenio == null || idConvenio == ''){
                return;
            }
            RanchucrutesWS.getCategoriasByIdConvenio(idConvenio, function(categorias){
                $('#categoria').html("<option/>");
                $.each(categorias, function(index) {
                   $('#categoria').append($("<option/>", {
                       value: categorias[index].id,
                       text: categorias[index].nome
                   }));

                });
                $('#categoria').select2({placeholder:"Selecione a categoria"})
                .on("select2:select", function (e) {
                    categoriaSelecionada = e.params.data;
                });
                 //BUG DO FOCUS.
                $("#categoria").next(".select2").find(".select2-selection").focus(function() {
                    $("#categoria").select2("open");
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
                })
                .on('success.field.fv', function(e, data) {
                    if (data.fv.getSubmitButton()) {
                        data.fv.disableSubmitButtons(false);
                    }
                });
        },
        initComboCategoria : function(){

          $('#categoriasSelecionadas').select2();

        },
        initBtnAddCategoria :function(){
            $("#btnAddCategoria").click(function(){
                if ( categoriaSelecionada == null ){
                    alert("está nulo seu burro");
                    return;
                }

                 $('#categoriasSelecionadas').append($("<option/>", {
                    value: categoriaSelecionada.id,
                    text: categoriaSelecionada.text
                 })).attr('selected','selected');

                var options = $('#categoriasSelecionadas option');

                var values = $.map(options ,function(option) {
                    return option.value;
                });
                $("#categoriasSelecionadas").val(values).trigger("change");
                $("#planoSaude").val(null).trigger("change");
                $("#categoria").val(null).trigger("change");

            });
        },
        initBtnAddClinica: function(){
            $("#btnAddClinica").click(function(){
                admin.addClinica();
            });
        },
        addClinica : function(){
            var clinicaBase = $("#clinica__INDEX").html();
            var clinica = $("<div id=\"clinica" + countClinica + "\" class=\"panel panel-default\">" + clinicaBase.replace(/__INDEX/g,countClinica) + "</div>")
            $("#accordionClinica").append(clinica);
            countClinica++;
        },
        removeClinica: function(obj){
            $("#" + obj).hide();

        }
  }
}();
var categoriaSelecionada = null;

$(document).ready(function(){
  admin.init();
});


