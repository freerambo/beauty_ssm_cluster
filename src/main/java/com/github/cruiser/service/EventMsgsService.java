package com.github.cruiser.service;

import com.github.cruiser.entity.EventMsg;
import java.util.List;

public interface EventMsgsService extends CommonResourceService<EventMsg> {

    List<EventMsg> getEntityListByOpenid(String userOpenid, int limit, int offset);

    List<EventMsg> getEntityListByOrderNumber(String orderNumber, int limit, int offset);

}
