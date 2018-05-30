var admin = function() {
  return {
        init: function(){
            admin.initValidate();
            admin.initComboEspecialidade();
            admin.initBtnAddClinica();
            admin.prepareInputsClinica();
            admin.adjustFieldPassword();


        },
        
        adjustFieldPassword: function(){
			$("input[name='senha']").blur(function() {
				$("input[name='confirmacao']").val("");
			});
        },
         prepareInputsClinica: function(){
            for (var index = 0; index < countClinica; index ++ ){
                admin.initComboConvenio(index);
                admin.initComboCategoria(index);
                admin.initBtnAddCategoria(index);
                admin.initInput(index);
                admin.initBntDiaSemana(index);
            }
        },
        initInput: function(index){
            $('[data-toggle="tooltip"]').tooltip();
            $('#valorConsulta' + index).maskMoney({
                prefix:'',
                allowNegative: true,
                thousands:'.',
                decimal:',',
                affixesStay: false
            });
            $("#cep" + index).blur(function(){
                admin.findCep(this, index);
            });

        },
        findCep: function(input, index){
            Utils.waitingSearch();
            RanchucrutesWS.getCep(input.value,function(endereco){
                if (endereco){
                    $('#logradouro' + index).val(endereco.logradouro);
                    $('#bairro' + index).val(endereco.bairro);
                    $('#localidade' + index).val(endereco.localidade);
                    $('#uf' + index).val(endereco.uf);
                    $("#formCadastro").formValidation('updateStatus', 'clinicas[' + index +'].endereco.logradouro', 'NOT_VALIDATED')
                        .formValidation('validateField', 'clinicas[' + index +'].endereco.logradouro');
                    $("#formCadastro").formValidation('updateStatus', 'clinicas[' + index +'].endereco.bairro', 'NOT_VALIDATED')
                        .formValidation('validateField', 'clinicas[' + index +'].endereco.bairro');
                    $("#formCadastro").formValidation('updateStatus', 'clinicas[' + index +'].endereco.localidade', 'NOT_VALIDATED')
                        .formValidation('validateField', 'clinicas[' + index +'].endereco.localidade');
                    $("#formCadastro").formValidation('updateStatus', 'clinicas[' + index +'].endereco.uf', 'NOT_VALIDATED')
                        .formValidation('validateField', 'clinicas[' + index +'].endereco.uf');
                }
                Utils.waitingClose();

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
        initComboConvenio: function(index){
            RanchucrutesWS.listAllConvenio(function(convenios){
                $('#planoSaude' + index).html("<option/>");
                $.each(convenios, function(i) {
                    $('#planoSaude' + index).append($("<option/>", {
                        value: convenios[i].id,
                        text: convenios[i].nome
                    }));
                });
                $('#planoSaude' + index).select2({placeholder:"Selecione um plano de saúde"});
                //BUG DO FOCUS.
                $("#planoSaude" + index).next(".select2").find(".select2-selection").focus(function() {
                    $("#planoSaude" + index).select2("open");
                });


            });
            //adicionando o change.
            $('#planoSaude' + index).change(admin.makeChangePlanoSaude(index));

        },
        makeChangePlanoSaude: function(index){
            return function(){
                   admin.getCategoriasByIdConvenio($("#planoSaude" + index).select2('val'), index);
            }
        },
        getCategoriasByIdConvenio : function (idConvenio, index){
            if (idConvenio == null || idConvenio == ''){
                return;
            }
            RanchucrutesWS.getCategoriasByIdConvenio(idConvenio, function(categorias){
                $('#categoria' + index).html("<option/>");
                $.each(categorias, function(i) {
                   $('#categoria' + index).append($("<option/>", {
                       value: categorias[i].id,
                       text: categorias[i].nome
                   }));

                });
                $('#categoria' + index).select2({placeholder:"Selecione a categoria"})
                .on("select2:select", function (e) {
                    categoriaSelecionada = e.params.data;
                });
                 //BUG DO FOCUS.
                $("#categoria" + index).next(".select2").find(".select2-selection").focus(function() {
                    $("#categoria" + index).select2("open");
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
                                            return $("#senha").val() == value;
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
                }).on('success.form.fv', function(e) {
                    Utils.waiting();
                });
        },
        initComboCategoria : function(index){
          $('#categoriasSelecionadas' + index).select2();
        },
        initBtnAddCategoria :function(index){

            $("#btnAddCategoria" + index).click(function(){
                if ( categoriaSelecionada == null ){
                    return;
                }

                $('#categoriasSelecionadas' + index).append($("<option/>", {
                   value: categoriaSelecionada.id,
                   text: categoriaSelecionada.text
                })).attr('selected','selected');

                var options = $('#categoriasSelecionadas' + index + ' option');

                var values = $.map(options ,function(option) {
                    return option.value;
                });
                $("#categoriasSelecionadas" + index).val(values).trigger("change");
                $("#planoSaude" + index).val(null).trigger("change");
                $("#categoria" + index).val(null).trigger("change");

            });

        },
        initBtnAddClinica: function(){
            $("#btnAddClinica").click(function(){
                admin.addClinica();
            });
        },
        initBntDiaSemana: function(index){
             $('.button-checkbox' + index).each(function () {
                // Settings
                var $widget = $(this),
                    $button = $widget.find('button'),
                    $checkbox = $widget.find('input:checkbox'),
                    color = $button.data('color'),
                    settings = {
                        on: {
                            icon: 'glyphicon glyphicon-check'
                        },
                        off: {
                            icon: 'glyphicon glyphicon-unchecked'
                        }
                    };

                // Event Handlers
                $button.on('click', function () {
                    $checkbox.prop('checked', !$checkbox.is(':checked'));
                    $checkbox.triggerHandler('change');
                    updateDisplay();
                });
                $checkbox.on('change', function () {
                    updateDisplay();
                });

                // Actions
                function updateDisplay() {
                    var isChecked = $checkbox.is(':checked');

                    // Set the button's state
                    $button.data('state', (isChecked) ? "on" : "off");

                    // Set the button's icon
                    $button.find('.state-icon')
                        .removeClass()
                        .addClass('state-icon ' + settings[$button.data('state')].icon);

                    // Update the button's color
                    if (isChecked) {
                        $button
                            .removeClass('btn-default')
                            .addClass('btn-' + color + ' active');
                    }
                    else {
                        $button
                            .removeClass('btn-' + color + ' active')
                            .addClass('btn-default');
                    }
                }
                // Initialization
                function init() {
                    updateDisplay();
                    // Inject the icon if applicable
                    if ($button.find('.state-icon').length == 0) {
                        $button.prepend('<i class="state-icon ' + settings[$button.data('state')].icon + '"></i> ');
                    }
                }
                init();
            });
        },
        addClinica : function(){


            $('#accordionClinica .clinicaCollapse').collapse('hide');


            var clinicaBase = $("#clinica__INDEX").html();

            var clinica = $("<div id=\"clinica" + countClinica + "\" class=\"panel panel-default\">" + clinicaBase.replace(/__INDEX/g,countClinica) + "</div>");
            $("#accordionClinica").append(clinica);


            admin.initComboConvenio(countClinica);
            admin.initComboCategoria(countClinica);
            admin.initBtnAddCategoria(countClinica);
            admin.initInput(countClinica);
            admin.initBntDiaSemana(countClinica);
             $('#formCadastro')
                    .formValidation('addField', 'clinicas[' + countClinica + '].nome')
                    .formValidation('addField', 'clinicas[' + countClinica + '].ddd')
                    .formValidation('addField', 'clinicas[' + countClinica + '].telefone')
                    .formValidation('addField', 'clinicas[' + countClinica + '].tempoConsultaEmMin')
                    .formValidation('addField', 'clinicas[' + countClinica + '].horaFuncionamentoIni')
                    .formValidation('addField', 'clinicas[' + countClinica + '].horaFuncionamentoFim')
                    .formValidation('addField', 'clinicas[' + countClinica + '].aceitaParticular')
                    .formValidation('addField', 'clinicas[' + countClinica + '].valorConsulta')
                    .formValidation('addField', 'clinicas[' + countClinica + '].endereco.cep')
                    .formValidation('addField', 'clinicas[' + countClinica + '].endereco.logradouro')
                    .formValidation('addField', 'clinicas[' + countClinica + '].endereco.numero')
                    .formValidation('addField', 'clinicas[' + countClinica + '].endereco.complemento')
                    .formValidation('addField', 'clinicas[' + countClinica + '].endereco.bairro')
                    .formValidation('addField', 'clinicas[' + countClinica + '].endereco.localidade')
                    .formValidation('addField', 'clinicas[' + countClinica + '].endereco.uf')
                    .formValidation('addField', 'clinicas[' + countClinica + '].idsCategoria')
                    .formValidation('addField', 'clinicas[' + countClinica + '].agendaHorarios[0].horaIni')
                    .formValidation('addField', 'clinicas[' + countClinica + '].agendaHorarios[0].horaFim');

            countClinica++;
        },
        removeClinica: function(index){
            var nomeClinica = $("#clinica" + index + " > .panel-heading > .panel-title > a.nomeTitle").html();
            $.confirm({
                  title:"Remover clínica",
                  text:"Você realmente quer remover a clinica '" + nomeClinica + " ' ?",
                  confirm: function(button) {
                       $('#formCadastro')
                          .formValidation('removeField', 'clinicas[' + index + '].nome')
                          .formValidation('removeField', 'clinicas[' + index + '].ddd')
                          .formValidation('removeField', 'clinicas[' + index + '].telefone')
                          .formValidation('removeField', 'clinicas[' + index + '].tempoConsultaEmMin')
                          .formValidation('removeField', 'clinicas[' + index + '].horaFuncionamentoIni')
                          .formValidation('removeField', 'clinicas[' + index + '].horaFuncionamentoFim')
                          .formValidation('removeField', 'clinicas[' + index + '].aceitaParticular')
                          .formValidation('removeField', 'clinicas[' + index + '].valorConsulta')
                          .formValidation('removeField', 'clinicas[' + index + '].endereco.cep')
                          .formValidation('removeField', 'clinicas[' + index + '].endereco.logradouro')
                          .formValidation('removeField', 'clinicas[' + index + '].endereco.numero')
                          .formValidation('removeField', 'clinicas[' + index + '].endereco.complemento')
                          .formValidation('removeField', 'clinicas[' + index + '].endereco.bairro')
                          .formValidation('removeField', 'clinicas[' + index + '].endereco.localidade')
                          .formValidation('removeField', 'clinicas[' + index + '].endereco.uf')
                          .formValidation('removeField', 'clinicas[' + index + '].idsCategoria')
                          .formValidation('removeField', 'clinicas[' + index + '].agendaHorarios[0].horaIni')
                          .formValidation('removeField', 'clinicas[' + index + '].agendaHorarios[0].horaFim');

                       $("#clinica" + index).remove();
                  },
                  cancel: function(button) {

                  },
                  confirmButton: "Sim",
                  cancelButton: "Não"
            });

        }
  }
}();
var categoriaSelecionada = null;

$(document).ready(function(){
  admin.init();
});
