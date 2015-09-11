package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;
import org.springframework.stereotype.Repository;

/**
 * Created by wagner on 11/09/15.
 */
@Repository
public class PacienteDaoImpl extends GenericDaoImpl<PacienteEntity,Long> implements PacienteDao {
    @Override
    public PacienteEntity getPacienteByKeySocial(RedeSocialEnum redeSocial, String keySocial) {
        return null;
    }
}
