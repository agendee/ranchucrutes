package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.FindMedicoForm;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.exception.CepNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationDuplicateFoundException;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;
import br.com.wjaa.ranchucrutes.ws.exception.LocationNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.MedicoServiceException;
import br.com.wjaa.ranchucrutes.ws.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.ws.vo.ResultadoBuscaMedicoVo;

import java.util.List;

/**
 * Created by wagner on 12/06/15.
 */
public interface MedicoService extends GenericService<MedicoEntity, Long> {

    ResultadoBuscaMedicoVo find(FindMedicoForm findDoctorForm) throws CepNotFoundException, LocationDuplicateFoundException, LocationNotFoundException;

    void saveClinicas(Long idMedico, List<MedicoClinicaEntity> clinicas) throws MedicoServiceException;

    void removeClinica(Long idClinica) throws MedicoServiceException;

    MedicoEntity update(MedicoEntity medico) throws MedicoServiceException;

}
