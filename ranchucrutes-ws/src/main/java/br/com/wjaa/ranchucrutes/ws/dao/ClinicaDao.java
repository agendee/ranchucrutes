package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.commons.vo.ClinicaVo;
import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.framework.dao.GenericDao;
import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;

import java.util.List;

/**
 * Created by wagner on 17/06/15.
 */
public interface ClinicaDao extends GenericDao<ClinicaEntity, Long> {

    /**
     *
     * @param idEspecialidade
     * @param idConvenio
     * @param location
     * @param raioPrecisao
     * @return
     */
    List<ClinicaVo> findClinicaByConvenio(Integer idEspecialidade, Integer idConvenio, LocationVo location, double raioPrecisao);

    /**
     *
     * @param idEspecialidade
     * @param location
     * @param raioPrecisao
     * @return
     */
    List<ClinicaVo> findClinicaParticular(Integer idEspecialidade, LocationVo location,
                                                             double raioPrecisao);


}
