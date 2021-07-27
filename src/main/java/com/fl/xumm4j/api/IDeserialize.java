package com.fl.xumm4j.api;

import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.CurrenciesDAO;
import com.fl.xumm4j.dao.DetailsDAO;
import com.fl.xumm4j.dao.PingDAO;

public interface IDeserialize {
    PingDAO Ping(String json);

    CuratedAssetsDAO CuratedAssets(String json);

    DetailsDAO Details(String json);

    CurrenciesDAO Currencies(String json);
}
