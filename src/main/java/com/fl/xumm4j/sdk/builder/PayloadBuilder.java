package com.fl.xumm4j.sdk.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.api.IPayloadBuilder;
import org.json.JSONObject;
import org.xrpl.xrpl4j.model.fl.jackson.ObjectMapperFactory;


public class PayloadBuilder {
    private String json;

    public String getGeneratedPayload() {
        return json;
    }

    public static class builder implements IPayloadBuilder {
        private boolean submit = IPayloadBuilder.SUBMIT_TRANSACTION_DEFAULT;
        private boolean multisign = IPayloadBuilder.MULTISIGN_DEFAULT;
        private double expire = IPayloadBuilder.EXPIRE_DEFAULT;
        private String user_token = "";
        private String txjson = "";
        private String txblob = "";
        private String returnURL_App = "";
        private String returnURL_Web = "";
        private String identifier = "";
        private String blob = "";
        private String instruction = "";

        @Override
        public builder identifier(String identifier){
            this.identifier = identifier;
            return this;
        }

        @Override
        public builder blob(String blob){
            this.blob = blob;
            return this;
        }

        @Override
        public builder instruction(String instruction){
            this.instruction = instruction;
            return this;
        }

        @Override
        public builder user_token(String user_token){
            this.user_token = user_token;
            return this;
        }

        @Override
        public builder returnURL_App(String return_url){
            this.returnURL_App = return_url;
            return this;
        }

        @Override
        public builder returnURL_Web(String return_url){
            this.returnURL_Web = return_url;
            return this;
        }

        @Override
        public builder txjson(String json){
            this.txjson = json;
            return this;
        }

        @Override
        public builder txblob(String blob){
            this.txblob = blob;
            return this;
        }

        @Override
        public builder submit(boolean submit){
            this.submit = submit;
            return this;
        }

        @Override
        public builder multisign(boolean multisign){
            this.multisign = multisign;
            return this;
        }

        @Override
        public builder expire(double expire){
            this.expire = expire;
            return this;
        }

        private void validate() {
            if (!txblob.equals("") && !txjson.equals("")) {
                throw new IllegalStateException(IPayloadBuilder.ERROR_AMBIGUOUS_PAYLOAD);
            }
            if (txblob.equals("") && txjson.equals("")) {
                throw new IllegalStateException(IPayloadBuilder.ERROR_MISSING_PROPERTIES);
            }
        }

        @Override
        public PayloadBuilder build(){
            StringBuilder sb = new StringBuilder();
            ObjectMapper objectMapper = ObjectMapperFactory.create();
            JSONObject main = new JSONObject();
            JSONObject Option = new JSONObject();
            JSONObject return_url = new JSONObject();
            JSONObject custom_meta = new JSONObject();

            PayloadBuilder payload = new PayloadBuilder();
            validate();
            try {
                if (!user_token.equals("")) {
                    main.put(IPayloadBuilder.USER_TOKEN, user_token);
                }

                if (!txblob.equals("")) {
                    main.put(IPayloadBuilder.TX_BLOB, txblob);
                }
                Option.put(IPayloadBuilder.SUBMIT, submit);
                Option.put(IPayloadBuilder.MULTISIGN, multisign);
                Option.put(IPayloadBuilder.EXPIRE, expire);
                if (!returnURL_Web.equals("") || !returnURL_App.equals("")) {
                    if (!returnURL_App.equals("")) {
                        return_url.put(IPayloadBuilder.APP, returnURL_App);
                    }
                    if (!returnURL_Web.equals("")) {
                        return_url.put(IPayloadBuilder.WEB, returnURL_Web);
                    }
                    Option.put(IPayloadBuilder.RETURN_URL, return_url);

                }
                main.put(IPayloadBuilder.OPTIONS, Option);
                if (!identifier.equals("") || !blob.equals("") || !instruction.equals("")) {
                    if (!identifier.equals("")) {
                        custom_meta.put(IPayloadBuilder.IDENTIFIER, identifier);
                    }
                    if (!blob.equals("")) {
                        custom_meta.put(IPayloadBuilder.BLOB, blob);
                    }
                    if (!instruction.equals("")) {
                        custom_meta.put(IPayloadBuilder.INSTRUCTION, instruction);
                    }
                    main.put(IPayloadBuilder.CUSTOM_META, custom_meta);
                }
                String manipulateMainJson = objectMapper.readTree(main.toString()).toPrettyString();
                if (!txjson.equals("") && txblob.equals("")) {
                    String[] JSONParts = manipulateMainJson.split("\n");
                    for (int x = 1; x < JSONParts.length; x++) {
                        sb.append(JSONParts[x]);
                    }
                    String PartOne = sb.toString();
                    sb.setLength(0);
                    String PartTwo;
                    sb.append("{");
                    sb.append("\""+IPayloadBuilder.TX_JSON+"\":");
                    sb.append(txjson);
                    sb.append(",");
                    PartTwo = sb.toString();
                    payload.json = objectMapper.readTree(PartTwo + PartOne).toPrettyString();
                }
            }catch(IllegalStateException | JsonProcessingException err){
                err.printStackTrace();
            }
            return payload;
        }

    }
    private PayloadBuilder() {

    }
}
