package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.form.FindClinicaForm;
import br.com.wjaa.ranchucrutes.commons.vo.ResultadoBuscaClinicaVo;
import br.com.wjaa.ranchucrutes.framework.service.GenericService;
import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.exception.CepNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.ClinicaServiceException;

import java.util.List;

/**
 * Created by wagner on 4/12/16.
 */
public interface ClinicaService extends GenericService<ClinicaEntity,Long> {

    /**
     *
     * @param form
     * @return
     */
    ResultadoBuscaClinicaVo find(FindClinicaForm form) throws ClinicaServiceException;
}
