package cl.learning.mall.service;

import cl.learning.base.dto.Paging;
import cl.learning.base.dto.Response;
import cl.learning.mall.domain.Goods;
import cl.learning.mall.dto.GoodsDto;
import cl.learning.mall.dto.GoodsQueryDto;
import cl.learning.mall.repository.GoodsRepository;
import cl.learning.mall.util.MallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Author : 常亮
 * @Date : 15:58 2019-01-14
 * @Description :
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

//    @Autowired
//    private MongoTemplate mongoTemplate;

//    public Response<Paging<Goods>> findAll(GoodsQueryDto queryDto) {
//        Pageable pageable = PageRequest.of(queryDto.getPageNo(), queryDto.getPageSize());
//        List<Goods> goodsList;
//        if (queryDto == null) {
//            goodsList = goodsRepository.findAll();
//        } else {
//            goodsList = goodsRepository.findByNameContainsOrTypeOrderById(queryDto.getKey(), queryDto.getType());
//        }
//        Paging<Goods> page = new Paging<>();
//        int start = (queryDto.getPageNo() - 1) * queryDto.getPageSize();
//        int end = Math.min(start + queryDto.getPageSize(), goodsList.size());
//        page.setTotal(goodsList.size());
//        page.setData(goodsList.subList(start, end));
//        return Response.ok(page);
//
//    }

    public Response<Paging<GoodsDto>> findAll(GoodsQueryDto queryDto) {
        Sort sort = Sort.unsorted();
        if (queryDto.getSortBy() != null) {
            sort = Sort.by(queryDto.getSortBy());
        }
        Pageable pageable = PageRequest.of(queryDto.getPageNo(), queryDto.getPageSize(), sort);
        Page<Goods> goodsPage = goodsRepository.findAll((Specification<Goods>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (queryDto.getKey() != null) {
                Predicate p1 = criteriaBuilder.like(root.get("name").as(String.class), "%" + queryDto.getKey() + "%");
                Predicate p2 = criteriaBuilder.like(root.get("type").as(String.class), "%" + queryDto.getKey() + "%");
                Predicate p3 = criteriaBuilder.like(root.get("origin").as(String.class), "%" + queryDto.getKey() + "%");
                list.add(criteriaBuilder.or(p1, p2, p3));
            }
            if (queryDto.getType() != null) {
                Predicate p = criteriaBuilder.equal(root.get("type").as(String.class), queryDto.getType());
                list.add(p);
            }
            if (queryDto.getOrigin() != null) {
                Predicate p = criteriaBuilder.equal(root.get("origin").as(String.class), queryDto.getOrigin());
                list.add(p);
            }
            if (queryDto.getBrand() != null) {
                Predicate p = criteriaBuilder.equal(root.get("brand").as(String.class), queryDto.getBrand());
                list.add(p);
            }
            if (queryDto.getMinPrice() != null) {
                Predicate p = criteriaBuilder.ge(root.get("price").as(Double.class), queryDto.getMinPrice());
                list.add(p);
            }
            if (queryDto.getMaxPrice() != null) {
                Predicate p = criteriaBuilder.lt(root.get("price").as(Double.class), queryDto.getMaxPrice());
                list.add(p);
            }
            Predicate[] predicates = list.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicates);
        }, pageable);
        List<Goods> goodsList = goodsPage.getContent();
        List<GoodsDto> goodsDtoList = MallUtil.goodsTranfer2Dto(goodsList);
        Integer totalPages = goodsPage.getTotalPages();
        Long totalElements = goodsPage.getTotalElements();
//        mongoTemplate.save(goodsPage);
        return Response.ok(new Paging<>(goodsDtoList, totalElements, totalPages));
    }

    public Response<Goods> saveGoods(Goods goods) {
        return Response.ok(goodsRepository.save(goods));
    }
}
