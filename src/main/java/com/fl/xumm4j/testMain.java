package com.fl.xumm4j;

import com.fl.xumm4j.Sdk.Credentials;
import com.fl.xumm4j.Sdk.Misc;

public class testMain {
    public static void main(String[] args) throws Exception {

        Credentials myAccess = new Credentials.Builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("fa44ffc6-d59f-44ef-89ce-dc4528da442c")
                .build();
        Misc misc = new Misc(myAccess);

        System.out.println(misc.getCurratedAssets());
        System.out.println(misc.doPing());
        System.out.println(misc.getRates("sar"));
        System.out.println(misc.getKycStatus("rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB"));
        System.out.println(misc.getTransaction("3661492C20982D84B53594C06C79C9AF93EA3FC8CFB27506CEDC4B3ECD1FBCB3"));
    }
}
