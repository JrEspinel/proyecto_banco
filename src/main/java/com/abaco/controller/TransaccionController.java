package com.abaco.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abaco.model.Cuenta;
import com.abaco.model.Transaccion;
import com.abaco.service.CuentaService;
import com.abaco.service.TransaccionService;

@Controller
@RequestMapping("/transacciones")
public class TransaccionController {

	@Autowired
	TransaccionService transaccionService;
	
	@Autowired
	CuentaService cuentaService;

	@GetMapping("/")
	public String transacciones(Model model) {
		model.addAttribute("transacciones", transaccionService.listarTodo());
		return "/vistas/transacciones/lista";
	}
	
	@GetMapping("/formulario")
	public String formulario(Transaccion transaccion,Model model) {
		model.addAttribute("cuentas", cuentaService.listarTodo());
		return "/vistas/transacciones/formulario";
	}
	
	@PostMapping("/guardar")
	public String guardar(Transaccion transaccion,RedirectAttributes attributes) {
		Optional<Cuenta> cuenta = cuentaService.buscarPorId(transaccion.getNumeroCuenta().getNumeroCuenta());
		switch (transaccion.getTipoTransaccion()) {
		case "ENVIAR":
			cuenta.get().setSaldo(cuenta.get().getSaldo()+transaccion.getValor());
			cuentaService.guardar(cuenta.get());
			break;
		case "RETIRAR":
			cuenta.get().setSaldo(cuenta.get().getSaldo()-transaccion.getValor());
			cuentaService.guardar(cuenta.get());
			break;
		default:
			break;
		}
		
		transaccionService.guardar(transaccion);
		attributes.addFlashAttribute("guardado","Transacción realizada con éxito");
		return "redirect:/transacciones/";
	}

}
