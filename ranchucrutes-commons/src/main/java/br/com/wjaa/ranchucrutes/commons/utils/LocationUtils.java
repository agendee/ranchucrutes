package br.com.wjaa.ranchucrutes.commons.utils;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;

/**
 * Created by wagner on 5/10/16.
 */
public class LocationUtils {

    public static Double getDistanceInKm(LocationVo source, LocationVo dest){

        if (source == null || dest == null){
            return 0.0;
        }

        double xa = source.getLatitude();
        double ya = source.getLongitude();
        double xb = dest.getLatitude();
        double yb = dest.getLongitude();

        double distance = Math.sqrt( Math.pow(xb-xa,2) + Math.pow(yb - ya,2));
        return LocationVo.ANGLE_IN_KM * distance;
    }

}
