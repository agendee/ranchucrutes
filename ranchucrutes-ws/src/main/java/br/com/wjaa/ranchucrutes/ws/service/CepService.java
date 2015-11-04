package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.exception.CepNotFoundException;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;

/**
 * Created by wagner on 16/06/15.
 */
public interface CepService {

    EnderecoEntity find(String cep) throws CepNotFoundException;

    LocationVo getLocationByCep(String cep);

    void saveLocation(LocationVo location, String cep);
}
