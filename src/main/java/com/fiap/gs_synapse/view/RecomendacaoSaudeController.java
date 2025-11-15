
import com.fiap.gs_synapse.dto.RecomendacaoSaudeDTO;
import com.fiap.gs_synapse.service.RecomendacaoSaudeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/recomendacoes/saude")
public class RecomendacaoSaudeController {

    private final RecomendacaoSaudeService service;

    public RecomendacaoSaudeController(RecomendacaoSaudeService service) {
        this.service = service;
    }

    // LISTAR TODAS
    @GetMapping
    public String listarTodas(Model model, Locale locale) {
        // Como não há método listarTodos no service, você pode criar um ou apenas listar pelo banco direto
        List<RecomendacaoSaudeDTO> recomendacoes = service.listarTodos(locale);
        model.addAttribute("recomendacoes", recomendacoes);
        model.addAttribute("recomendacaoSaudeDTO", new RecomendacaoSaudeDTO());
        return "recomendacao-saude";
    }

    // SALVAR NOVA OU ATUALIZAR EXISTENTE
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("recomendacaoSaudeDTO") RecomendacaoSaudeDTO dto, Locale locale) {
        if (dto.getIdRecomendacao() != null && service.existe(dto.getIdRecomendacao())) {
            service.atualizar(dto.getIdRecomendacao(), dto, locale);
        } else {
            service.criar(dto, locale);
        }
        return "redirect:/recomendacoes/saude";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, Locale locale) {
        RecomendacaoSaudeDTO dto = service.buscarPorId(id, locale);
        List<RecomendacaoSaudeDTO> recomendacoes = service.listarTodos(locale);
        model.addAttribute("recomendacaoSaudeDTO", dto);
        model.addAttribute("recomendacoes", recomendacoes);
        return "recomendacao-saude";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, Locale locale) {
        service.deletar(id, locale);
        return "redirect:/recomendacoes/saude";
    }
}
