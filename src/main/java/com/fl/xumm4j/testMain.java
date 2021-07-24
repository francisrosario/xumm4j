package com.fl.xumm4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.Sdk.builder.Credentials;
import com.fl.xumm4j.Sdk.Misc;
import com.fl.xumm4j.Sdk.builder.Payload;
import org.json.JSONObject;
import org.xrpl.xrpl4j.model.fl.jackson.ObjectMapperFactory;
import org.xrpl.xrpl4j.model.fl.transactions.*;

import java.math.BigDecimal;

public class testMain {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = ObjectMapperFactory.create();
        // Don't worry about this key being exposed... I built it for testing. keys not being used in production env.
        Credentials myAccess = new Credentials.builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("6dab854e-b317-47f7-8453-490b8bd171ad")
                .build();
        Misc misc = new Misc(myAccess);

        Payment payment = Payment.builder()
                .fee(XrpCurrencyAmount.ofDrops(12))
                .destination(Address.of("ra5nK24KXen9AHvsdFTKHSANinZseWnPcX"))
                .amount(XrpCurrencyAmount.ofXrp(BigDecimal.valueOf(8787)))
                .build();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payment);

        Payload payload = new Payload.builder()
                .txjson(json)
                .instruction("ZUPP...")
                .returnURL_App("www.google.com")
                .user_token("My Token")
                .expire(75)
                .identifier("Identify me 12345")
                .instruction("Instruct me")
                .returnURL_Web("www.twitter.com")
                .build();

        //System.out.println("Generated Payload: \n" + payload.getGeneratedPayload());
        String GenPayload = payload.getGeneratedPayload();
        System.out.println(GenPayload);
        System.out.println(misc.postPayload(GenPayload));

        //System.out.println(misc.getCurratedAssets());
        //System.out.println(misc.doPing());
        //System.out.println(misc.getRates("sar"));
        //System.out.println(misc.getKycStatus("rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB"));
        //System.out.println(misc.getTransaction("3661492C20982D84B53594C06C79C9AF93EA3FC8CFB27506CEDC4B3ECD1FBCB3"));
    }
}
