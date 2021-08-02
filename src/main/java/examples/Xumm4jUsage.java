package examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fl.xrpl4j.model.jackson.ObjectMapperFactory;
import com.fl.xrpl4j.model.transactions.Address;
import com.fl.xrpl4j.model.transactions.Payment;
import com.fl.xrpl4j.model.transactions.XrpCurrencyAmount;
import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.GetPayloadDAO;
import com.fl.xumm4j.sdk.DeserializeIT;
import com.fl.xumm4j.sdk.XummClient;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.sdk.builder.PayloadBuilder;

import java.math.BigDecimal;

class Xumm4jUsage {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = ObjectMapperFactory.create();
        // Don't worry about this key being exposed... I built it for testing, demonstration purposes. keys not being used in production env.
        CredentialsBuilder myAccess = new CredentialsBuilder.builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("6dab854e-b317-47f7-8453-490b8bd171ad")
                .build();
        XummClient xummClient = new XummClient(myAccess);

        //Use modified version of xrpl4j model, It won't throw an error if Account and sequence are missing.
        Payment payment = Payment.builder()
                //.account()
                //.sequence()
                .fee(XrpCurrencyAmount.ofDrops(12))
                .destination(Address.of("ra5nK24KXen9AHvsdFTKHSANinZseWnPcX"))
                .amount(XrpCurrencyAmount.ofXrp(BigDecimal.valueOf(8787)))
                .build();
        String JSON = null;
        try {
            JSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String payload = new PayloadBuilder.builder()
                .txjson(JSON)
                .instruction("This is a payment transaction")
                .build();

        System.out.println("Generated Payload: \n" + payload);
        String Payload = payload;
        System.out.println(Payload+"\n");
        //Post payload
        System.out.println(xummClient.postPayload(Payload));

        System.out.println(xummClient.getRates("USD"));
        System.out.println(xummClient.doPing());
        System.out.println(xummClient.getCuratedAssets());
        //Storage
        System.out.println(xummClient.setStorage(JSON));
        System.out.println(xummClient.getStorage());
        System.out.println(xummClient.deleteStorage());
        //KYC
        System.out.println(xummClient.getKycStatus("rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB"));
        //Get Transaction
        System.out.println(xummClient.getTransaction("DA66B07C9FE0876A3447DE4C57D565FC9C5324485912D10B48C0507F191A4021"));

        String jsonResponse = xummClient.getCuratedAssets();
        DeserializeIT deserialize = new DeserializeIT();
        //Other DAO are available under com.fl.xumm4j.dao.*
        CuratedAssetsDAO result = deserialize.CuratedAssets(jsonResponse);
        result.forEachCurrencies(System.out::println);
        result.forEachDetails(System.out::println);
        result.forEachIssuer(System.out::println);

        String Response = xummClient.getPayload("3211f3b4-9a4b-4c32-bbe7-ab52cfceedcb");
        System.out.println(Response);
        GetPayloadDAO resultTwo = deserialize.getPayload(Response);
        System.out.println(resultTwo.getResponse());

    }
}