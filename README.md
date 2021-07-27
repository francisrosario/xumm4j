# XUMM SDK (Java) WIP README

Interact with the XUMM SDK from Java environments.

#### **Please note! The xumm4j SDK (XUMM API in general) is for BACKEND USE only. Please DO NOT use your API credentials in a FRONTEND environment.**

<div class="alert alert-danger shadow-sm" style="color: #ca0000; border: 1px solid #ca0000; padding: 4px 6px; border-radius: 5px; background-color: rgba(200, 110, 50, .2)">To implement the XUMM SKD (or XUMM API directly) in your own web project, make sure your frontend calls your own backend, where the follow up
communication with the XUMM SDK (or XUMM API) will take place. Your XUMM credentials should never be publicly available.</div>

## How to use the xumm4j SDK

Import required class.
```java
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.sdk.XummClient;
import com.fl.xumm4j.sdk.Deserialize
```

Now continue by creating an instance of CredentialsBuilder, XummClient and Deserialize:
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

The XummClient will look in your CredentialsBuilder Object, It's highly recommended not to hard-code the API Key and Secret Key, You may use https://docs.oracle.com/javase/tutorial/essential/environment/env.html **Environment Variables** to hide your API Key and Secret Key.

Create your app and get your XUMM API credentials at the XUMM Developer Console:

- https://apps.xumm.dev

More information about the XUMM API, payloads, the API workflow, sending Push notifications, etc. please check the XUMM API Docs: 

- https://xumm.readme.io/docs


### Methods & params (+ samples)

After creating an instance of XummClient and Deserialize, you can call the methods:

- `xummclient.*` for the helper methods; Along with methods to get/update payloads for users to sign.
- `deserialize.*` for JSON deserialization.

