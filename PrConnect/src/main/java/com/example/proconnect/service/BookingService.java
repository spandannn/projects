package com.example.proconnect.service;

import com.example.proconnect.model.Booking;
import com.example.proconnect.model.Professional;
import com.example.proconnect.model.Client;
import com.example.proconnect.repository.BookingRepository;
import com.example.proconnect.repository.ProfessionalRepository;
import com.example.proconnect.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsForProfessional(Long professionalId) {
        return bookingRepository.findByProfessionalId(professionalId);
    }

    public List<Booking> getBookingsForClient(Long clientId) {
        return bookingRepository.findByClientId(clientId);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setBookingTime(updatedBooking.getBookingTime());
            booking.setStatus(updatedBooking.getStatus());
            return bookingRepository.save(booking);
        }).orElseGet(() -> {
            updatedBooking.setId(id);
            return bookingRepository.save(updatedBooking);
        });
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public void cancelBooking(Long id) {
        bookingRepository.findById(id).ifPresent(booking -> {
            booking.cancel();
            bookingRepository.save(booking);
        });
    }

    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }

    public Professional saveProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
}
