package at.project.taxizentrale7bbif.presentation.www;

import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.persistence.FahrtRepository;
import at.project.taxizentrale7bbif.service.FahrtService;
import at.project.taxizentrale7bbif.service.MitarbeiterService;
import at.project.taxizentrale7bbif.service.dtos.FahrtDTO;
import at.project.taxizentrale7bbif.service.dtos.MutateFahrtCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static at.project.taxizentrale7bbif.domain.QFahrt.fahrt;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping(FahrtController.BASE_URL)
public class FahrtController {

    private final FahrtService fahrtService;
    private final MitarbeiterService mitarbeiterService;
    private final FahrtRepository fahrtRepository;

    public static final String BASE_URL = "/fahrten";
    public static final String PATH_INDEX = "/";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String ROUTE_NEW = "/new";
    public static final String ROUTE_DELETE = "/delete" + PATH_VAR_ID;
    public static final String ROUTE_UPDATE = "/update" + PATH_VAR_ID;

    @GetMapping({"", PATH_INDEX})
    public String index(Model model, HttpServletResponse resp, HttpServletRequest req, HttpSession session) {
        var fahrten = fahrtService.getFahrten();
        if (fahrten.size() == 1) {
            model.addAttribute("fahrten", fahrten.get(0));
            return "taxifahrt/show";
        } else {
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



    @GetMapping(ROUTE_NEW)
    public String showCreateForm(Model model) {
        model.addAttribute("mitarbeiter", mitarbeiterService.getMitarbeiter());
        model.addAttribute("fahrt", fahrtService.getFahrten());
        model.addAttribute("neueFahrt", new CreateFahrtForm());
        model.addAttribute("neuerMitarbeiter", new CreateMitarbeiterForm());
        return "taxifahrt/create";
    }

    /*@PostMapping(ROUTE_NEW)
    public String handleCreateForm(@Valid @ModelAttribute(name = "neueFahrt") CreateFahrtForm newFahrtForm, BindingResult brNewFahrtForm,
                                   @Valid @ModelAttribute(name = "neuerMitarbeiter") CreateMitarbeiterForm newMitarbeiterForm, BindingResult brNewMitarbeiterForm,
                                   Model model) {

        if (brNewFahrtForm.hasErrors() || brNewMitarbeiterForm.hasErrors()) {
            model.addAttribute("Mitarbeiter", mitarbeiterService.getMitarbeiter());
            return "fahrten/create";
        }
        fahrtService.createFahrt(newFahrtForm, newMitarbeiterForm);

        return BASE_URL;
    }

*/
    @GetMapping(ROUTE_UPDATE)
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Fahrt fahrt = fahrtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fahrt Id:" + id));

        model.addAttribute("fahrt", fahrt);
        return "/taxifahrt/edit" + PATH_VAR_ID;
    }

    @PostMapping(ROUTE_UPDATE)
    public String updateFahrt(@PathVariable("id") Long id, MutateFahrtCommand command, @Valid Fahrt fahrt,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            fahrtService.partiallyUpdateFahrt(id, command);
            return BASE_URL;
        }

        fahrtRepository.save(fahrt);
        return BASE_URL;
    }

    @GetMapping(ROUTE_DELETE)
    public String deleteFahrt(@PathVariable("id") long id, Model model) {
        Fahrt fahrt = fahrtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fahrt Id:" + id));
        fahrtRepository.delete(fahrt);
        return BASE_URL;
    }
}











