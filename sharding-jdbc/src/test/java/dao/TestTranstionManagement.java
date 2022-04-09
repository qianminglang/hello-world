package dao;

import com.clear.RunBoot;
import com.clear.repository.PositionDetailRepository;
import com.clear.repository.PositionRepository;
import com.clear.entity.Position;
import com.clear.entity.PositionDetail;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestTranstionManagement {

    @Resource
    PositionRepository positionRepository;

    @Resource
    PositionDetailRepository positionDetailRepository;

    @Test
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void test() {
        for (int i = 0; i <= 3; i++) {
            Position position = new Position();
//            position.setId(i);
            position.setName("lagou" + i);
            position.setSalary(String.valueOf(i));
            position.setCity(String.valueOf(i));


            positionRepository.save(position);

            if (i == 3) {
                throw new RuntimeException("人为错误");
            }


            PositionDetail positionDetail = new PositionDetail();
            positionDetail.setPid(position.getId());
            positionDetail.setDescription("职位描述" + i);
            positionDetailRepository.save(positionDetail);
        }
    }
}
