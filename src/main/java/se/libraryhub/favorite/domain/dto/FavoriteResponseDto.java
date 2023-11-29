package se.libraryhub.favorite.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema
public class FavoriteResponseDto implements Comparable<FavoriteResponseDto>{

    private int favoriteCount;

    private boolean isLiked;

    @Builder
    public FavoriteResponseDto(int favoriteCount, boolean isLiked) {
        this.favoriteCount = favoriteCount;
        this.isLiked = isLiked;
    }

    @Override
    public int compareTo(FavoriteResponseDto o) {
        return Integer.compare(o.favoriteCount, this.favoriteCount);
    }
}
