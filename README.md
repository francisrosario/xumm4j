xumm4j
=====

### What is xumm4j?
xumm4j is a wrapper for the XUMM API written in Java. Project started by Francis Mico A. Rosario.

### Current state of xumm4
Currently, xumm4j is still in the development stage where some features of it are still work-in-progress. nightly builds will be released.

### What can it do?
xumm4j interacts with XUMM API. It can do almost anything similar to xumm sdk (javascript) https://www.npmjs.com/package/xumm-sdk.

### How to use xumm4j?
```java
// Create an instance of CredentialBuilder, This is where XUMM API Key and SecretKey are stored.
// Secret Key and XUMM API is available in https://apps.xumm.dev/
CredentialsBuilder myAccess = new CredentialsBuilder.builder()
      .apiKey("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
      .secretKey("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
      .build();

// Interact with https://xumm.readme.io/ (Misc)
// Pass the instance of CredentialBuilder to Misc.
Misc misc = new Misc(myAccess);

//Performs ping request https://xumm.readme.io/reference/testinput
String pingJSON = misc.doPing();

//Performs curarated assets request https://xumm.readme.io/reference/curated-assets
String curratedJSON = misc.getCuratedAssets());
CuratedAssetsDAO  = misc.deserializeCuratedAssets(curratedJSON);
curatedAssetsDAO.forEachCurrencies(System.out::println);
curatedAssetsDAO.forEachDetails(System.out::println);
curatedAssetsDAO.forEachIssuer(System.out::println);
//Other delegates methods are available such as ArrayList size, add, get.

//Create JSON using modified xrp4j model.
Payment payment = Payment.builder()
      .fee(XrpCurrencyAmount.ofDrops(12))
      .destination(Address.of("ra5nK24KXen9AHvsdFTKHSANinZseWnPcX"))
      .amount(XrpCurrencyAmount.ofXrp(BigDecimal.valueOf(8787)))
      .build();
ObjectMapper objectMapper = ObjectMapperFactory.create();
String PaymentJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payment);

//Create a txjson using PayloadBuilder
PayloadBuilder payload = new PayloadBuilder.builder()
      .txjson(IPayloadBuilder.TXJSON_SIGNIN)
      .instruction("Hi!!")
      .expire(50)
      .identifier("My Identifier")
      .multisign(false)
      .returnURL_Web("https://github.com/francisrosario/xumm4j")
      .returnURL_App("https://github.com/francisrosario/")
      .build();

//Temporary stored postPayload method in com.fl.xumm4j.sdk.Misc
String Result = misc.postPayload(Payload);
```
