package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.vo.ConvenioCategoriaVo;
import br.com.wjaa.ranchucrutes.ws.entity.ConvenioCategoriaEntity;
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
}
