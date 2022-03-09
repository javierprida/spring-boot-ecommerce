package com.jagp.app.controlador;


import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jagp.app.modelo.Producto;
import com.jagp.app.modelo.Usuario;
import com.jagp.app.servicio.ProductoServices;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoServices productoServicio;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoServicio.findAllProducto());
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String saveProducto(Producto producto) {
		LOGGER.info("este es el objeto producto {}",producto);
		Usuario user = new Usuario(1, "", "", "", "", "", "", null);
		producto.setUsuario(user);
		productoServicio.saveProducto(producto);
		return "redirect:/productos";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoServicio.getProducto(id);
		producto = optionalProducto.get();
		
		LOGGER.info("producto buscado {}",producto);
		
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String updateProducto(Producto producto) {
		
		productoServicio.updateProducto(producto);
		
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProducto(@PathVariable Integer id) {
		
		productoServicio.deleteProducto(id);
		
		return "redirect:/productos";
	}
}
