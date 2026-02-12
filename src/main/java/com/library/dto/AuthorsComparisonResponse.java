package com.library.dto;

import java.util.List;

public record AuthorsComparisonResponse(
        List<AuthorResponse> nPlusOne,
        List<AuthorResponse> fetchJoin,
        List<AuthorResponse> entityGraph
) {
}
