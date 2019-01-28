package com.stackroute.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.MuzixController;
import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.MuzixAlreadyExistsException;
import com.stackroute.exceptions.MuzixTrackNotFoundException;
import com.stackroute.service.MuzixService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@WebMvcTest
public class MuzixControllerTest {

        @Autowired
        private MockMvc mockMvc;
        private Muzix muzix;
        @MockBean
        private MuzixService muzixService;
        @InjectMocks
        private MuzixController muzixController;

        private List<Muzix> list =null;

        @Before
        public void setUp(){

            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
            muzix = new Muzix();
            muzix.setComment("Enrique song");
            muzix.setTrackId(1);
            muzix.setTrackName("Freak");
            list = new ArrayList();
            list.add(muzix);
        }

        @Test
        public void saveMuzix() throws Exception {
            when(muzixService.saveMuzix(any())).thenReturn(muzix);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
//        @Test
//        public void saveMuzixFailure() throws Exception {
//            when(muzixService.saveMuzix(any())).thenThrow(MuzixAlreadyExistsException.class);
//            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
//                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
//                    .andExpect(MockMvcResultMatchers.status().isConflict())
//                    .andDo(MockMvcResultHandlers.print());
//        }

        @Test
        public void getAllMuzix() throws Exception {
            when(muzixService.getAllMuzixs()).thenReturn(list);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }


    @Test
    public void getAllMuzixFailure() throws Exception {
        when(muzixService.getAllMuzixs()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateMuzix() throws Exception {
        when(muzixService.updateMuzix(1,"Enrique Song")).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void removeMuzix() throws Exception {
        when(muzixService.removeMuzix(1)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void trackByName() throws Exception {
        when(muzixService.trackByName("Baby")).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void trackById() throws Exception {
        when(muzixService.trackById(1)).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

        private static String asJsonString(final Object obj)
        {
            try{
                return new ObjectMapper().writeValueAsString(obj);

            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }
