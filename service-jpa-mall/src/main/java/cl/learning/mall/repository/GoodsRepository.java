package cl.learning.mall.repository;

import cl.learning.mall.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 16:00 2019-01-14
 * @Description :
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long>, JpaSpecificationExecutor<Goods> {

    List<Goods> findByTypeOrOrderById(String type);

    List<Goods> findByNameContainsOrTypeOrderById(String key, String type);

}
