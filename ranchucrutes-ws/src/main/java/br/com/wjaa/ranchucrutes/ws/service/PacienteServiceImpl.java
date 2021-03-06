package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.utils.StringUtils;
import br.com.wjaa.ranchucrutes.framework.service.GenericServiceImpl;
import br.com.wjaa.ranchucrutes.ws.dao.PacienteDao;
import br.com.wjaa.ranchucrutes.ws.exception.PacienteServiceException;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by wagner on 11/09/15.
 */
@Service
@Transactional(readOnly = true, timeout = 10000)
public class PacienteServiceImpl extends GenericServiceImpl<PacienteEntity,Long> implements PacienteService {

    private static Log LOG = LogFactory.getLog(PacienteServiceImpl.class);

    private PacienteDao pacienteDao;

    @Autowired
    public PacienteServiceImpl(PacienteDao pacienteDao) {
        super(pacienteDao);
        this.pacienteDao = pacienteDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PacienteEntity savePaciente(PacienteEntity pacienteEntity) throws PacienteServiceException {
        LOG.debug("m=savePaciente, pacienteEntity=" + pacienteEntity);
        if (pacienteEntity.getRedeSocial() != null){

            PacienteEntity pacienteFound = pacienteDao.getPacienteByKeySocial(pacienteEntity.getRedeSocial(), pacienteEntity.getKeySocial());
            if (pacienteFound != null){
                throw new PacienteServiceException("Paciente já cadastrado!");
            }

            pacienteEntity.setSenha("unknow");
        }else{
            PacienteEntity pacienteFound = pacienteDao.getPacienteByEmail(pacienteEntity.getEmail());
            if (pacienteFound != null){
                throw new PacienteServiceException("Paciente já cadastrado!");
            }
            pacienteEntity.setSenha(StringUtils.createMD5(pacienteEntity.getSenha()));
        }
        pacienteEntity.setAtivo(true);
        pacienteEntity.setDataCriacao(new Date());
        pacienteEntity.setDataUltimoAcesso(new Date());
        return pacienteDao.save(pacienteEntity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PacienteEntity updatePaciente(PacienteEntity pacienteEntity) throws PacienteServiceException {
        LOG.debug("m=updatePaciente, pacienteEntity=" + pacienteEntity);
        PacienteEntity pacientePersisted = pacienteDao.get(pacienteEntity.getIdLogin());

        if (pacientePersisted != null){

            BeanUtils.copyProperties(pacienteEntity,pacientePersisted,"idLogin",
                    "ativo","dataCriacao","dataUltimoAcesso","senha","redeSocial","dataConfirmacao"
                    ,"codeConfirmacao","keySocial");
            return pacienteDao.save(pacientePersisted);
        }else{
            throw new PacienteServiceException("Paciente não encontrado na base de dados");
        }


    }
}
