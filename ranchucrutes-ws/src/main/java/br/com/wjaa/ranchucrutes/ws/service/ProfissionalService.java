package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.FindProfissionalForm;
import br.com.wjaa.ranchucrutes.commons.vo.ProfissionalBasicoVo;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaProfissionalVo;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.exception.CepNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationDuplicateFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.ProfissionalServiceException;

import java.util.List;

/**
 * Created by wagner on 12/06/15.
 */
public interface ProfissionalService extends GenericService<ProfissionalEntity, Long> {

    ResultadoBuscaProfissionalVo find(FindProfissionalForm findProfissionalForm) throws CepNotFoundException, LocationDuplicateFoundException, LocationNotFoundException;

    void saveClinicas(ProfissionalEntity profissionalPersisted, List<ProfissionalClinicaEntity> clinicas) throws ProfissionalServiceException;

    void saveAgendaHorarios(List<ProfissionalClinicaEntity> clinicas);

    void removeClinica(Long idClinica) throws ProfissionalServiceException;

    ProfissionalEntity saveProfissional(ProfissionalEntity profissional) throws ProfissionalServiceException;

    ProfissionalEntity update(ProfissionalEntity profissional) throws ProfissionalServiceException;

    ProfissionalBasicoVo getProfissionalBasico(Long idProfissional);

    boolean profissionalAceitaCategoria(Long idProfissional, Integer idCategoria);

}
