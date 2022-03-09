package com.jagp.app.controlador;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jagp.app.modelo.Producto;
import com.jagp.app.servicio.ProductoServices;

@Controller
@RequestMapping(value={"", "/", "/home"})
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductoServices poductoServicio;
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Producto> productos = poductoServicio.findAllProducto();
		model.addAttribute("productos", productos);
		
		return "usuario/home";
	}
	
	@GetMapping("producto_home/{id}")
	public String productoHome(@PathVariable Integer id) {
		log.info("id enviado como parametro {}", id);
		return "usuario/producto_home";
	}

}