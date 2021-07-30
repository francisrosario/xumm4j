package com.fl.xumm4j.api;

import com.fl.xumm4j.dao.*;

public interface IDeserialize {
    PingDAO Ping(String json);

    CuratedAssetsDAO CuratedAssets(String json);

    CuratedAssetsDAO.Details Details(String json);

    CuratedAssetsDAO.Details.Currencies Currencies(String json);

    StorageDAO Storage(String json);

    KycStatusDAO.Public KycPublic(String json);

    KycStatusDAO KycState(String json);

    GetPayloadDAO getPayload(String json);

    PostPayloadDAO Payload(String json);

    RatesDAO Rates(String json);

    DeletePayloadDAO deletePayload(String json);
}
