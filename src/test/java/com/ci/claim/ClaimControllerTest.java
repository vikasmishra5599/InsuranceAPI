package com.ci.claim;

import com.ci.domain.ClaimResponse;
import com.ci.domain.CreateClaimResponse;
import com.ci.domain.ErrorType;
import com.ci.domain.StatusType;
import com.ci.dto.ClaimDetail;
import com.ci.exception.ClaimRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import static com.ci.domain.StatusType.FAILED;
import static com.ci.domain.StatusType.OK;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StringUtils.isEmpty;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClaimController.class)
public class ClaimControllerTest {
    @MockBean
    private ClaimService mockService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void should_create_claim_successfully() throws Exception {
        Mockito.when(mockService.createClaim(any()))
                .thenReturn(createDummyServiceResponse(1212, null,null));

        MvcResult mvcResult = this.mockMvc.perform(post("/claims/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyRequest()))
                .andExpect(status().isOk())
                .andReturn();

        CreateClaimResponse response = new ObjectMapper()
                .readValue(mvcResult.getResponse().getContentAsString(), CreateClaimResponse.class);

        assertThat(response.getStatus()).isEqualTo(OK);
        assertThat(isEmpty(response.getClaimResponse().getErrorType())).isTrue();
        assertThat(response.getClaimResponse().getClaimOrderNO()).isEqualTo(1212);
    }

    @Test
    public void should_handle_bad_request_exception_gracefully() throws Exception {
        Mockito.when(mockService.createClaim(any()))
                .thenThrow(new ClaimRequestException(asList(new ErrorType(400, "bad request"))));

        MvcResult mvcResult = this.mockMvc.perform(post("/claims/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyRequest()))
                .andExpect(status().isOk())
                .andReturn();

        CreateClaimResponse response = new ObjectMapper()
                .readValue(mvcResult.getResponse().getContentAsString(), CreateClaimResponse.class);

        assertThat(response.getStatus()).isEqualTo(FAILED);
        assertThat(response.getClaimResponse().getErrorType().size()).isEqualTo(1);
        assertThat(response.getClaimResponse().getErrorType().get(0).getCode()).isEqualTo(400);
    }

    @Test
    public void should_fetch_all_claims() throws Exception {
        Mockito.when(mockService.fetchAllClaims())
                .thenReturn(createDummyServiceResponse(0,
                        null,
                        asList(createDummyClaimDetail(BigInteger.TEN, "abcd@test.com"))));

        MvcResult mvcResult = this.mockMvc.perform(get("/claims/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CreateClaimResponse response = new ObjectMapper()
                .readValue(mvcResult.getResponse().getContentAsString(), CreateClaimResponse.class);

        assertThat(response.getStatus()).isEqualTo(OK);
        assertThat(isEmpty(response.getClaimResponse().getErrorType())).isTrue();
        assertThat(response.getClaimResponse().getClaimDetails().get(0).getId()).isEqualTo(10);
    }

    @Test
    public void should_fetch_claims_for_id() throws Exception {
        Mockito.when(mockService.fetchClaimDetailForId(1234))
                .thenReturn(createDummyServiceResponse(0,
                        null,
                        asList(createDummyClaimDetail(new BigInteger("1234"), "abcd@test.com"))));

        MvcResult mvcResult = this.mockMvc.perform(get("/claims/claim/1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CreateClaimResponse response = new ObjectMapper()
                .readValue(mvcResult.getResponse().getContentAsString(), CreateClaimResponse.class);

        assertThat(response.getStatus()).isEqualTo(OK);
        assertThat(isEmpty(response.getClaimResponse().getErrorType())).isTrue();
        assertThat(response.getClaimResponse().getClaimDetails().get(0).getId()).isEqualTo(1234);
    }

    @Test
    public void should_fetch_claims_for_emailId() throws Exception {
        Mockito.when(mockService.fetchClaimDetailForEmailId("abcd@test.com"))
                .thenReturn(createDummyServiceResponse(0,
                        null,
                        asList(createDummyClaimDetail(new BigInteger("1234"), "abcd@test.com"))));

        MvcResult mvcResult = this.mockMvc.perform(get("/claims/abcd@test.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CreateClaimResponse response = new ObjectMapper()
                .readValue(mvcResult.getResponse().getContentAsString(), CreateClaimResponse.class);

        assertThat(response.getStatus()).isEqualTo(OK);
        assertThat(isEmpty(response.getClaimResponse().getErrorType())).isTrue();
        assertThat(response.getClaimResponse().getClaimDetails().get(0).getId()).isEqualTo(1234);
    }

    private String dummyRequest() {
        return "{\n" +
                "    \"firstName\": \"VIKAS\",\n" +
                "    \"lastName\": \"mishra\",\n" +
                "\t\"emailId\":\"vikasmisghra@gmail.com\",\n" +
                "\t\"policyNumber\": \"pol123\",\n" +
                "\t\"originalFlightNumber\":\"FLN113121\",\n" +
                "\t\"reasonOfDelay\":\"Weather\",\n" +
                "\t\"consequenceOfDelay\" :\"CANCELLATION\",\n" +
                "\t\"replacementFlightDetail\": {\n" +
                "\t\t\"flightNo\":\"CVASS\",\n" +
                "\t\t\"date\":\"02-01-2020 06:07:59\"\n" +
                "\t},\n" +
                "\t\"delayTime\":\"12\",\n" +
                "\t\t\"dateOfOriginalFlight\":\"22-01-2020 06:07:59\"\n" +
                "}";
    }

    private ClaimDetail createDummyClaimDetail(BigInteger id, String emailId) {
        return ClaimDetail.builder()
                .withId(id)
                .withFirstName("FNAME")
                .withLastName("LNAME")
                .withEmailId(emailId)
                .withPolicyNumber("PO123")
                .withOriginalFlightNumber("SA12233")
                .withOriginalFlightDate(new Date())
                .withReasonOfDelay("Security")
                .withConsequenceOfDelay("Delay")
                .withDelayTime("12")
                .build();
    }

    public CreateClaimResponse createDummyServiceResponse(long orderNo, List<ErrorType> errorTypes, List<ClaimDetail> claimDetails) {
        ClaimResponse claimResponse = new ClaimResponse();
        claimResponse.setClaimOrderNO(orderNo);
        claimResponse.setErrorType(errorTypes);
        claimResponse.setClaimDetails(claimDetails);
        StatusType statusType = StatusType.fromValue("Ok");
        return new CreateClaimResponse(claimResponse, statusType);
    }
}