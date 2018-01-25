package com.project.hb.billing.domain.service;

import com.project.hb.billing.domain.model.entity.Booking;
import com.project.hb.billing.domain.model.entity.Entity;
import com.project.hb.billing.domain.repository.BookingRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookingService")
public class BookingServiceImpl extends BaseService<Booking, String>
        implements BookingService {

    private BookingRepository<Booking, String> bookingRepository;

    /**
     *
     * @param bookingRepository
     */
    @Autowired
    public BookingServiceImpl(BookingRepository<Booking, String> bookingRepository) {
        super(bookingRepository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void add(Booking booking) throws Exception {
        if (bookingRepository.containsName(booking.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", booking.getName()));
        }

        if (booking.getName() == null || "".equals(booking.getName())) {
            throw new Exception("Booking name cannot be null or empty string.");
        }
        super.add(booking);
    }

    @Override
    public Collection<Booking> findByName(String name) throws Exception {
        return bookingRepository.findByName(name);
    }

    @Override
    public void update(Booking booking) throws Exception {
        bookingRepository.update(booking);
    }

    @Override
    public void delete(String id) throws Exception {
        bookingRepository.remove(id);
    }

    @Override
    public Entity findById(String id) throws Exception {
        return bookingRepository.get(id);
    }

    @Override
    public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
