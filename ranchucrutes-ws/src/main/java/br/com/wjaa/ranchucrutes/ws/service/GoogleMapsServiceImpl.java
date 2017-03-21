package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.exception.DistanceNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationDuplicateFoundException;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.ws.exception.LocationNotFoundException;
import br.com.wjaa.ranchucrutes.commons.vo.DistanceVo;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 15/06/15.
 */
@Service
public class GoogleMapsServiceImpl implements GoogleMapsService {

    private static final Log LOG = LogFactory.getLog(GoogleMapsServiceImpl.class);
    private static final String PATTERN_ADDRESS = "{STREET}, {NUMBER} - {NEIGHBORHOOD}, {CITY} - {STATE}";

    private static GeoApiContext context;

    static{
        try{
            InputStream io = GoogleMapsServiceImpl.class.getClassLoader().getResourceAsStream("google-api.key");
            String key = IOUtils.readLines(io).get(0);
            context = new GeoApiContext().setApiKey(key);
        }catch(Exception ex){
            LOG.info("Erro ao ler a chave do google api");
        }

    }


    public LocationVo getLatLngByAddress (String address) throws LocationNotFoundException, LocationDuplicateFoundException {
        LOG.debug("m=getLatLngByAddress, address=" + address);
        LOG.info("Procurando location para endereco: " + address);
        try{

            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    address).await();

            if (results.length == 1){
                return new LocationVo(results[0].geometry.location.lat, results[0].geometry.location.lng);
            }

            LOG.info("Location encontrado duplicado ou nao encontrado para o endereco: " + address);
            throw new LocationDuplicateFoundException(address, "Talvez seja necessario mais informações do " +
                    "endereço para achar uma localizacao exata.");

        } catch (Exception ex) {
            LOG.error("Location nao encontrado para o endereco: " + address, ex);
            throw new LocationNotFoundException(address);
        }
    }

    @Override
    public List<DistanceVo> getDistance(LocationVo origin, LocationVo ... dests) throws DistanceNotFoundException {
        try{
            LOG.debug("m=getDistance, origin=" + origin + ", dests=" + dests);
            LatLng latLngOrigin = new LatLng(origin.getLatitude(),origin.getLongitude());
            LatLng [] latLngDest = this.getDestinations(dests);
            DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                    .origins(latLngOrigin)
                    .destinations(latLngDest)
                    .mode(TravelMode.DRIVING)
                    .language("pt-BR")
                    .await();


            List<DistanceVo> distanceVos = new ArrayList<>(dests.length);

            if (matrix != null && matrix.rows != null){

                for (int i = 0; i < matrix.rows[0].elements.length; i ++){
                    if (DistanceMatrixElementStatus.OK.equals(matrix.rows[0].elements[i].status) ){
                        distanceVos.add(new DistanceVo(matrix.rows[0].elements[i].distance.toString(),
                                matrix.rows[0].elements[i].duration.toString()));
                    }
                }

                return distanceVos;
            }

            throw new DistanceNotFoundException();

        } catch (Exception ex) {
            LOG.error("Distance nao encontrado para a origin: " + origin + " dest:" + dests, ex);
            throw new DistanceNotFoundException();
        }

    }

    private LatLng[] getDestinations(LocationVo[] dests) {
        LatLng[] lls = new LatLng[dests.length];
        for (int i = 0; i < dests.length ; i++){
            lls[i] = new LatLng(dests[i].getLatitude(),dests[i].getLongitude());
        }
        return lls;
    }

    @Override
    public String patternAddress(EnderecoEntity enderecoEntity) {
        LOG.debug("m=patternAddress, enderecoEntity=" + enderecoEntity);
        String [] params = new String[]{"{STREET}","{NUMBER}","{NEIGHBORHOOD}","{CITY}" ,"{STATE}"};
        String [] values = new String[]{enderecoEntity.getLogradouro(),
                enderecoEntity.getNumero() != null ? enderecoEntity.getNumero().toString() : "" ,
                enderecoEntity.getBairro(), enderecoEntity.getLocalidade(), enderecoEntity.getUf()};
        return StringUtils.replaceEach(PATTERN_ADDRESS,params,values);
    }
}
