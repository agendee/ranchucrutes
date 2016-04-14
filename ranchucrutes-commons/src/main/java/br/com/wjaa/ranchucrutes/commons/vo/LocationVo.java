package br.com.wjaa.ranchucrutes.commons.vo;

import org.springframework.util.Assert;

/**
 * Created by wagner on 15/06/15.
 */
public class LocationVo {

    public static final Double ANGLE_IN_KM = 111.3194444444;



    private Double latitude;
    private Double longitude;

    public LocationVo(){}

    public LocationVo(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "LocationVo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }


    public Double getLimitInAngle(Double raioKm){
        Assert.notNull(raioKm, "Km não pode ser nula");
        Assert.isTrue(raioKm >= 5, "Raio de km tem que ser maior que 5km");
        Assert.isTrue(raioKm <= 300, "Raio de km tem que ser menor que 300km");
        //transformando km em angulo e dividimos na metade porque isso será usado para calcular os maximos e minimos.
        return ( raioKm / ANGLE_IN_KM ) / 2;

    }

    public Double getMaxLatitude(Double raioKm){
        return this.latitude + this.getLimitInAngle(raioKm);
    }

    public Double getMinLatitude(Double raioKm){
        return this.latitude - this.getLimitInAngle(raioKm);
    }

    public Double getMaxLongitude(Double raioKm){
        return this.longitude + this.getLimitInAngle(raioKm);
    }

    public Double getMinLongitude(Double raioKm){
        return this.longitude - this.getLimitInAngle(raioKm);
    }

    public boolean isNull() {
        return this.latitude == null || this.longitude == null;
    }
}
