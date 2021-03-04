package com.abaco.controller;

import com.abaco.model.Cuenta;
import com.abaco.service.ClienteService;
import com.abaco.service.CuentaService;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	CuentaService cuentaService;

	@Autowired
	ClienteService clienteService;

	@GetMapping("/")
	public String listarCuentas(Model model) {
		model.addAttribute("cuentas", cuentaService.listarTodo());
		return "vistas/cuentas/lista";
	}

	@GetMapping("/formulario")
	public String formularioCuenta(Cuenta cuenta, Model model) {
		model.addAttribute("clientes", clienteService.listarTodo());
		return "vistas/cuentas/formulario";
	}

	@GetMapping("/editar/{numCuenta}")
	public String editar(@PathVariable String numCuenta, Model model) {
		Cuenta cuenta = cuentaService.buscarPorId(numCuenta).get();
		model.addAttribute("clientes",clienteService.listarTodo());
		model.addAttribute("cuenta",cuenta);
		return "vistas/cuentas/formulario";
	}

	@PostMapping("/guardar")
	public String guardarCuenta(Cuenta cuenta, RedirectAttributes attributes) {
		boolean _cuenta = cuentaService.buscarPorId(cuenta.getNumeroCuenta()).isPresent();
		System.out.println(_cuenta);
		if(!_cuenta) {
			cuenta.setFecha(new Date());
			cuentaService.guardar(cuenta);
			attributes.addFlashAttribute("ok", "Cuenta guardada correctamente");
			return "redirect:/cuentas/";
		}
		attributes.addFlashAttribute("error", "Esta cuenta ya esta asignada");
		return "redirect:/cuentas/";
		
	}

	@GetMapping("/eliminar/{numeroCuenta}")
	public String eliminarCuenta(@PathVariable String numeroCuenta, RedirectAttributes attributes) {
		 Optional<Cuenta> _cuenta = cuentaService.buscarPorId(numeroCuenta);
		 if(!_cuenta.isPresent()) {
			 attributes.addFlashAttribute("error", "La cuenta no existe");
			return "redirect:/cuentas/";
		 }
		 attributes.addFlashAttribute("ok", "Cuenta eliminada correctamente");
		 cuentaService.eliminarPorCuenta(numeroCuenta);
		return "redirect:/cuentas/";
	}

	@GetMapping("/estado/{estado}")
	public String listarPorEstado(@PathVariable String estado, Model model) {
		System.out.println(estado);
		if (estado.equalsIgnoreCase("activas")) {
			model.addAttribute("cuentas", cuentaService.listarPorEstado(true));
		} else if (estado.equalsIgnoreCase("inactivas")) {
			model.addAttribute("cuentas", cuentaService.listarPorEstado(false));
		}

		return "vistas/cuentas/lista";
	}

}