Please note all snippets below assume you created an instance of the XummClient into the `xummclient` object name and Deserialize into the `deserialize` object name, as the [How to use the xumm4j](#how-to-use-the-xumm-sdk) section outlines.

#### XummClient methods

##### xummclient.doPing()

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

##### xummclient.getCuratedAssets()

The `getCuratedAssets` method allows you to get the list of trusted issuers and IOU's. This is the same list used to
populate the "Add Asset" button at the XUMM home screan.

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

##### Sdk.getKycStatus()

The `getKycStatus` return the KYC status of a user based on a user_token, issued after the
user signed a Sign Request (from your app) before (see Payloads - Intro).

If a user token specified is invalid, revoked, expired, etc. the method will always
return `NONE`, just like when a user didn't go through KYC. You cannot distinct a non-KYC'd user
from an invalid token.

Alternatively, KYC status can be retrieved for an XPRL account address: the address selected in
XUMM when the session KYC was initiated by.

```typescript
const kycStatus = await Sdk.getKycStatus('00000000-0000-0000-0000-000000000000')
```

... or using an account address:
```typescript
const kycStatus = await Sdk.getKycStatus('rwu1dgaUq8DCj3ZLFXzRbc1Aco5xLykMMQ')
```

Returns [`<keyof PossibleKycStatuses>`](https://github.com/XRPL-Labs/XUMM-SDK/blob/master/src/types/Meta/KycStatusResponse.ts#L1).

###### Notes on KYC information

- Once an account has successfully completed the XUMM KYC flow, the KYC flag will be applied to the account even if the identity document used to KYC expired. The flag shows that the account was **once** KYC'd by a real person with a real identity document.
- Please note that the KYC flag provided by XUMM can't be seen as a "all good, let's go ahead" flag: it should be used as **one of the data points** to determine if an account can be trusted. There are situations where the KYC flag is still `true`, but an account can no longer be trusted. Eg. when account keys are compromised and the account is now controlled by a 3rd party. While unlikely, depending on the level of trust required for your application you may want to mitigate against these kinds of fraud.

##### xummclient.getTransaction()

The `getTransaction` method allows you to get the transaction outcome (mainnet)
live from the XRP ledger, as fetched for you by the XUMM backend.

**Note**: it's best to retrieve these results **yourself** instead of relying on the XUMM platform to get live XRPL transaction information! You can use the **[xrpl-txdata](https://www.npmjs.com/package/xrpl-txdata)** package to do this:  
[![npm version](https://badge.fury.io/js/xrpl-txdata.svg)](https://www.npmjs.com/xrpl-txdata)

```java
String JSON = xummclient.getTransaction();
```

Returns [`<String>`](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html):

#### App Storage

App Storage allows you to store a JSON object at the XUMM API platform, containing max 60KB of data.
Your XUMM APP storage is stored at the XUMM API backend, meaning it persists until you overwrite or delete it.

This data is private, and accessible only with your own API credentials. This private JSON data can be used to store credentials / config / bootstrap info / ... for your headless application (eg. POS device).

```java
String storageSet =  xummclient.setStorage("{name: 'Francis', age: 22, male: true}")
System.out.println(storageSet);

String storageGet = xummclient.getStorage()
System.out.println(storageGet)

String storageDelete = await Sdk.storage.delete()
System.out.println(storageDelete)

```

#### Payloads

##### Intro

Payloads are the primary reason for the XUMM API (thus this SDK) to exist. The [XUMM API Docs explain '**Payloads**'](https://xumm.readme.io/docs/introduction) like this:

>  An XRPL transaction "template" can be posted to the XUMM API. Your transaction tample to sign (so: your "sign request") will be persisted at the XUMM API backend. We now call it a  a **Payload**. XUMM app user(s) can open the Payload (sign request) by scanning a QR code, opening deeplink or receiving push notification and resolve (reject or sign) on their own device.

A payload can contain an XRPL transaction template. Some properties may be omitted, as they will be added by the XUMM app when a user signs a transaction. A simple payload may look like this:

```javascript
{
  txjson: {
    TransactionType : 'Payment',
    Destination : 'rwiETSee2wMz3SBnAG8hkMsCgvGy9LWbZ1',
    Amount: '1337'
  }
}
```

As you can see the payload looks like a regular XRPL transaction, wrapped in an `txjson` object, omitting the mandatory `Account`, `Fee` and `Sequence` properties. They will be added containing the correct values when the payload is signed by an app user.

Optionally (besides `txjson`) a payload can contain these properties ([TS definition](https://github.com/XRPL-Labs/XUMM-SDK/blob/d2aae98eb8f496f4d77079c777aa41df754d4ebc/src/types/xumm-api/index.ts#L79)):

- `options` to define payload options like a return URL, expiration, etc.
- `custom_meta` to add metadata, user insruction, your own unique ID, ...
- `user_token` to push the payload to a user (after [obtaining a user specific token](https://xumm.readme.io/docs/pushing-sign-requests))

A more complex payload [could look like this](https://gist.github.com/WietseWind/ecdfd58bece14e5d15e41138fa4b0f4a). A [reference for payload options & custom meta](https://xumm.readme.io/reference/post-payload) can be found in the [API Docs](https://xumm.readme.io/reference/post-payload).

Instead of providing a `txjson` transaction, a transaction formatted as HEX blob (string) can be provided in a `txblob` property.

##### Sdk.payload.get

```typescript
async Sdk.payload.get (
  payload: string | CreatedPayload,
  returnErrors: boolean = false
): Promise<XummPayload | null>
```

To get payload details, status and if resolved & signed: results (transaction, transaction hash, etc.) you can `get()` a payload.

Note! Please don't use _polling_! The XUMM API offers Webhooks (configure your Webhook endpoint in the [Developer Console](https://apps.xumm.dev)) or use [a subscription](#payload-subscriptions-live-updates) to receive live payload updates (for non-SDK users: [Webhooks](https://xumm.readme.io/docs/payload-status)).

You can `get()` a payload by:

- Payload UUID  
  ```javascript
  const payload = await Sdk.payload.get('aaaaaaaa-bbbb-cccc-dddd-1234567890ab')
  ```

- Passing a created Payload object (see: [Sdk.payload.create](#sdkpayloadcreate))  
  ```javascript
  const newPayload: XummTypes.CreatedPayload = {txjson: {...}}
  const created = await Sdk.payload.create(newPayload)
  const payload = await Sdk.payload.get(created)
  ```

If a payload can't be fetched (eg. doesn't exist), `null` will be returned, unless a second param (boolean) is provided to get the SDK to throw an Error in case a payload can't be retrieved:

```javascript
await Sdk.payload.get('aaaaaaaa-bbbb-cccc-dddd-1234567890ab', true)
```

##### Sdk.payload.create

```typescript
async Sdk.payload.create (
  payload: CreatePayload,
  returnErrors: boolean = false
): Promise<CreatedPayload | null>
```

To create a payload, a `txjson` XRPL transaction can be provided. Alternatively, a transaction formatted as HEX blob (string) can be provided in a `txblob` property. **See the [intro](#intro) for more information about payloads.** Take a look at the [Developer Docs for more information about payloads](https://xumm.readme.io/docs/your-first-payload).

The response (see: [Developer Docs](https://xumm.readme.io/docs/payload-response-resources)) of a `Sdk.payload.create()` operation, a `<CreatedPayload>` object, looks like this:

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

The `next.always` URL is the URL to send the end user to, to scan a QR code or automatically open the XUMM app (if on mobile). If a `user_token` has been provided as part of the payload data provided to `Sdk.payload.create()`, you can see if the payload has been pushed to the end user. A button "didn't receive a push notification" could then take the user to the `next.no_push_msg_received` URL. The 

Alternatively user routing / instruction flows can be custom built using the QR information provided in the `refs` object, and a subscription for live status updates (opened, signed, etc.) using a WebSocket client can be setup by conneting to the `refs.websocket_status` URL. **Please note: this SDK already offers subscriptions. There's no need to setup your own WebSocket client, see [Payload subscriptions: live updates](#payload-subscriptions-live-updates).** There's more information about the [payload workflow](https://xumm.readme.io/docs/payload-workflow) and a [paylaod lifecycle](https://xumm.readme.io/docs/doc-payload-life-cycle) in the Developer Docs.

##### Sdk.payload.cancel

```typescript
async Sdk.payload.cancel (
  payload: string | XummPayload | CreatedPayload,
  returnErrors: boolean = false
): Promise<DeletedPayload | null>
```

To cancel a payload, provide a payload UUID (string), a `<XummPayload>` (by performing a `Sdk.payload.get()` first) or a `<CreatedPayload>` (by using the response of a `Sdk.payload.create()` call). By cancelling an existing payload, the payload will be marked as expired and can no longer be opened by users. 

**Please note**: *if a user already opened the payload in XUMM APP, the payload cannot be cancelled: the user may still be resolving the payload in the XUMM App, and should have a chance to complete that process*.

A response (generic API types [here](https://github.com/XRPL-Labs/XUMM-SDK/blob/master/src/types/xumm-api/index.ts)) looks like:
```javascript
{
  result: {
    cancelled: boolean
    reason: XummCancelReason
  }
  meta: XummPayloadMeta
  custom_meta: XummCustomMeta
}
```

##### Sdk.payload.createAndSubscribe

```typescript
async Sdk.payload.createAndSubscribe (
    payload: CreatePayload,
    callback?: onPayloadEvent
  ): Promise<PayloadAndSubscription>
```

The [`<PayloadAndSubscription>`](https://github.com/XRPL-Labs/XUMM-SDK/blob/master/src/types/Payload/PayloadAndSubscription.ts) object is basically a [`<PayloadSubscription>`](https://github.com/XRPL-Labs/XUMM-SDK/blob/master/src/types/Payload/PayloadSubscription.ts) object with the created payload results in the `created` property:

All information that applies on [`Sdk.payload.create()`](#sdkpayloadcreate) and [`Sdk.payload.subscribe()`](#sdkpayloadsubscribe) applies. Differences are:

1. The input for a `Sdk.payload.createAndSubscribe()` call isn't a payload UUID / existing payload, but a paykiad to create. 
2. The response object also contains (`<PayloadAndSubscription>.created`) the response obtained when creating the payload

## Debugging (logging)

The XUMM SDK will emit debugging info when invoked with a debug environment variable configured like: `DEBUG=xumm-sdk*` 

You'll see the XUMM SDK debug messages if you invoke your script instead of this:

```
node myApp.js
```

like this:

```
DEBUG=xumm-sdk* node myApp.js
```

## Development

Please note: you most likely just want to **use** the XUMM SDK, to do so, fetch the `xumm-sdk` package from NPM using `npm install --save xumm-sdk`.

If you actually want to change/test/develop/build/contribute (to) the source of the XUMM SDK:

##### Build

Please note: at least Typescript version **3.8+** is required!

To build the code, run `tsc`. To build automatically on file changes (watch): `tsc -w`.

##### Lint & test

Lint the code using `npm run lint`, run tests (jest) using `npm run test`

##### Run development code:

Build, run, show debug output & watch `dist/samples/dev.js`, compiled from `samples/dev.ts` using `npm run dev`. The `samples/dev.ts` file is **not included by default**.

[Here's a sample `samples/dev.ts` file](https://gist.github.com/WietseWind/e2e9729619872cb736fe29b486e9c623).
