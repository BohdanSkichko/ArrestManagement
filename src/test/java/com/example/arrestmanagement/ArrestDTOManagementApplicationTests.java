//package com.example.arrestmanagement;
//
//import com.example.arrestmanagement.dto.ArrestDTO;
//import com.example.arrestmanagement.dto.ArrestRequest;
//import com.example.arrestmanagement.dto.IdentDocDTO;
//import com.example.arrestmanagement.entity.Client;
//import com.example.arrestmanagement.service.ArrestService;
//import com.example.arrestmanagement.service.ClientService;
//import org.junit.Rule;
//import org.junit.jupiter.api.Test;
//import org.junit.rules.ExpectedException;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.sql.Date;
//import java.util.Optional;
//
//
//@SpringBootTest
//class ArrestDTOManagementApplicationTests {
//
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @Test
//    public void whenCreateNewUserThenReturnListWithNewUser() {
//        ArrestRequest arrestRequest = new ArrestRequest();
//        arrestRequest.setLastname("Skichko");
//        arrestRequest.setFirstName("Ira");
//        arrestRequest.setOrganCode(39);
//
//        ArrestDTO arrestDTO = new ArrestDTO();
//        arrestDTO.setAmount(12131131L);
//        arrestDTO.setDocDate(new Date(2012, 11, 22));
//        arrestDTO.setOperation(1);
//        arrestDTO.setRefDocNum("1");
//        arrestDTO.setDocNum("112*");
//        arrestDTO.setPurpose("121291asd*;nfasd");
//
//        arrestRequest.setArrestDTO(arrestDTO);
//
//        IdentDocDTO identDocDTO = new IdentDocDTO();
//
//        identDocDTO.setType(22);
//        identDocDTO.setNumberSeries("22 923456");
//        identDocDTO.setIssueDate(new Date(2014, 11, 11));
//        arrestRequest.setIdentDocDTO(identDocDTO);
//
//        clientService.getClientFromRequest(arrestRequest);
//        clientService.createClientsFormatFromRequest(arrestRequest);
//
//        Client client = new Client();
//        client.setLastName(arrestRequest.getLastname());
//        client.setFirstName(arrestRequest.getFirstName());
//        client.setDulType(2);
//        client.setNumSeries("923456 21");
//
//        Client client1 = new Client();
//        client1.setFirstName("as");
//
//        Mockito.when(clientService.getClientFromRequest(arrestRequest)).thenReturn(client);
//        Mockito.when(clientService.getClientFromRequest(arrestRequest))
//                .thenReturn(client);
//        Mockito.when(clientService.findClient(Mockito.eq(client)))
//                .thenReturn(Optional.of(client1));
//
//        String s = arrestDTO.getPurpose();
//        String a = arrestDTO.getDocNum();
//        System.out.println(a);
//    }
//
//    ArrestService arrestService = Mockito.mock(ArrestService.class);
//    ClientService clientService = Mockito.mock(ClientService.class);
//
//    public ArrestService getArrestService() {
//        return arrestService;
//    }
//
//}
