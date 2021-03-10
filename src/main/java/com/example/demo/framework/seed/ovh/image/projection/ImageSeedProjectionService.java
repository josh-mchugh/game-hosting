package com.example.demo.framework.seed.ovh.image.projection;

import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageResponse;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageSeedProjectionService implements IImageSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyImageResponse existsAny(ExistsAnyImageQuery query) {

        QImageEntity qImage = QImageEntity.imageEntity;

        long count = queryFactory.select(qImage.id)
                .from(qImage)
                .fetchCount();

        return new ExistsAnyImageResponse(count >= 1);
    }
}
