# XUMM SDK (Java)

Interact with the XUMM API from Java environments.

#### **Please note! The xumm4j SDK (XUMM API in general) is for BACKEND USE only. Please DO NOT use your API credentials in a FRONTEND environment.**

<div class="alert alert-danger shadow-sm" style="color: #ca0000; border: 1px solid #ca0000; padding: 4px 6px; border-radius: 5px; background-color: rgba(200, 110, 50, .2)">To implement the xumm4j SDK (or XUMM API directly) in your web project, make sure your frontend calls your backend, where the follow-up
communication with the xumm4j SDK (or XUMM API) will take place. Your XUMM credentials should never be publicly available.</div>

## How to use the xumm4j SDK

Import required class.
```java
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.sdk.XummClient;
import com.fl.xumm4j.sdk.Deserialize;
```

Now continue by creating an instance of CredentialsBuilder, XummClient, and Deserialize:
```java
CredentialsBuilder myAccess = new CredentialsBuilder.builder()
  .apiKey("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
  .secretKey("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
  .build();

//Pass the created CredentialsBuilder object to XummClient.
XummClient xummclient = new XummClient(myAccess);

//Use the instance of Deserialize for Deserializing JSON response.
Deserialize deserialize = new Deserialize();
```

### Credentials

The XummClient will look in your CredentialsBuilder Object, and It's highly recommended not to hard-code the API Key and Secret Key, You may use https://docs.oracle.com/javase/tutorial/essential/environment/env.html **Environment Variables** to hide your API Key and Secret Key.

Create your app and get your XUMM API credentials at the XUMM Developer Console:

- https://apps.xumm.dev

More information about the XUMM API, payloads, the API workflow, sending Push notifications, etc. please check the XUMM API Docs: 

- https://xumm.readme.io/docs


### Methods & params (+ samples)

For more information about the XUMM API, payloads, the API workflow, sending Push notifications, etc., please check the XUMM API Docs: 

- `xummclient.*` for the helper methods; Along with methods to get/update payloads for users to sign.
- `deserialize.*` for JSON deserialization.

