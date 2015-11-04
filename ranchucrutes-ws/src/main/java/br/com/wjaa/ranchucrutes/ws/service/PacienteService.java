package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.framework.service.GenericService;
import br.com.wjaa.ranchucrutes.ws.exception.PacienteServiceException;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;

/**
 * Created by wagner on 11/09/15.
 */
public interface PacienteService extends GenericService<PacienteEntity,Long> {

    PacienteEntity savePaciente(PacienteEntity pacienteEntity) throws PacienteServiceException;

    PacienteEntity updatePaciente(PacienteEntity pacienteEntity) throws PacienteServiceException;
}
