package com.github.cruiser.service;

import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.Upstream;

import java.util.List;

public interface UpstreamsService extends CommonResourceService<Upstream> {

    List<Route> getRouteEntityByUpstreamId(long id, int limit, int offset);

    Route getRouteEntityByRouteId(long upstreamId, long routeId);

    List<Upstream> queryUpstreamByName(String upstreamName, int limit, int offset);

}
