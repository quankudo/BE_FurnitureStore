package com.furniture.store.dto.response;

import com.furniture.store.enums.ReactionType;

public interface ReactionSummaryResponse {
    ReactionType getType();
    Long getCount();
}
