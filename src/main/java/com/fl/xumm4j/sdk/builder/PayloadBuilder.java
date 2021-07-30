package com.fl.xumm4j.sdk.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.api.builder.IPayloadBuilder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class PayloadBuilder {
    public static class builder implements IPayloadBuilder {
        private boolean submit = IPayloadBuilder.DEFAULT_SUBMIT_TRANSACTION;
        private boolean multisign = IPayloadBuilder.DEFAULT_MULTISIGN;
        private double expire = IPayloadBuilder.DEFAULT_EXPIRE;
        private String user_token;
        private String txjson;
        private String txblob;
        private String returnURL_App;
        private String returnURL_Web;
        private String identifier;
        private String blob;
        private String instruction;

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
        public builder userToken(String user_token){
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
            if (txblob != null && txjson != null) {
                throw new IllegalStateException(IPayloadBuilder.ERROR_AMBIGUOUS_PAYLOAD);
            }
            if (txblob == null && txjson == null) {
                throw new IllegalStateException(IPayloadBuilder.ERROR_MISSING_PROPERTIES);
            }
        }

        @Override
        public String build(){
            return getPayloadBuilder();
        }

        @NotNull
        private String getPayloadBuilder() {
            final StringBuilder sb = new StringBuilder();
            final ObjectMapper objectMapper = new ObjectMapper();
            final JSONObject main = new JSONObject();
            final JSONObject Option = new JSONObject();
            final JSONObject return_url = new JSONObject();
            final JSONObject custom_meta = new JSONObject();

            String mainPayload = null;
            validate();
            try {
                if (user_token != null) main.put(IPayloadBuilder.KEY_USER_TOKEN, user_token);
                if (txblob != null) main.put(IPayloadBuilder.KEY_TX_BLOB, txblob);

                Option.put(IPayloadBuilder.KEY_SUBMIT, submit);
                Option.put(IPayloadBuilder.KEY_MULTISIGN, multisign);
                Option.put(IPayloadBuilder.KEY_EXPIRE, expire);

                if (returnURL_Web != null || returnURL_App != null) {
                    if (returnURL_App != null) return_url.put(IPayloadBuilder.KEY_APP, returnURL_App);
                    if (returnURL_Web != null) return_url.put(IPayloadBuilder.KEY_WEB, returnURL_Web);
                    Option.put(IPayloadBuilder.KEY_RETURN_URL, return_url);
                }

                main.put(IPayloadBuilder.KEY_OPTIONS, Option);

                if (identifier != null || blob != null || instruction != null) {
                    if (identifier != null) custom_meta.put(IPayloadBuilder.KEY_IDENTIFIER, identifier);
                    if (blob != null) custom_meta.put(IPayloadBuilder.KEY_BLOB, blob);
                    if (instruction != null) custom_meta.put(IPayloadBuilder.KEY_INSTRUCTION, instruction);
                    main.put(IPayloadBuilder.KEY_CUSTOM_META, custom_meta);
                }

                String manipulateJSON = objectMapper.readTree(main.toString()).toPrettyString();
                if (txjson != null && txblob == null) {
                    String[] JSONParts = manipulateJSON.split("\n");
                    for (int x = 1; x < JSONParts.length; x++) sb.append(JSONParts[x]);
                    String stageOne = sb.toString();
                    sb.setLength(0);
                    String stageTwo;
                    sb.append("{");
                    sb.append("\""+ IPayloadBuilder.KEY_TXJSON +"\":");
                    sb.append(txjson);
                    sb.append(",");
                    stageTwo = sb.toString();
                    mainPayload = objectMapper.readTree(stageTwo + stageOne).toPrettyString();
                }
            }catch(IllegalStateException | JsonProcessingException err){
                err.printStackTrace();
            }
            assert mainPayload != null;
            return mainPayload;
        }

    }
    private PayloadBuilder() {

    }
}
