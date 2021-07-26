package com.fl.xumm4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.jackson.CuratedAssets;
import com.fl.xumm4j.sdk.Misc;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.sdk.builder.PayloadBuilder;
import com.fl.xumm4j.api.IPayloadBuilder;
import org.xrpl.xrpl4j.model.fl.jackson.ObjectMapperFactory;
import org.xrpl.xrpl4j.model.fl.transactions.*;

import java.math.BigDecimal;

public class testMain {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = ObjectMapperFactory.create();
        // Don't worry about this key being exposed... I built it for testing. keys not being used in production env.
        CredentialsBuilder myAccess = new CredentialsBuilder.builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("6dab854e-b317-47f7-8453-490b8bd171ad")
                .build();
        Misc misc = new Misc(myAccess);

        Payment payment = Payment.builder()
                .fee(XrpCurrencyAmount.ofDrops(12))
                .destination(Address.of("ra5nK24KXen9AHvsdFTKHSANinZseWnPcX"))
                .amount(XrpCurrencyAmount.ofXrp(BigDecimal.valueOf(8787)))
                .build();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        PayloadBuilder payload = new PayloadBuilder.builder()
                .txjson(IPayloadBuilder.TXJSON_SIGNIN)
                .instruction("This is a login transaction.")
                .build();

        //System.out.println("Generated Payload: \n" + payload.getGeneratedPayload());
        //String Payload = payload.getGeneratedPayload();
        //System.out.println(Payload+"\n");
        //System.out.println(misc.postPayload(Payload));

        //System.out.println(misc.getCuratedAssets());
        //System.out.println(misc.doPing());
        //System.out.println(misc.deserializeCuratedAssets(jsonResponse));

        //System.out.println(misc.deserializePing(jsonResponse).getUuidv4());
        //System.out.println(misc.getRates("sar"));
        //System.out.println(misc.getKycStatus("rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB"));
        //System.out.println(misc.getTransaction("DA66B07C9FE0876A3447DE4C57D565FC9C5324485912D10B48C0507F191A4021"));

        String jsonResponse = misc.getCuratedAssets();
        CuratedAssets result = misc.deserializeCuratedAssets(jsonResponse);
        result.forEachCurrencies(System.out::println);
        result.forEachDetails(System.out::println);
        result.forEachIssuer(System.out::println);

        String Issuer = result.getIssuer(0);
        System.out.println(Issuer);

    }
}
