package com.trip.enjoy_trip.service;

import com.trip.enjoy_trip.dto.AttractionDto;
import com.trip.enjoy_trip.dto.MarkerDto;
import com.trip.enjoy_trip.repository.MapRepository;
import com.trip.enjoy_trip.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private MapRepository mapRepository;


    //Map 검색 API: 지역, 시군구, 관광지명 순서로 검색
    @Override
    public List<AttractionDto> searchAreasByTitle(String title) {
        return mapRepository.findAreasByTitle(title);
    }
    @Override
    public List<AttractionDto> searchSigungusByTitle(String title) {
        return mapRepository.findSigungusByTitle(title);
    }
    @Override
    public List<AttractionDto> searchAttractionsByTitle(String title) {
        return mapRepository.findAttractionsByTitle(title);
    }

    //시군구 검색
    @Override
    public List<AttractionDto> getAttractionsByRegion(Integer areaCode, Integer siGunGuCode) {
        return mapRepository.findAttractionsByRegion(areaCode, siGunGuCode);
    }
    //콘텐츠 검색
    @Override
    public List<AttractionDto> searchAttractionsByContentTypeId(Integer contentTypeId) {
        return mapRepository.findAttractionsByContentTypeId(contentTypeId);
    }

    //근처 명소 검색
    @Override
    public List<AttractionDto> searchNearbyAttractions(double latitude, double longitude, double radius) {
        return mapRepository.findNearbyAttractions(latitude, longitude, radius);
    }


    //마커 기능
    // 사용자의 마커 목록 조회
    @Override
    public List<MarkerDto> getUserMarkers(Integer userId) {
        return mapRepository.findMarkersByUserId(userId);
    }

    //마커 수정본
    @Override
    public void addMarker(MarkerDto markerDTO, Integer userId) {
        markerDTO.setUserId(userId);
        mapRepository.insertMarker(markerDTO);
    }

    @Override
    public boolean isMarkerExists(Integer attractionId, Integer userId) {
        return mapRepository.findMarkerByUserAndAttraction(userId, attractionId) != null;
    }

    @Override
    public boolean removeMarker(Integer attractionId, Integer userId) {
        return mapRepository.deleteMarkerByUserAndAttraction(userId, attractionId) > 0;
    }


}
