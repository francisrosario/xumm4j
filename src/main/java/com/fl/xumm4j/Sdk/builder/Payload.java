package com.fl.xumm4j.Sdk.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.xrpl.xrpl4j.model.fl.jackson.ObjectMapperFactory;


public class Payload {
    private String json;

    public String getGeneratedPayload() {
        return json;
    }

    public static class builder {
        JSONObject main = new JSONObject();
        JSONObject Option = new JSONObject();
        JSONObject return_url = new JSONObject();
        JSONObject custom_meta = new JSONObject();

        private String user_token = "";
        private String txjson = "";
        private String txblob = "";
        private boolean submit = true;
        private boolean multisign = false;
        private double expire = 60;
        private String returnURL_App = "";
        private String returnURL_Web = "";
        private String identifier = "";
        private String blob = "";
        private String instruction = "";

        public builder identifier(String identifier){
            this.identifier = identifier;
            return this;
        }

        public builder blob(String blob){
            this.blob = blob;
            return this;
        }

        public builder instruction(String instruction){
            this.instruction = instruction;
            return this;
        }

        public builder user_token(String user_token){
            this.user_token = user_token;
            return this;
        }

        public builder returnURL_App(String return_url){
            this.returnURL_App = return_url;
            return this;
        }

        public builder returnURL_Web(String return_url){
            this.returnURL_Web = return_url;
            return this;
        }

        public builder txjson(String json){
            this.txjson = json;
            return this;
        }

        public builder txblob(String blob){
            this.txblob = blob;
            return this;
        }

        public builder submit(boolean submit){
            this.submit = submit;
            return this;
        }

        public builder multisign(boolean multisign){
            this.multisign = multisign;
            return this;
        }

        public builder expire(double expire){
            this.expire = expire;
            return this;
        }

        public Payload build() throws JsonProcessingException {
            StringBuilder sb = new StringBuilder();
            Payload payload = new Payload();

            if(user_token != ""){
                main.put("user_token", user_token);
            }

            if(txblob != ""){
                main.put("txblob", txblob);
            }
            //Inner Option
            Option.put("submit", submit);
            Option.put("multisign", multisign);
            Option.put("expire", expire);

            if(!returnURL_Web.equals("") || !returnURL_App.equals("")){
                if(!returnURL_App.equals("")){
                    return_url.put("app", returnURL_App);
                }
                if(!returnURL_Web.equals("")){
                    return_url.put("web", returnURL_Web);
                }
                Option.put("return_url", return_url);

            }
            main.put("options", Option);

            if(!identifier.equals("") || !blob.equals("") || !instruction.equals("")){
                if(!identifier.equals("")){
                    custom_meta.put("identifier",identifier);
                }
                if(!blob.equals("")){
                    custom_meta.put("blob", blob);
                }
                if(instruction != ""){
                    custom_meta.put("instruction", instruction);
                }
                main.put("custom_meta", custom_meta);
            }

            ObjectMapper objectMapper = ObjectMapperFactory.create();
            String json = null;
            try {
                json = objectMapper.readTree(main.toString()).toPrettyString();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            if(!txjson.equals("") && txblob.equals("")){
                String[] JSONParts = json.split("\n");
                for(int x = 1; x < JSONParts.length; x++){
                    sb.append(JSONParts[x]);
                }
                String PartOne = sb.toString();
                sb.setLength(0);

                String PartTwo;
                sb.append("{");
                sb.append("\"txjson\":");
                sb.append(txjson);
                sb.append(",");
                PartTwo = sb.toString();
                payload.json = objectMapper.readTree(PartTwo + PartOne).toPrettyString();
            }else{
                payload.json = json;
            }
            return payload;
        }

    }
    private Payload() {

    }
}
