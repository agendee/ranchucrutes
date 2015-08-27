<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="pt">
<head>
    <jsp:include page="/views/commons/header.jsp" />
    <jsp:include page="/views/commons/header-components.jsp" />
    <link href="/static/css/medico/admin.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/views/commons/menu_admin.jsp" />
    <div class="col-xs-12 container">

        <form:form id="formCadastro" class="form-horizontal" action="/medico/update" method="POST" role="form" modelAtribute="form">
        <div class="col-md-offset-3 col-md-6">
            <section>

                <!-- transformar isso aqui em uma tag -->
                <c:if test="${errorMessage != null}">
                    <div class="alert alert-danger" role="alert">${errorMessage.errorMessage}</div>
                </c:if>

                <c:if test="${successMessage != null}">
                     <div class="alert alert-success" role="alert">${successMessage}</div>
                </c:if>


                <h4>Meu cadastro</h4>
                <div class="panel-group" id="accordionDadosPessoais">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionDadosPessoais" href="#collapseDadosPessoais">Dados Pessoais</a>
                                <input type="hidden" name="medico.idLogin" value="${form.medico.idLogin}"/>
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


                <!-- CLINICAS -->

                <h4>Minhas Clinicas <a class="btn btn-success" ><i class="fa fa-plus"></i></a></h4>

                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" id="">Clínica #1 </a>
                                <a class="btn btn-danger" style="float:right; padding: 4px 10px; " ><i class="fa fa-minus"></i></a>
                                <input type="hidden" name="clinicas[0].id" value="${form.clinicas[0].id}"/>
                                <input type="hidden" name="clinicas[0].idClinica" value="${form.clinicas[0].idClinica}"/>
                            </h4>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Nome:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="clinicas[0].nome" placeholder="Nome da clínica" maxlength="200" value="${form.clinicas[0].nome}" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Telefone:<font style="color: rgb(169, 68, 66);">*</font></label>
                                    <div class="col-md-3">
                                        <input type="number" class="form-control" name="clinicas[0].ddd" placeholder="DDD" maxlength="2" value="${form.clinicas[0].ddd}" />
                                    </div>
                                    <div class="col-md-5">
                                        <input type="number" class="form-control" name="clinicas[0].telefone" placeholder="Telefone da clínica" maxlength="9" value="${form.clinicas[0].telefone}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Tempo Consulta:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                    <div class="col-md-5 col-xs-9">
                                        <div class="input-group">
                                            <input type="number" class="form-control" name="clinicas[0].tempoConsultaEmMin" placeholder="" maxlength="80" value="${form.clinicas[0].tempoConsultaEmMin}" required/>
                                            <span class="input-group-addon">min&nbsp;&nbsp;&nbsp;   </span>
                                        </div>
                                    </div>
                                    <div class="col-xs-2">
                                        <label for="clinicas[0].tempoConsulta" class="control-label" style="font-size: 25px; cursor: pointer;" data-toggle="tooltip" title="Informe aqui a duração em minutos de cada consulta."><i class="fa fa-info-circle"></i></label>
                                     </div>
                               </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Horário de Funcionamento:<font style="color: rgb(169, 68, 66);"></font></label>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-addon">hh:mm</div>
                                            <input type="time" class="form-control" name="clinicas[0].horaFuncionamentoIni" placeholder="Hora de abertura" maxlength="5" value="${form.clinicas[0].horaFuncionamentoIni}" />
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <div class="input-group-addon">hh:mm</div>
                                            <input type="time" class="form-control" name="clinicas[0].horaFuncionamentoFim" placeholder="Hora de fechamento" maxlength="5" value="${form.clinicas[0].horaFuncionamentoFim}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"></font> </label>
                                    <div class="col-md-4">

                                        <input id="box1" type="checkbox" name="clinicas[0].aceitaParticular"  <c:if test="${form.clinicas[0].aceitaParticular}">checked</c:if> value="true"/>
                                        <label for="box1" style="font-size: 18px;">Aceita particular</label>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                          <div class="input-group-addon">R$</div>
                                          <input id="valorConsulta" type="text" class="form-control" name="clinicas[0].valorConsulta" placeholder="Valor da Consulta" maxlength="9" value="${form.clinicas[0].valorConsultaStr}" />
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Endereço</h3>
                                        <input type="hidden" name="clinicas[0].endereco.id" value="${form.clinicas[0].id}"/>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">CEP:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-4">
                                                <input id="cep" type="text" class="form-control" name="clinicas[0].endereco.cep" placeholder="Cep" maxlength="8" value="${form.clinicas[0].endereco.cep}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Logradouro:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-9">
                                                <input id="logradouro" type="text" class="form-control" name="clinicas[0].endereco.logradouro" placeholder="Logradouro" maxlength="80" value="${form.clinicas[0].endereco.logradouro}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Número:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-4">
                                                <input  id="numer" type="number" class="form-control" name="clinicas[0].endereco.numero" placeholder="Número" maxlength="10" value="${form.clinicas[0].endereco.numero}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Complemento:</font> </label>
                                            <div class="col-md-9">
                                                <input id="complemento" type="text" class="form-control" name="clinicas[0].endereco.complemento" placeholder="Complemento" maxlength="40" value="${form.clinicas[0].endereco.complemento}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Bairro:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-6">
                                                <input id="bairro" type="text" class="form-control" name="clinicas[0].endereco.bairro" placeholder="Bairro" maxlength="60" value="${form.clinicas[0].endereco.bairro}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Cidade:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-6">
                                                <input id="localidade" type="text" class="form-control" name="clinicas[0].endereco.localidade" placeholder="Cidade" maxlength="60" value="${form.clinicas[0].endereco.localidade}" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">UF:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-4">
                                                <input id="uf" type="text" class="form-control" name="clinicas[0].endereco.uf" placeholder="UF" maxlength="2" value="${form.clinicas[0].endereco.uf}" required/>
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
                                            <label class="col-md-3 control-label">Selecione um Convênio:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="planoSaude" data-live-search="true"></select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-md-12">Selecione a partir de qual categoria sua clinica atendente o plano:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-offset-2 col-md-8">
                                                <select class="form-control col-md-9" id="categoria" data-live-search="true" ></select>
                                                <a id="btnAddCategoria" class="btn btn-success col-md-3" ><i class="fa fa-plus"></i></a>
                                            <div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Convênios Aceitos:<font style="color: rgb(169, 68, 66);">*</font> </label>
                                            <div class="col-md-8">
                                                <select id="categoriasSelecionadas" name="clinicas[0].idsCategoria" data-placeholder="Selecione os convênios atendidos" multiple class="form-control chosen-select" required>
                                                    <c:forEach var="e" items="${form.clinicas[0].categorias}">
                                                        <option value="${e.id}" selected>${e.nome}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Horários Agenda</h3>
                                        <input type="hidden" name="clinicas[0].idAgenda" value="${form.clinicas[0].idAgenda}"/>

                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-8 control-label">Quais horários você quer abrir?</label>
                                        </div>
                                        <c:set var="countHorarios" value="0"/>

                                        <c:if test="${form.clinicas[0].agendaHorarios != null && form.clinicas[0].agendaHorarios.size() > 0}">
                                            <c:forEach var="h" items="${form.clinicas[0].agendaHorarios}">
                                                <div class="row">
                                                    <input type="hidden" name="clinicas[0].agendaHorarios[${countHorarios}].id" value="${h.id}"/>
                                                    <div class="form-group">
                                                        <label class="col-md-3 control-label">Das:</font> </label>
                                                        <div class="col-md-4">
                                                            <input type="time" class="form-control" name="clinicas[0].agendaHorarios[${countHorarios}].horaIni" placeholder="Hora inicial" maxlength="5" value="${h.horaIni}"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-md-3 control-label">Até:</label>
                                                        <div class="col-md-4">
                                                            <input type="time" class="form-control" name="clinicas[0].agendaHorarios[${countHorarios}].horaFim" placeholder="Hora final" maxlength="5" value="${h.horaFim}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <c:set var="countHorarios" value="${countHorarios + 1}"/>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${form.clinicas[0].agendaHorarios == null || form.clinicas[0].agendaHorarios.size() == 0}">
                                            <div class="row">
                                                <input type="hidden" name="clinicas[0].agendaHorarios[0].id"/>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Das:</font> </label>
                                                    <div class="col-md-4">
                                                        <input type="time" class="form-control" name="clinicas[0].agendaHorarios[0].horaIni" placeholder="Hora inicial" maxlength="5"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Até:</label>
                                                    <div class="col-md-4">
                                                        <input type="time" class="form-control" name="clinicas[0].agendaHorarios[0].horaFim" placeholder="Hora final" maxlength="5"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
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
             </div>
             </div>
                <div class="form-group">
                    <div class="col-md-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </div>
                </div>

            </section>
        </div>
        </form:form>
        <jsp:include page="/views/commons/rodape.jsp" />
    </div>
    <jsp:include page="/views/commons/footer.jsp" />
    <jsp:include page="/views/commons/footer-components.jsp" />
    <script src="/static/js/ws/ranchucrutes-ws-client.js"></script>
    <script src="/static/js/commons/utils.js"></script>
    <script src="/static/js/medico/admin.js"></script>

    <script>
        var especSelected = [];
        <c:forEach var="esp" items="${form.medico.idEspecialidade}">
            especSelected.push("${esp}");
        </c:forEach>
    </script>

</body>
</html>