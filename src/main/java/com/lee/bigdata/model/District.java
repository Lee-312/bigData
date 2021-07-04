package com.lee.bigdata.model;

import com.lee.bigdata.annotation.Message;


public class District {

    @Message(remark = "行政区")
    private String district;
    private String districtCode;

    @Message(remark = "市")
    private String city;
    private String cityCode;

    @Message(remark = "区")
    private String region;
    private String regionCode;

    @Message(remark = "街道")
    private String street;
    private String streetCode;

    @Message(remark = "社区")
    private String community;
    private String communityCode;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    @Override
    public String toString() {
        return "District{" +
                "district='" + district + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", region='" + region + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", street='" + street + '\'' +
                ", streetCode='" + streetCode + '\'' +
                ", community='" + community + '\'' +
                ", communityCode='" + communityCode + '\'' +
                '}';
    }
}
