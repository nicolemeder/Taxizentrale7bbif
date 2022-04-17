package at.project.taxizentrale7bbif.presentation.www;

import at.project.taxizentrale7bbif.service.FahrtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping(FahrtController.BASE_URL)
public class FahrtController {

    private final FahrtService fahrtService;

    public static final String BASE_URL = "/fahrten";
    public static final String PATH_INDEX = "/";
    public static final String PATH_VAR_ID = "/{id}";

    @GetMapping({"", PATH_INDEX})
    public String index(Model model, HttpServletResponse resp, HttpServletRequest req, Cookie[] cookies, HttpSession session) {
        var fahrten = fahrtService.getFahrten();
        if (fahrten.size() == 1) {
            model.addAttribute("fahrten", fahrten.get(0));
            return "taxifahrt/show";
        }
        else {
            model.addAttribute("fahrten", fahrten);
            return "taxifahrt/index";
        }
    }

    @GetMapping(PATH_VAR_ID)
    public String show(Model model, @PathVariable Long id) {
        return fahrtService.getFahrt(id)
                .map(fahrt -> model.addAttribute("fahrt", fahrt))
                .map(__ -> "taxifahrt/show")
                .orElse("taxifahrt/index");
    }

    @ExceptionHandler(Throwable.class)
    public String handleGeneralErrors(Throwable t, Model model) {
        log.warn("Es ist etwas passiert {}", t.getMessage());
        model.addAttribute("error", "Ein Fehler ist aufgetreten!");
        return "taxifahrt/error";
    }


}
