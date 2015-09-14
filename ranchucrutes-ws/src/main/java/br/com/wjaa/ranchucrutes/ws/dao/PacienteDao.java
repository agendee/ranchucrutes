package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;

/**
 * Created by wagner on 11/09/15.
 */
public interface PacienteDao extends GenericDao<PacienteEntity,Long>{
    PacienteEntity getPacienteByKeySocial(RedeSocialEnum redeSocial, String keySocial);

    PacienteEntity getPacienteByEmail(String email);
}
