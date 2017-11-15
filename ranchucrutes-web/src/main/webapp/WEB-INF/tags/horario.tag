<%@tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="indexHorario" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="indexClinica" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="horario" required="false" rtexprvalue="true" type="br.com.wjaa.ranchucrutes.commons.form.HorarioForm"%>
<%@ attribute name="oculto" required="false" rtexprvalue="true" type="java.lang.Boolean"%>

<div id="horario${indexClinica}${indexHorario}" >
    <div class="head-days" >
         <input type="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].id" value="${horario.id}"/>
         <a class="btn btn-danger" style="float:right; padding: 4px 10px;" onclick="$('#horario${indexClinica}${indexHorario}').remove();" ><i class="fa fa-trash-o"></i></a>
         <span class="button-checkbox button-checkbox${indexClinica}${indexHorario}">
             <button type="button" class="btn btn-success active" data-color="success">Seg</button>
             <input type="checkbox" class="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].diasSemana" value="1" <c:if test="${horario.temSegunda()}"> checked </c:if> />
         </span>
         <span class="button-checkbox button-checkbox${indexClinica}${indexHorario}">
              <button type="button" class="btn" data-color="success">Ter</button>
              <input type="checkbox" class="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].diasSemana" value="2" <c:if test="${horario.temTerca()}"> checked </c:if>/>
         </span>
         <span class="button-checkbox button-checkbox${indexClinica}${indexHorario}">
              <button type="button" class="btn" data-color="success">Qua</button>
              <input type="checkbox" class="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].diasSemana" value="4" <c:if test="${horario.temQuarta()}"> checked </c:if>/>
         </span>
         <span class="button-checkbox button-checkbox${indexClinica}${indexHorario}">
              <button type="button" class="btn" data-color="success">Qui</button>
              <input type="checkbox" class="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].diasSemana" value="8" <c:if test="${horario.temQuinta()}"> checked </c:if>/>
         </span>
         <span class="button-checkbox button-checkbox${indexClinica}${indexHorario}">
              <button type="button" class="btn" data-color="success">Sex</button>
              <input type="checkbox" class="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].diasSemana" value="16" <c:if test="${horario.temSexta()}"> checked </c:if> />
         </span>
         <span class="button-checkbox button-checkbox${indexClinica}${indexHorario}">
              <button type="button" class="btn" data-color="success">Sab</button>
              <input type="checkbox" class="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].diasSemana" value="32" <c:if test="${horario.temSabado()}"> checked </c:if>/>
         </span>
         <span class="button-checkbox button-checkbox${indexClinica}${indexHorario}">
              <button type="button" class="btn" data-color="success">Dom</button>
              <input type="checkbox" class="hidden" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].diasSemana" value="64" <c:if test="${horario.temDomingo()}"> checked </c:if>/>
         </span>
     </div>
     <div class="form-group">
         <div class="col-md-6">
            <label><i class="fa fa-clock-o" aria-hidden="true"></i> Das: </label>
            <input type="time" class="form-control" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].horaIni" placeholder="Hora inicial" maxlength="5" value="${horario.horaIni}"/>
         </div>
         <div class="col-md-6">
            <label><i class="fa fa-clock-o" aria-hidden="true"></i> Até:</label>
            <input type="time" class="form-control" name="clinicas[${indexClinica}].agendaHorarios[${indexHorario}].horaFim" placeholder="Hora final" maxlength="5" value="${horario.horaFim}"/>
         </div>
     </div>
</div>
