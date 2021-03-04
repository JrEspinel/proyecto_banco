package com.abaco.controller;

import com.abaco.model.Cliente;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abaco.service.ClienteService;
import com.abaco.service.CuentaService;
import com.abaco.service.TransaccionService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	TransaccionService transaccionService;

	@Autowired
	CuentaService cuentaService;

	@GetMapping("/")
	public String listarClientes(Model model) {
		model.addAttribute("clientes", clienteService.listarTodo());
		return "vistas/clientes/lista";
	}

	@GetMapping("/formulario")
	public String formularioCliente(Cliente cliente) {

		return "vistas/clientes/formulario";
	}

	@PostMapping("/guardar")
	public String guardarCliente(Cliente cliente, RedirectAttributes attributes) {
		attributes.addFlashAttribute("guardado", "Usuario creado con exito.");
		clienteService.guardar(cliente);
		return "redirect:/clientes/";
	}

	@GetMapping("eliminar")
	@Transactional
	public String eliminarCliente(@RequestParam String cedula, RedirectAttributes attributes) {
		try {
			clienteService.eliminarPorCedula(cedula);
			attributes.addFlashAttribute("ok", "El cliente fue eliminado correctamente");
			return "redirect:/clientes/";
		} catch (Exception e) {
			attributes.addFlashAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			return "redirect:/clientes/";
		}
	}

}
