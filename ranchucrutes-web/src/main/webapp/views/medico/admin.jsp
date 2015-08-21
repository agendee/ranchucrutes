<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
</head>
<body>
    <jsp:include page="/views/commons/menu_admin.jsp" />
    <div class="col-xs-12 container">

        <form id="formCadastro" class="form-horizontal" action="/medico/savefull" method="POST">
        <div class="col-md-offset-3 col-md-6">
            <section>


                <c:if test="${errorMessage != null}">
                    <div class="alert alert-danger" role="alert">${errorMessage.errorMessage}</div>
                </c:if>


                <h4>Meu cadastro</h4>
                <div class="panel-group" id="accordionDadosPessoais">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionDadosPessoais" href="#collapseDadosPessoais">Dados Pessoais</a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse in" id="collapseDadosPessoais">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Nome:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="medico.nome" placeholder="Nome Completo" maxlength="80" value="${form.medico.nome}" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">CRM:<font style="color: rgb(169, 68, 66);">*</font></label>
                                    <div class="col-md-6">
                                        <input type="number" class="form-control" name="medico.crm" placeholder="CRM" maxlength="10" value="${form.medico.crm}" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">CPF:<font style="color: rgb(169, 68, 66);">*</font></label>
                                    <div class="col-md-6">
                                        <input type="number" class="form-control" name="medico.cpf" placeholder="CPF" maxlength="11" value="${form.medico.cpf}" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">Especialidade:<font style="color: rgb(169, 68, 66);">*</font></label>
                                    <div class="col-md-8">
                                        <select id="idEspecialidade" name="medico.idEspecialidade" data-placeholder="Selecione suas especialidades" multiple class="form-control chosen-select" required></select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">Celular:</label>
                                    <div class="col-md-3">
                                        <input type="number" class="form-control" name="medico.ddd" placeholder="DDD" maxlength="2" value="${form.medico.ddd}" />
                                    </div>
                                    <div class="col-md-5">
                                        <input type="number" class="form-control" name="medico.celular" placeholder="Celular" maxlength="9" value="${form.medico.celular}" />
                                    </div>
                                </div>

                               <div class="form-group">
                                   <label class="col-md-3 control-label">Email:<font style="color: rgb(169, 68, 66);">*</font></label>
                                   <div class="col-md-9">
                                       <input type="text" class="form-control" name="medico.email" placeholder="Email" maxlength="100" value="${form.medico.email}" required/>
                                   </div>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>


                <h4>Minhas Clinicas <button class="btn btn-success" ><i class="fa fa-plus"></i></button></h4>

                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Clinica Jóse maria da silva </a>
                                <button class="btn btn-danger" style="float:right; padding: 4px 10px; " ><i class="fa fa-minus"></i></button>
                            </h4>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Nome:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="clinica[0].nome" placeholder="Nome da clinica[0]" maxlength="80" value="${form.clinica[0].nome}" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Telefone:<font style="color: rgb(169, 68, 66);">*</font></label>
                                    <div class="col-md-3">
                                        <input type="number" class="form-control" name="clinica[0].ddd" placeholder="DDD" maxlength="2" value="${form.clinica[0].ddd}" />
                                    </div>
                                    <div class="col-md-5">
                                        <input type="number" class="form-control" name="clinica[0].telefone" placeholder="Telefone da clinica[0]" maxlength="9" value="${form.clinica[0].telefone}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Tempo Consulta:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                    <div class="col-md-5">
                                        <input type="number" class="form-control" name="clinica[0].tempoConsulta" placeholder="Duração Consulta" maxlength="80" value="${form.clinica[0].tempoConsulta}" required/>
                                    </div>
                                    <div class="col-md-3">
                                        <span>Tempo em minutos</span>
                                     </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Horário de Funcionamento:<font style="color: rgb(169, 68, 66);"></font></label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="clinica[0].horaAbertura" placeholder="Hora de abertura" maxlength="2" value="${form.clinica[0].horaAbertura}" />
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="clinica[0].horaFechamento" placeholder="Hora de fechamento" maxlength="9" value="${form.clinica[0].horaFechamento}" />
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Endereço</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">CEP:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-4">
                                                <input type="number" class="form-control" name="clinica[0].cep" placeholder="Cep" maxlength="8" value="${form.clinica[0].cep}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Logradouro:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" name="clinica[0].logradouro" placeholder="Logradouro" maxlength="80" value="${form.clinica[0].logradouro}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Número:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-4">
                                                <input type="number" class="form-control" name="clinica[0].numero" placeholder="Número" maxlength="10" value="${form.clinica[0].numero}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Complemento:</font> </label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" name="clinica[0].complemento" placeholder="Complemento" maxlength="40" value="${form.clinica[0].complemento}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Bairro:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control" name="clinica[0].bairro" placeholder="Bairro" maxlength="60" value="${form.clinica[0].bairro}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Cidade:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control" name="clinica[0].cidade" placeholder="Cidade" maxlength="60" value="${form.clinica[0].cidade}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">UF:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control" name="clinica[0].uf" placeholder="UF" maxlength="2" value="${form.clinica[0].uf}" required/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Planos de Saúde</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Convênios:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-8">
                                                <select id="idPlano" name="clinica[0].idPlano" data-placeholder="Selecione os convênios atendidos" multiple class="form-control chosen-select" required></select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Horários Agenda</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-8 control-label">Quais horários você quer abrir?</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Das:</font> </label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control" name="clinica[0].horarios[0].horaIni" placeholder="Hora inicial" maxlength="60" value="${form.clinica[0].horarios[0].horaIni}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Até:</label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control" name="clinica[0].horarios[0].horaFim" placeholder="Hora final" maxlength="2" value="${form.clinica[0].horarios[0].horaFim}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Collapsible Group Item #2</a>
                            </h4>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse">
                            <div class="panel-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                            </div>
                        </div>
                    </div>

                </div>



            </section>
        </div>
        </form>
        <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src="/static/js/ws/ranchucrutes-ws-client.js"></script>
    <script src="/static/js/commons/utils.js"></script>
    <script src="/static/js/medico/admin.js"></script>
</body>
</html>