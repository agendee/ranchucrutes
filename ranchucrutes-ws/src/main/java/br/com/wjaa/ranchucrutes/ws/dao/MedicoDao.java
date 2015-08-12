package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;

import java.util.List;

/**
 * Created by wagner on 17/06/15.
 */
public interface MedicoDao extends GenericDao<MedicoEntity,Long>{

    /**
     *
     * @param idEspecialidade
     * @param idConvenio
     * @param location
     * @param raioPrecisao
     * @return
     */
    List<MedicoEntity> findMedico(Integer idEspecialidade, Integer idConvenio, LocationVo location, double raioPrecisao);

    /**
     *
     * @param idEspecialidade
     * @param location
     * @param raioPrecisao
     * @return
     */
    List<MedicoEntity> findMedicoByEspecialidade(Integer idEspecialidade, LocationVo location,
                                                      double raioPrecisao);

    /**
     *
     * @param crm
     * @return
     */
    MedicoEntity getMedicoByCrm(Integer crm);

    /**
     *
     * @param email
     * @return
     */
    MedicoEntity getMedicoByEmail(String email);
}
