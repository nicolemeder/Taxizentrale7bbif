package at.project.taxizentrale7bbif.presentation.www;

import at.project.taxizentrale7bbif.service.FahrtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@Controller
@RequestMapping(FahrtController.BASE_URL)
public class FahrtController {

    private final FahrtService fahrtService;

    public static final String BASE_URL = "/fahrten";
    public static final String PATH_INDEX = "/";

    @GetMapping({"", PATH_INDEX})
    public String browseFahrt(Model model) {
        var fahrten = fahrtService.getFahrten();
        model.addAttribute("fahrten", fahrten);
        return "taxifahrt/index";
    }




}
