package com.fl.xumm4j.api;

import com.fl.xumm4j.dao.*;

public interface IDeserialize {
    PingDAO Ping(String json);

    CuratedAssetsDAO CuratedAssets(String json);

    DetailsDAO Details(String json);

    CurrenciesDAO Currencies(String json);

    StorageDAO Storage(String json);

    KycPublicDAO KycPublic(String json);

    KycStateDAO KycState(String json);
}
