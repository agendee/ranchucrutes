package br.com.wjaa.ranchucrutes.web.controller;

import br.com.wjaa.ranchucrutes.commons.vo.ConvenioVo;
import br.com.wjaa.ranchucrutes.web.vo.EspecialidadeVo;

import java.util.List;

/**
 * Created by wagner on 05/07/15.
 */
public class RanchucrutesCache {

    private static RanchucrutesCache ranchucrutesCache;
    private static List<EspecialidadeVo> especialidadeVos;
    private static List<ConvenioVo> convenioVos;

    private RanchucrutesCache(){}



    private static RanchucrutesCache getInstance(){
        if (ranchucrutesCache != null ){
            ranchucrutesCache = new RanchucrutesCache();
        }
        return ranchucrutesCache;
    }
}
