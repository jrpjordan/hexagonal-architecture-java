package com.joser.application.usecases;

import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;

public interface RouterNetworkUseCase {

    Router addNetworkToRouter(RouterId routerId, Network network);

    Router getRouter(RouterId routerId);
}
