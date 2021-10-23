package com.usa.ciclo3.reto4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.usa.ciclo3.reto4.model.*;
import com.usa.ciclo3.reto4.model.Reservation;
import com.usa.ciclo3.reto4.service.*;

@Controller
public class IndexController {

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


	@GetMapping("/")
	public String inicio() {
		return "index";
	}

	@GetMapping("/cabin")
	public String cabana(Model modelo) {
		List<Cabana> cabanas = cabinService.traerTodo();
		modelo.addAttribute("cabanas", cabanas);

		List<Category> categorias = categoryService.TraerTodo();
		modelo.addAttribute("categorias", categorias);

		return "cabin";
	}

	@GetMapping("/category")
	public String categorias(Model modelo) {
		List<Category> categorias = categoryService.TraerTodo();
		modelo.addAttribute("cats", categorias);

		return "category";
	}

	@GetMapping("/client")
	public String clientes(Model modelo) {
		List<Client> clientes = clientService.TraerTodo();
		modelo.addAttribute("clientes", clientes);
		return "client";
	}

	@GetMapping("/msg")
	public String mensajes(Model modelo) {
		List<Client> clientes = clientService.TraerTodo();
		List<Cabana> cabanas = cabinService.traerTodo();
		List<Message> mensajes = msgService.TraerTodo();

		modelo.addAttribute("clientes", clientes);
		modelo.addAttribute("cabanas", cabanas);
		modelo.addAttribute("mensajes", mensajes);

		return "msg";
	}

	@GetMapping("/reservation")
	public String reservas(Model modelo) {

		List<Reservation> reservaciones = reservationService.TraerTodo();
		modelo.addAttribute("reservas", reservaciones);

		List<Client> clientes = clientService.TraerTodo();
		modelo.addAttribute("clientes", clientes);

		List<Cabana> cabanas = cabinService.traerTodo();
		modelo.addAttribute("cabanas", cabanas);
	
		return "reservation";
	}

	@GetMapping("/admin")
	public String admins(Model modelo) {
		List<Admin> admins = adminService.traerTodo();
		modelo.addAttribute("admins", admins);

		return "admin";
	}

	@GetMapping("/formAdmin")
	public String formularioAdmin(Model modelo){
		List<Admin> admins = adminService.traerTodo();

		modelo.addAttribute("admin",new Admin());
		modelo.addAttribute("admins",admins);

		return "formAdmin";
	}

}
