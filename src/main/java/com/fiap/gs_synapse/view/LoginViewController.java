package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String login(Model model) {

        // Garante que o UsuarioDTO existe no model
        if (!model.containsAttribute("usuarioDTO")) {
            model.addAttribute("usuarioDTO", new UsuarioDTO());
        }

        // Define título da página (apenas se necessário)
        model.addAttribute("tituloPagina", "Login");

        return "login";  // Renderiza login.html
    }
}
