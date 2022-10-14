package com.example.controller;

import com.example.dto.ProductDetail;
import com.example.dto.RegisterRequest;
import com.example.service.AuthenService;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ViewController {

	private final AuthenService authenService;
	
	private final ProductService productService;
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "login";
	}

	@GetMapping("/index")
	public String indexPage(Model model) {
		return "index";
	}
	
	@GetMapping("/403")
	public String forbiddenPage() {
		return "403";
	}
	
	@GetMapping("/products/{slug}")
	public String productDetail(Model model,@PathVariable(value = "slug", required = false) String slug) {
		
		ProductDetail products = productService.getProdDetail(slug);
		System.out.println(products);
		model.addAttribute("productId", products);
		return "product-detail";
	}
	
	@GetMapping("/register")
	public String registerPage(RegisterRequest registerRequest) {
		return "register";
	}

	@PostMapping("/register")
	public String doRegister(HttpServletRequest request, Model model, @Valid RegisterRequest registerRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		if (!authenService.registerUser(request, model, registerRequest)) {
			return "register";
		}
		return "redirect:/login";

	}
	@GetMapping("/adminProducts")
	public String adminPage() {
		return "qlSanPham";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/products")
	public String products() {
		return "products";
	}
	
	@GetMapping("/detail")
	public String detail() {
		return "product-detail";
	}
	
	@GetMapping("/adminBrands")
	public String brand() {
		return "qlBrand";
	}
	
	@GetMapping("/admin/img")
	public String img() {
		return "qlPicture";
	}
	
	@GetMapping("/cart")
	public String cartPage() {
		return "cart";
	}
	
	@GetMapping("/payment")
	public String paymentPage() {
		return "payment";
	}
	
	@GetMapping("/success-oder")
	public String successOder() {
		return "successOder";
	}
}
