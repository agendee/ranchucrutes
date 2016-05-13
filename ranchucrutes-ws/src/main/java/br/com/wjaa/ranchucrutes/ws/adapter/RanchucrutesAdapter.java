package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.vo.AppConfigVo;
import br.com.wjaa.ranchucrutes.commons.vo.ConvenioCategoriaVo;
import br.com.wjaa.ranchucrutes.commons.vo.ConvenioVo;
import br.com.wjaa.ranchucrutes.ws.entity.AppConfigEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ConvenioCategoriaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ConvenioEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.commons.vo.EnderecoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 21/08/15.
 */
public class RanchucrutesAdapter {
    public static List<ConvenioCategoriaVo> toConvenioCategoriaVo(List<ConvenioCategoriaEntity> categorias) {
        List<ConvenioCategoriaVo> categoriaVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categorias)){
            for (ConvenioCategoriaEntity categoria : categorias){
                categoriaVos.add(new ConvenioCategoriaVo(categoria.getId(),
                        categoria.getConvenio().getNome() + " - " + categoria.getNome()));
            }
        }
        return categoriaVos;
    }


    public static ConvenioCategoriaVo toConvenioCategoriaVo(ConvenioCategoriaEntity categoria) {
        if (categoria == null){
            return null;
        }

        ConvenioCategoriaVo categoriaVo = new ConvenioCategoriaVo(categoria.getId(),
                        categoria.getConvenio().getNome() + " - " + categoria.getNome());
        if (categoria.getConvenio() != null){
            ConvenioEntity convenio = categoria.getConvenio();
            categoriaVo.setConvenioVo(new ConvenioVo(convenio.getId(), convenio.getNome()));
            categoriaVo.setIdConvenio(convenio.getId());
        }

        return categoriaVo;
    }

    public static EnderecoVo toEnderecoVo(EnderecoEntity endereco) {
        EnderecoVo vo = new EnderecoVo();
        BeanUtils.copyProperties(endereco,vo);
        return vo;
    }

    public static EnderecoEntity fromEnderecoVo(EnderecoVo vo) {
        EnderecoEntity ee =new EnderecoEntity();
        ee.setBairro(vo.getBairro());
        ee.setLocalidade(vo.getLocalidade());
        ee.setCep(vo.getCep());
        ee.setLogradouro(vo.getLogradouro());
        ee.setUf(vo.getUf());
        return ee;
    }

    public static AppConfigVo toAppConfigVo(AppConfigEntity appConfig) {
        AppConfigVo vo = new AppConfigVo();
        if (appConfig == null){
            return vo;
        }

        vo.setChave(appConfig.getChave());
        vo.setValor(appConfig.getValor());

        return vo;

    }

    public static List<ConvenioCategoriaVo> toConveniosCategoriasVo(List<ConvenioCategoriaEntity> convenios) {
        List<ConvenioCategoriaVo> result = new ArrayList<>(convenios.size());
        for (ConvenioCategoriaEntity c : convenios){
            result.add(RanchucrutesAdapter.toConvenioCategoriaVo(c));
        }
        return result;
    }
}
