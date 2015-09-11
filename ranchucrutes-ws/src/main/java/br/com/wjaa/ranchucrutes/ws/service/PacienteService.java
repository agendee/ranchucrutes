package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.exception.PacienteServiceException;

/**
 * Created by wagner on 11/09/15.
 */
public interface PacienteService extends GenericService<PacienteEntity,Long>{

    PacienteEntity savePaciente(PacienteEntity pacienteEntity) throws PacienteServiceException;
}
