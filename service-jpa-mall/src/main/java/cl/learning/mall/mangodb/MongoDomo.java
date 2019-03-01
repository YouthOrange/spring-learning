package cl.learning.mall.mangodb;

import cl.learning.mall.domain.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author : 常亮
 * @Date : 16:42 2019-02-22
 * @Description :
 */
public interface MongoDomo extends MongoRepository<Goods,Long> {



}
