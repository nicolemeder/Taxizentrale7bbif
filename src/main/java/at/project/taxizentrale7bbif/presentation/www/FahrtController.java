package at.project.taxizentrale7bbif.presentation.www;

import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.persistence.FahrtRepository;
import at.project.taxizentrale7bbif.service.FahrtService;
import at.project.taxizentrale7bbif.service.MitarbeiterService;
import at.project.taxizentrale7bbif.service.dtos.MutateFahrtCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public static final String ROUTE_SHOW = "/show" + PATH_VAR_ID;
    public static final String ROUTE_DELETE = "/delete" + PATH_VAR_ID;
    public static final String ROUTE_UPDATE = "/update" + PATH_VAR_ID;
    public static final String ERROR = "/error";

    @GetMapping({"", PATH_INDEX})
    public String index(Model model) {
        var fahrten = fahrtService.getFahrten();
            model.addAttribute("fahrten", fahrten);
            return "taxifahrt/index";
        }


    @GetMapping(ROUTE_SHOW)
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
        model.addAttribute("fahrt", new CreateFahrtForm());
        model.addAttribute("neuerMitarbeiter", new CreateMitarbeiterForm());
        return "taxifahrt/create";
    }

    @PostMapping(ROUTE_NEW)
    public String handleCreateForm(@Valid @ModelAttribute(name = "neueFahrt") CreateFahrtForm newFahrtForm, BindingResult brNewFahrtForm,
                                   @Valid @ModelAttribute(name = "neuerMitarbeiter") CreateMitarbeiterForm newMitarbeiterForm, BindingResult brNewMitarbeiterForm,
                                   Model model) {

        if (brNewFahrtForm.hasErrors() || brNewMitarbeiterForm.hasErrors()) {
            model.addAttribute("mitarbeiter", mitarbeiterService.getMitarbeiter());
            model.addAttribute("fahrt", new CreateFahrtForm());
            model.addAttribute("neuerMitarbeiter", new CreateMitarbeiterForm());
            log.error(brNewFahrtForm.getAllErrors().toString());
            return "taxifahrt/create";
        }
        fahrtService.createFahrt(newFahrtForm, newMitarbeiterForm);

        return "redirect:/fahrten";
    }


    @GetMapping(ROUTE_UPDATE)
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Fahrt fahrt = fahrtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fahrt Id:" + id));

        model.addAttribute("fahrt", fahrt);
        return "/taxifahrt/edit";
    }

    @PostMapping(ROUTE_UPDATE)
    public String updateFahrt(@PathVariable("id") Long id, @Valid Fahrt fahrt,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/fahrten/error";
        }
        fahrtService.partiallyUpdateFahrt(id, fahrt);
        return "redirect:/fahrten";
    }

    @GetMapping(ROUTE_DELETE)
    public String deleteFahrt(@PathVariable("id") long id, Model model) {
        Fahrt fahrt = fahrtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fahrt Id:" + id));
        fahrtRepository.deleteById(id);
        return "redirect:/fahrten";
    }

    @GetMapping(ERROR)
    public String error() {
        return "/taxifahrt/error";
    }
}











