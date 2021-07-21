package com.fl.xumm4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.Sdk.Credentials;
import com.fl.xumm4j.Sdk.Misc;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;
import org.xrpl.xrpl4j.model.jackson.ObjectMapperFactory;
import org.xrpl.xrpl4j.model.transactions.*;

import java.math.BigDecimal;

public class testMain {

    public static void main(String[] args) throws Exception {

        Credentials myAccess = new Credentials.Builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("fa44ffc6-d59f-44ef-89ce-dc4528da442c")
                .build();
        Misc misc = new Misc(myAccess);

        Payment payment = Payment.builder()
                .account(Address.of("dsadsadsadass"))
                .sequence(UnsignedInteger.valueOf(23))
                .fee(XrpCurrencyAmount.of(UnsignedLong.valueOf(32)))
                .destination(Address.of("asddasdad"))
                .amount(XrpCurrencyAmount.ofXrp(BigDecimal.valueOf(8787)))
                .build();

        System.out.println(payment);
        ObjectMapper objectMapper = new ObjectMapper();

        String transactionJson = objectMapper.readTree(objectMapper.writeValueAsString(payment)).toPrettyString();
        System.out.println(transactionJson);


        try{

        }catch (IllegalStateException ignored){

        }

        //System.out.println(misc.getCurratedAssets());
        //System.out.println(misc.doPing());
        //System.out.println(misc.getRates("sar"));
        //System.out.println(misc.getKycStatus("rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB"));
        //System.out.println(misc.getTransaction("3661492C20982D84B53594C06C79C9AF93EA3FC8CFB27506CEDC4B3ECD1FBCB3"));
    }
}
