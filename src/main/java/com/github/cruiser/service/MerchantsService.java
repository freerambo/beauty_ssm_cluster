package com.github.cruiser.service;

import com.github.cruiser.entity.Merchant;
import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.Upstream;

import java.util.List;

public interface MerchantsService extends CommonResourceService<Merchant> {

    List<Route> getRouteEntityByMerchantId(long id, int limit, int offset);

    Route getRouteEntityByRouteId(long upstreamId, long routeId);

    List<Merchant> queryBySelective(String merchantCode, String merchantType,
                                    String bossCertificateNumber,
                                    String settlementAccount, String salerCode,
                                    int limit, int offset);

    String queryMerchantCodeByUpstreamAliasThirdMerchantCode(String upstreamAlias,
                                                             String thirdMerchantCode);

    Long queryMerchantIdByUpstreamAliasThirdMerchantCode(String upstreamAlias,
                                                 String thirdMerchantCode);
}
