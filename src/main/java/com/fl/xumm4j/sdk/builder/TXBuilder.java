package com.fl.xumm4j.sdk.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xrpl4j.model.jackson.ObjectMapperFactory;
import com.fl.xumm4j.api.ITXBuilder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class TXBuilder {
    private String json;

    public String getGeneratedPayload() {
        return json;
    }

    public static class builder implements ITXBuilder {
        private boolean submit = ITXBuilder.DEFAULT_SUBMIT_TRANSACTION;
        private boolean multisign = ITXBuilder.DEFAULT_MULTISIGN;
        private double expire = ITXBuilder.DEFAULT_EXPIRE;
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
            if (!txblob.equals("") && !txjson.equals("")) {
                throw new IllegalStateException(ITXBuilder.ERROR_AMBIGUOUS_PAYLOAD);
            }
            if (txblob.equals("") && txjson.equals("")) {
                throw new IllegalStateException(ITXBuilder.ERROR_MISSING_PROPERTIES);
            }
        }

        @Override
        public TXBuilder build(){
            return getPayloadBuilder();
        }

        @NotNull
        private TXBuilder getPayloadBuilder() {
            final StringBuilder sb = new StringBuilder();
            final ObjectMapper objectMapper = ObjectMapperFactory.create();
            final JSONObject main = new JSONObject();
            final JSONObject Option = new JSONObject();
            final JSONObject return_url = new JSONObject();
            final JSONObject custom_meta = new JSONObject();
            final TXBuilder payload = new TXBuilder();

            validate();
            try {
                if (!user_token.equals("")) main.put(ITXBuilder.KEY_USER_TOKEN, user_token);
                if (!txblob.equals("")) main.put(ITXBuilder.KEY_TX_BLOB, txblob);

                Option.put(ITXBuilder.KEY_SUBMIT, submit);
                Option.put(ITXBuilder.KEY_MULTISIGN, multisign);
                Option.put(ITXBuilder.KEY_EXPIRE, expire);

                if (!returnURL_Web.equals("") || !returnURL_App.equals("")) {
                    if (!returnURL_App.equals("")) return_url.put(ITXBuilder.KEY_APP, returnURL_App);
                    if (!returnURL_Web.equals("")) return_url.put(ITXBuilder.KEY_WEB, returnURL_Web);
                    Option.put(ITXBuilder.KEY_RETURN_URL, return_url);
                }

                main.put(ITXBuilder.KEY_OPTIONS, Option);

                if (!identifier.equals("") || !blob.equals("") || !instruction.equals("")) {
                    if (!identifier.equals("")) custom_meta.put(ITXBuilder.KEY_IDENTIFIER, identifier);
                    if (!blob.equals("")) custom_meta.put(ITXBuilder.KEY_BLOB, blob);
                    if (!instruction.equals("")) custom_meta.put(ITXBuilder.KEY_INSTRUCTION, instruction);
                    main.put(ITXBuilder.KEY_CUSTOM_META, custom_meta);
                }

                String manipulateJSON = objectMapper.readTree(main.toString()).toPrettyString();
                if (!txjson.equals("") && txblob.equals("")) {
                    String[] JSONParts = manipulateJSON.split("\n");
                    for (int x = 1; x < JSONParts.length; x++) sb.append(JSONParts[x]);
                    String stageOne = sb.toString();
                    sb.setLength(0);
                    String stageTwo;
                    sb.append("{");
                    sb.append("\""+ ITXBuilder.KEY_TXJSON +"\":");
                    sb.append(txjson);
                    sb.append(",");
                    stageTwo = sb.toString();
                    payload.json = objectMapper.readTree(stageTwo + stageOne).toPrettyString();
                }
            }catch(IllegalStateException | JsonProcessingException err){
                err.printStackTrace();
            }
            return payload;
        }

    }
    private TXBuilder() {

    }
}
