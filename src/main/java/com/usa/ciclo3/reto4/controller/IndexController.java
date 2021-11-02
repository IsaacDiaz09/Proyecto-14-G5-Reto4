package com.usa.ciclo3.reto4.controller;

import java.util.List;

import com.usa.ciclo3.reto4.model.*;
import com.usa.ciclo3.reto4.service.*;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // inyeccion de la dependencia de los servicios necesarios
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private MessageService msgService;
    @Autowired
    private CabanaService cabinService;
    @Autowired
    private CabinRatingService cabinRatingService;

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/formCabin")
    public String cabana(Model modelo) {

        List<Cabana> cabanas = cabinService.traerTodo();
        List<Category> categorias = categoryService.TraerTodo();

        modelo.addAttribute("categorias", categorias);
        modelo.addAttribute("cabin", new Cabana());
        modelo.addAttribute("cabanas", cabanas);

        return "formCabin";
    }

    @GetMapping("/formCategory")
    public String categorias(Model modelo) {
        List<Category> categorias = categoryService.TraerTodo();
        modelo.addAttribute("cats", categorias);
        modelo.addAttribute("category", new Category());

        return "formCategory";
    }

    @GetMapping("/formClient")
    public String clientes(Model modelo) {
        List<Client> clientes = clientService.TraerTodo();
        modelo.addAttribute("clients", clientes);
        modelo.addAttribute("client", new Client());
        return "formClient";
    }

    @GetMapping("/formMsg")
    public String mensajes(Model modelo) {
        List<Client> clients = clientService.TraerTodo();
        List<Cabana> cabins = cabinService.traerTodo();
        List<Message> messages = msgService.TraerTodo();

        modelo.addAttribute("message", new Message());
        modelo.addAttribute("clients", clients);
        modelo.addAttribute("cabins", cabins);
        modelo.addAttribute("messages", messages);

        return "formMsg";
    }

    @GetMapping("/formReservation")
    public String reservas(Model modelo) {

        List<Reservation> reservaciones = reservationService.traerReservas();
        modelo.addAttribute("reservas", reservaciones);

        List<Client> clientes = clientService.TraerTodo();
        modelo.addAttribute("clientes", clientes);

        List<Cabana> cabanas = cabinService.traerTodo();
        modelo.addAttribute("cabanas", cabanas);

        modelo.addAttribute("reservation", new Reservation());

        return "formReservation";
    }

    @GetMapping("/formRatingReservation")
    public String calificaciones(Model modelo) {

        List<CabinRating> calificaciones = cabinRatingService.TraerTodo();
        modelo.addAttribute("calificaciones", calificaciones);

        List<Reservation> reservaciones = reservationService.traerReservas();
        List<Reservation> reservacionesNoCalificadas = new ArrayList<>();
        for (Reservation reservacion : reservaciones) {
            if (Objects.isNull(reservacion.getScore())) {
                reservacionesNoCalificadas.add(reservacion);
            }
        }
        modelo.addAttribute("reservaciones", reservacionesNoCalificadas);

        modelo.addAttribute("rating", new CabinRating());

        return "formRatingReservation";
    }

    @GetMapping("/formAdmin")
    public String formularioAdmin(Model modelo) {
        List<Admin> admins = adminService.traerTodo();

        modelo.addAttribute("admin", new Admin());
        modelo.addAttribute("admins", admins);

        return "formAdmin";
    }

}
