package com.project.hb.hotel.domain.service;

import com.project.hb.hotel.domain.model.entity.Entity;
import com.project.hb.hotel.domain.model.entity.Hotel;

import com.project.hb.hotel.domain.repository.HotelRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hotelService")
public class HotelServiceImpl extends BaseService<Hotel, String>
        implements HotelService {

    private HotelRepository<Hotel, String> hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository<Hotel, String> hotelRepository) {
        super(hotelRepository);
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void add(Hotel hotel) throws Exception {
        if (hotelRepository.containsName(hotel.getName())) {
            throw new Exception(String.format("There is already a hotel with the name - %s", hotel.getName()));
        }

        if (hotel.getName() == null || "".equals(hotel.getName())) {
            throw new Exception("Hotel name cannot be null or empty string.");
        }
        super.add(hotel);
    }

    @Override
    public Collection<Hotel> findByName(String name) throws Exception {
        return hotelRepository.findByName(name);
    }

    @Override
    public void update(Hotel hotel) throws Exception {
        hotelRepository.update(hotel);
    }

    @Override
    public void delete(String id) throws Exception {
        hotelRepository.remove(id);
    }


    @Override
    public Entity findById(String id) throws Exception {
        return hotelRepository.get(id);
    }

    @Override
    public Collection<Hotel> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Hotel> getAll() {
        return hotelRepository.getAll();
    }
}
