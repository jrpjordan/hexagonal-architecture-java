package com.joser.domain.valueobject;

import java.util.UUID;

public class RouterId {

    private final UUID id;

    private RouterId(UUID id) {
        this.id = id;
    }

    public static RouterId withUUID(String id) {
        return new RouterId(UUID.fromString(id));
    }

    public static RouterId withoutUUID() {
        return new RouterId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RouterId routerId = (RouterId) obj;
        return id.equals(routerId.id);
    }

}
