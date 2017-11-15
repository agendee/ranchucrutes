<%@tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags"%>
<%@ attribute name="index" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="clinica" required="false" rtexprvalue="true" type="br.com.wjaa.ranchucrutes.commons.form.ClinicaForm"%>
<%@ attribute name="oculto" required="false" rtexprvalue="true" type="java.lang.Boolean"%>

<div id="clinica${index}" class="panel panel-default" <c:if test="${oculto}"> style="display:none;" </c:if> >
    <div class="panel-body">
        <h4>
            <a data-toggle="collapse" data-parent="#accordionClinica" href="#collapse${index}" aria-expanded="true" class="nomeTitle accordion-toggle">
                <c:if test="${clinica.nome != null}">
                    ${clinica.nome}
                </c:if>
                <c:if test="${clinica.nome == null}">
                    Nova clínica
                </c:if>
            </a>
            <a class="btn btn-danger" style="float:right; padding: 4px 10px;" onclick="admin.removeClinica(${index});" ><i class="fa fa-trash-o"></i></a>
            <input type="hidden" name="clinicas[${index}].id" value="${clinica.id}"/>
            <input type="hidden" name="clinicas[${index}].idClinica" value="${clinica.idClinica}"/>
            <input type="hidden" name="clinicas[${index}].idAgenda" value="${clinica.idAgenda}"/>
        </h4>
    </div>
    <div id="collapse${index}" class="panel-collapse collapse in clinicaCollapse" aria-expanded="true">

        <div class="panel-footer">
            <div class="form-group">
                <label class="col-md-12">Nome:<font style="color: rgb(169, 68, 66);">*</font> </label>
                <div class="col-md-12">
                    <input type="text" class="form-control" name="clinicas[${index}].nome" placeholder="Nome da clínica" maxlength="200" value="${clinica.nome}" required/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-12">Telefone:</label>
                <div class="col-md-3">
                    <input type="number" class="form-control" name="clinicas[${index}].ddd" placeholder="DDD" maxlength="2" value="${clinica.ddd}" />
                </div>
                <div class="col-md-9">
                    <input type="number" class="form-control" name="clinicas[${index}].telefone" placeholder="Telefone da clínica" maxlength="9" value="${clinica.telefone}" />
                </div>
            </div>
			
			<div class="row">
			
			<div class="col-md-6">
            <div class="form-group">
                <label class="col-md-12">Tempo Consulta:<font style="color: rgb(169, 68, 66);">*</font> </label>
                <div class="col-md-10 col-xs-10">
                    <div class="input-group">
                        <input type="number" class="form-control" name="clinicas[${index}].tempoConsultaEmMin" placeholder="" maxlength="80" value="${clinica.tempoConsultaEmMin}" required/>
                        <span class="input-group-addon">min&nbsp;&nbsp;&nbsp;   </span>
                    </div>
                </div>
                <div class="col-xs-2">
                    <label for="clinicas[${index}].tempoConsulta" class="control-label" style="font-size: 25px; cursor: pointer;" data-toggle="tooltip" title="Informe aqui a duração em minutos de cada consulta."><i class="fa fa-info-circle"></i></label>
                 </div>
            </div>
			</div>
			
			
			<div class="col-md-6">
            <div class="form-group">
                <label class="col-md-12">Tempo de abertura da agenda:<font style="color: rgb(169, 68, 66);">*</font> </label>
                <div class="col-md-10 col-xs-10">
                    <select class="form-control" name="clinicas[${index}].aberturaAgenda" placeholder="Tempo para abertura da agenda"  value="${clinica.aberturaAgenda}" required>
                        <option value="SEMANAL" <c:if test="${clinica.aberturaAgenda == 'SEMANAL'}"> selected </c:if> > Semanal</option>
                        <option value="DUAS_SEMANAS" <c:if test="${clinica.aberturaAgenda == 'DUAS_SEMANAS'}"> selected </c:if>> 2 Semanas</option>
                        <option value="TRES_SEMANAS" <c:if test="${clinica.aberturaAgenda == 'TRES_SEMANAS'}"> selected </c:if>> 3 Semanas</option>
                        <option value="QUATRO_SEMANAS" <c:if test="${clinica.aberturaAgenda == 'QUATRO_SEMANAS'}"> selected </c:if>> 4 Semanas</option>
                        <option value="MENSAL" <c:if test="${clinica.aberturaAgenda == 'MENSAL'}"> selected </c:if>> Mensal</option>
                        <option value="BIMESTRAL" <c:if test="${clinica.aberturaAgenda == 'BIMESTRAL'}"> selected </c:if>> 2 Meses</option>
                    </select>
                 </div>

                <div class="col-xs-2">
                    <label for="clinicas[${index}].aberturaAgenda" class="control-label" style="font-size: 25px; cursor: pointer;" data-toggle="tooltip" title="Informe aqui até quanto tempo você quer deixar sua agenda aberta."><i class="fa fa-info-circle"></i></label>
                 </div>
            </div>
			</div>
			
			 </div>

			<div class="row">
			
			<div class="col-md-6">
			
            <div class="form-group">
                <label class="col-md-12">Horário de Funcionamento:<font style="color: rgb(169, 68, 66);"></font></label>
                <div class="col-md-6">
                    <div class="input-group">
                        <div class="input-group-addon">hh:mm</div>
                        <input type="time" class="form-control" name="clinicas[${index}].horaFuncionamentoIni" placeholder="Hora de abertura" maxlength="5" value="${clinica.horaFuncionamentoIni}" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="input-group">
                        <div class="input-group-addon">hh:mm</div>
                        <input type="time" class="form-control" name="clinicas[${index}].horaFuncionamentoFim" placeholder="Hora de fechamento" maxlength="5" value="${clinica.horaFuncionamentoFim}" />
                    </div>
                </div>
            </div>
			
			</div>
			
			<div class="col-md-6">
			
            <div class="form-group">
                <label class="col-md-12">Opções de Cobrança:<font style="color: rgb(169, 68, 66);"></font></label>
                <div class="col-md-6">

                    <span class="button-checkbox button-checkbox${index}">
                          <button type="button" class="btn" data-color="success">Aceita particular</button>
                          <input type="checkbox" class="hidden" name="clinicas[${index}].aceitaParticular" value="true" <c:if test="${clinica.aceitaParticular}">checked</c:if>/>
                    </span>

                </div>
                <div class="col-md-6">
                    <div class="input-group">
                      <div class="input-group-addon">R$</div>
                      <input id="valorConsulta${index}" type="text" class="form-control" name="clinicas[${index}].valorConsulta" placeholder="Valor da Consulta" maxlength="9" value="${clinica.valorConsultaStr}" />
                    </div>
                </div>
            </div>
			
			</div>
			</div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Endereço</h3>
                    <input type="hidden" name="clinicas[${index}].endereco.id" value="${clinica.endereco.id}"/>
                </div>
                <div class="panel-footer">
					<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-12">CEP:<font style="color: rgb(169, 68, 66);">*</font> </label>
							<div class="col-md-12">
								<input id="cep${index}" type="text" class="form-control" name="clinicas[${index}].endereco.cep" placeholder="Cep" maxlength="8" value="${clinica.endereco.cep}" required/>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-12">Logradouro:<font style="color: rgb(169, 68, 66);">*</font> </label>
							<div class="col-md-12">
								<input id="logradouro${index}" type="text" class="form-control" name="clinicas[${index}].endereco.logradouro" placeholder="Logradouro" maxlength="80" value="${clinica.endereco.logradouro}" required/>
							</div>
						</div>
					</div>
					
					</div>
					
					<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="col-md-12">Número:<font style="color: rgb(169, 68, 66);">*</font> </label>
							<div class="col-md-12">
								<input  id="number" type="number" class="form-control" name="clinicas[${index}].endereco.numero" placeholder="Número" maxlength="10" value="${clinica.endereco.numero}" required/>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-12">Complemento:</font> </label>
							<div class="col-md-12">
								<input id="complemento" type="text" class="form-control" name="clinicas[${index}].endereco.complemento" placeholder="Complemento" maxlength="40" value="${clinica.endereco.complemento}"/>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-12">Bairro:<font style="color: rgb(169, 68, 66);">*</font> </label>
							<div class="col-md-12">
								<input id="bairro${index}" type="text" class="form-control" name="clinicas[${index}].endereco.bairro" placeholder="Bairro" maxlength="60" value="${clinica.endereco.bairro}" required/>
							</div>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-12">Cidade:<font style="color: rgb(169, 68, 66);">*</font> </label>
							<div class="col-md-12">
								<input id="localidade${index}" type="text" class="form-control" name="clinicas[${index}].endereco.localidade" placeholder="Cidade" maxlength="60" value="${clinica.endereco.localidade}" required/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-12">UF:<font style="color: rgb(169, 68, 66);">*</font> </label>
							<div class="col-md-12">
								<input id="uf${index}" type="text" class="form-control" name="clinicas[${index}].endereco.uf" placeholder="UF" maxlength="2" value="${clinica.endereco.uf}" required/>
							</div>
						</div>
					</div>
					</div>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Planos de Saúde</h3>
                </div>
                <div class="panel-footer">
				
				<div class="row">
				
					<div class="col-md-6">
						<div class="form-group">
							<label>Selecione um Convênio:</label>
							<select class="form-control planoSaude" id="planoSaude${index}" data-live-search="true"></select>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label>Selecione a partir de qual categoria sua clinica atendente o plano: </label>
							<div class="col-xs-9" style="padding-left:0px;">
								<select class="form-control" id="categoria${index}" data-live-search="true" ></select>
							</div>
							<a id="btnAddCategoria${index}" class="btn btn-success col-xs-1" ><i class="fa fa-plus"></i></a>

						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group" style="margin-top:30px;">
							<label>Convênios Aceitos:</label>
							<select id="categoriasSelecionadas${index}" name="clinicas[${index}].idsCategoria" data-placeholder="Selecione os convênios atendidos" multiple class="form-control chosen-select categoriasSelecionadas">
								<c:forEach var="e" items="${clinica.categorias}">
									<option value="${e.id}" selected>${e.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
                </div>
            </div>


        </div>
    </div>
</div>          