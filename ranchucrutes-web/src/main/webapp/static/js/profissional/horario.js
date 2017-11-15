var horario = function() {
  return {
        init: function(){

            for (var index = 0; index <= countClinica; index ++ ){
                horario.initBntAddHorario(index);
                for (var indexH = 0; indexH <= countHorario; indexH ++ ){
                    horario.initBntDiaSemana(index + "" + indexH);
                }
            }
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
        initBntAddHorario:function(index){
            $("#btnAddHorario" + index).click(function(){
                 $("html, body").animate({ scrollTop: $(document).height() - 100 }, 1000);
                var htmlDivHorarioBase = $("#templateDivHorario").html();
                var divHorario = $(htmlDivHorarioBase.replace(/__INDEX/g,index).replace(/__2INDEX/g,countHorario));
                $("#collapseHorario" + index).append(divHorario);
                horario.initBntDiaSemana(index + "" + countHorario);
                countHorario++;
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
  }
}();

$(document).ready(function(){
  horario.init();
});
