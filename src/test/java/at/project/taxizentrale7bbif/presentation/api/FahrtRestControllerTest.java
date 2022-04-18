package at.project.taxizentrale7bbif.presentation.api;


import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.domain.Mitarbeiter;
import at.project.taxizentrale7bbif.service.FahrtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
public class FahrtRestControllerTest {  //Unit Test

    @MockBean
    private FahrtService fahrtService;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new FahrtRestController(fahrtService)).build();
    }

    @Test
    void ensureGetFahrtenReturnsANoContentResponseWhenNoDataIsAvailable() throws Exception {
        //when
        when(fahrtService.getFahrten()).thenReturn(Collections.emptyList());

        //expect
        mockMvc.perform(get(FahrtRestController.BASE_URL).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    @Test
    void ensureGetFahrtenReturnsAnOkResponseWhenDataIsAvailable() throws Exception {
        //given
        String nummer = "AM63829";
        String taxifahrerVorname = "Anton";
        String taxifahrerNachname = "Maier";
        Mitarbeiter taxifahrer = Mitarbeiter.builder().vorname(taxifahrerVorname).nachname(taxifahrerNachname).build();
        Fahrt fahrt = Fahrt.builder().nummer(nummer).taxifahrer(taxifahrer).build();
        when(fahrtService.getFahrten()).thenReturn(List.of(fahrt));


        //expect
        mockMvc.perform(get(FahrtRestController.BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].nummer").value(nummer))
                .andExpect(jsonPath("$[0].taxifahrer.vorname").value(taxifahrerVorname))
                .andExpect(jsonPath("$[0].taxifahrer.nachname").value(taxifahrerNachname));


    }








}