Please note all snippets below assume you created an instance of the XummClient into the `xummclient` object name and Deserialize into the `deserialize` object name, as the [How to use the xumm4j](#how-to-use-the-xumm-sdk) section outlines.

#### XummClient methods

##### xummclient.doPing

The `ping` method allows you to verify API access (valid credentials) and returns some info on your XUMM APP:

```java
String JSON = xummclient.doPing();
```

Returns [`<String>`](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html):
```java
System.out.println(JSON);
/*
{
  quota: {},
  application: {
    uuidv4: '00000000-1111-2222-3333-aaaaaaaaaaaa',
    name: 'My XUMM APP',
    webhookurl: '',
    disabled: 0
  },
  call: { uuidv4: 'bbbbbbbb-cccc-dddd-eeee-111111111111' }
}
*/
```

##### xummclient.getCuratedAssets

The `getCuratedAssets` method allows you to get the list of trusted issuers and IOU. This is the same list used to
populate the "Add Asset" button at the XUMM home screen.

```java
String JSON = xummclient.getCuratedAssets();
```

Returns [`<String>`](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html):
```java
System.out.println(JSON);
/*
{
  curatedAssets: {
    issuers: [ 'Bitstamp', 'GateHub' ],
    currencies: [ 'USD', 'BTC', 'EUR', 'ETH' ],
    details: {
      Bitstamp: [Object],
      GateHub: [Object]
    }
  }
}
*/
```

##### xummclient.getKycStatus

The `getKycStatus` return the KYC status of a user based on a user_token, issued after the user signed a Sign Request (from your app) before (see Payloads - Intro).

If a user token specified is invalid, revoked, expired, etc., the method will always
return `NONE`, just like when a user didn't go through KYC. This is because you cannot see distinct a non-KYC'd user from an invalid token.

Alternatively, KYC status can be retrieved for an XPRL account address: the address selected in XUMM when KYC was initiated.

```java
String JSON = xummclient.getKycStatus("00000000-0000-0000-0000-000000000000");
```

... or using an account address:
```java
String JSON = xummclient.getKycStatus("wu1dgaUq8DCj3ZLFXzRbc1Aco5xLykMMQ")
```

Returns [`<String>`](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html):
###### Notes on KYC information

- Once an account has completed the XUMM KYC flow, the KYC flag will be applied to the account even if the identity document used to KYC expired. The flag shows that the account was **once** KYC'd by a real person with an actual identity document.
- Please note that the KYC flag provided by XUMM can't be seen as a "all good, let's go ahead" flag: it should be used as **one of the data points** to determine if an account can be trusted. There are situations where the KYC flag is still `true`, but an account can no longer be trusted. Eg. when account keys are compromised and a 3rd party now controls the account. While unlikely, depending on the level of trust required for your application you may want to mitigate against these kinds of fraud.

##### xummclient.getTransaction

The `getTransaction` method allows you to get the transaction outcome (mainnet)
live from the XRP ledger, as fetched for you by the XUMM backend.

```java
String JSON = xummclient.getTransaction("DA66B07C9FE0876A3447DE4C57D565FC9C5324485912D10B48C0507F191A4021");
```

Returns [`<String>`](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html):

#### App Storage

App Storage allows you to store a JSON object at the XUMM API platform, containing a max of 60KB of data.
Your XUMM APP storage is stored at the XUMM API backend, meaning it persists until you overwrite or delete it.

This data is private and accessible only with your API credentials. This private JSON data can be used to store credentials/config/bootstrap info / ... for your headless application (e.g., POS device).

```java
String storageSet =  xummclient.setStorage("{name: 'Francis', age: 22, male: true}");
System.out.println(storageSet);
// true

String storageGet = xummclient.getStorage();
System.out.println(storageGet)
// {name: 'Francis', age: 22, male: true}

String storageDelete = xummclient.deleteStorage();
System.out.println(storageDelete)
// true

String storageGetAfterDelete = xummclient.getStorage();
System.out.println(storageGetAfterDelete)
// null

```

#### Payloads

##### Intro

Payloads are the primary reason for the XUMM API (thus, this SDK) to exist. The [XUMM API Docs explain '**Payloads**'](https://xumm.readme.io/docs/introduction) like this:

>  An XRPL transaction "template" can be posted to the XUMM API. Your transaction template to sign (so: your "sign request") will be persisted at the XUMM API backend. We now call it a **Payload**. XUMM app user(s) can open the Payload (sign request) by scanning a QR code, opening a deep link, or receiving push notifications and resolve (reject or sign) on their device.

A payload can contain an XRPL transaction template. Some properties may be omitted, as they will be added by the XUMM app when a user signs a transaction. A simple payload may look like this:

```java
/*
{
  txjson: {
    TransactionType : 'Payment',
    Destination : 'rwiETSee2wMz3SBnAG8hkMsCgvGy9LWbZ1',
    Amount: '1337'
  }
}
*/
```

As you can see, the payload looks like a regular XRPL transaction, wrapped in a `txjson` object, omitting the mandatory `Account`, `Fee` and `Sequence` properties. They will be added containing the correct values when an app user signs the payload.

Optionally (besides `txjson`) a payload can contain these properties ([XUMM API Postpayload](https://xumm.readme.io/reference/post-payload)):
- `options` to define payload options like a return URL, expiration, etc.
- `custom_meta` to add metadata, user insruction, your own unique ID, ...
- `user_token` to push the payload to a user (after [obtaining a user specific token](https://xumm.readme.io/docs/pushing-sign-requests))

Instead of providing a `txjson` transaction, a transaction formatted as HEX blob (string) can be provided in a `txblob` property.

##### xummclient.getPayload

To get payload details, status and if resolved & signed: results (transaction, transaction hash, etc.) you can `get()` a payload.

You can `get()` a payload by:

- Payload UUID  
  ```java
  String JSON = xummclient.getPayload('aaaaaaaa-bbbb-cccc-dddd-1234567890ab')
  ```

##### xummclient.postPayload + PayloadBuilder + xrp4j JSON Builder

Creating payload by using `PayloadBuilder` class and XRP4J JSON Builder.

Import the following class:
```java
import com.fl.xumm4j.sdk.builder.PayloadBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xrpl4j.model.jackson.ObjectMapperFactory;
import com.fl.xrpl4j.model.transactions.Address;
import com.fl.xrpl4j.model.transactions.Payment;
import com.fl.xrpl4j.model.transactions.XrpCurrencyAmount;
```

The following code constructs an Payment object, which represents an Payment Transaction:
```java
Payment payment = Payment.builder()
  .fee(XrpCurrencyAmount.ofDrops(12))
  .destination(Address.of("ra5nK24KXen9AHvsdFTKHSANinZseWnPcX"))
  .amount(XrpCurrencyAmount.ofXrp(BigDecimal.valueOf(8787)))
  .build();
```
These objects can be serialized to and deserialized from the rippled JSON representations using the provided Jackson `ObjectMapper`, which can be instantiated using ObjectMapperFactory.

Using the Payment object we just created, we can use the supplied ObjectMapper to serialize to JSON:
```java
ObjectMapper objectMapper = ObjectMapperFactory.create();
String JSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payment);
System.out.println(JSON);
/*
{
  "Fee" : "12",
  "Flags" : 2147483648,
  "Amount" : "8787000000",
  "Destination" : "ra5nK24KXen9AHvsdFTKHSANinZseWnPcX",
  "TransactionType" : "Payment"
}
*/
```

We now can now generate the payload object by using `PayloadBuilder` class:
```java
PayloadBuilder payload = new PayloadBuilder.builder()
  .txjson(JSON) //Pass the generated JSON Object.
  .build();
```

You can also generate payload by using all of the body properties:
```java
PayloadBuilder payloadTwo = new PayloadBuilder.builder()
  .userToken() //User (Push) token, to deliver a signing request directly to the mobile device of a user (Optional)
  .txjson(JSON) //Mandatory JSON transaction template to sign. Alternatively a HEX string could be posted in a txblob field.
  .txblob() // You can provide a HEX transaction template instead of a JSON formatted one here.
  .submit() // Should the xumm app submit to the XRPL after signing? (Optional)
  .multisign() // Should the transaction be signed as a multi sign transaction? (Optional)
  .expire() //After how many minutes should the payload expire? (Optional)
  .returnURL_App() //Smart device application return URL (Optional)
  .returnURL_Web() //Web (browser) return URL (Optional)
  .identifier() //Your own identifier for this payload. This identifier must be unique. If duplicate, an error code 409 will be returned (max 40 positions)
  .blob() //A custom JSON object containing metadata, attached to this specific payload (stringified max 1500 positions)
  .instruction() //A message (instruction, reason for signing) to display to the XUMM (signing) user (max 280 positions)
  .build();
```
By using xrp4j JSON Builder + xumm4j PayloadBuilder we now have a payload object ready to be submmited using `postPayload` method.

```java
String JSON = xummclient.postPayload(payload.getGeneratedPayload());
//The getter method getGeneratedPayload will retrieve the generated payload object from the `PayloadBuilder`
```
Once `postPayload` method is executed you'll get a response similar below: 

More information regarding payload **For more information about payloads.** Take a look at the [Developer Docs for more information about payloads](https://xumm.readme.io/docs/your-first-payload).

```json
{
  "uuid": "1289e9ae-7d5d-4d5f-b89c-18633112ce09",
  "next": {
    "always": "https://xumm.app/sign/1289e9ae-7d5d-4d5f-b89c-18633112ce09",
    "no_push_msg_received": "https://xumm.app/sign/1289e9ae-7d5d-4d5f-b89c-18633112ce09/qr"
  },
  "refs": {
    "qr_png": "https://xumm.app/sign/1289e9ae-7d5d-4d5f-b89c-18633112ce09_q.png",
    "qr_matrix": "https://xumm.app/sign/1289e9ae-7d5d-4d5f-b89c-18633112ce09_q.json",
    "qr_uri_quality_opts": [ "m", "q", "h" ],
    "websocket_status": "wss://xumm.app/sign/1289e9ae-7d5d-4d5f-b89c-18633112ce09"
  },
  "pushed": true
}
```

The `next.always` URL is the URL to send the end-user to, scan a QR code or automatically open the XUMM app (if on mobile). If a `user_token` has been provided as part of the payload data provided to `postPayload()`, you can see if the payload has been pushed to the end user. A button "didn't receive a push notification" could then take the user to the `next.no_push_msg_received` URL.

Alternatively, user routing/instruction flows can be custom-built using the QR information provided in the `refs` object. There's more information about the [payload workflow](https://xumm.readme.io/docs/payload-workflow) and a [paylaod lifecycle](https://xumm.readme.io/docs/doc-payload-life-cycle) in the Developer Docs.

##### xummclient.deletePayload
```java
String JSON = xummclient.deletePayload("00000000-1111-2222-3333-aaaaaaaaaaaa");
```

To cancel a payload, provide a payload UUID (string), By performing a `xummclient.getPayload();` first) or a `<CreatedPayload>` (by using the response of a `xummclient.postPayload();` call). By canceling an existing payload, the payload will be marked as expired and can no longer be opened by users. 

**Please note**: *if a user already opened the payload in XUMM APP, the payload cannot be canceled: the user may still be resolving the payload in the XUMM App and should have a chance to complete that process*.


```java
System.out.println(JSON);
/*
{
  "result": {
    "cancelled": true,
    "reason": "OK"
  },
  "meta": {
    "exists": true,
    "uuid": "<some-uuid>",
    "multisign": false,
    "submit": true,
    "destination": "rPEPPER7kfTD9w2To4CQk6UCfuHM9c6GDY",
    "resolved_destination": "XRP Tip Bot",
    "finished": false,
    "expired": true,
    "pushed": true,
    "app_opened": false,
    "return_url_app": "<some-url-or-null>",
    "return_url_web": "<some-url-or-null>"
  },
  "custom_meta": {
    "identifier": "some_identifier_1337",
    "blob": {},
    "instruction": "Hey ❤️ ..."
  }
}
*/
```
