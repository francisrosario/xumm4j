package com.fl.xumm4j.api;

import com.fl.xumm4j.dao.*;

public interface IDeserialize {
    PingDAO Ping(String json);

    GetCuratedAssetsDAO CuratedAssets(String json);

    GetCuratedAssetsDAO.Details Details(String json);

    GetCuratedAssetsDAO.Details.Currencies Currencies(String json);

    StorageDAO Storage(String json);

    KycStatusDAO.Public KycPublic(String json);

    KycStatusDAO KycState(String json);
}
