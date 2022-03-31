package dao;

import com.clear.RunBoot;
import com.clear.dao.PositionRepository;
import com.clear.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//注解的意义在于Test测试类要使用注入的类，比如@Autowired注入的类，
@RunWith(SpringRunner.class)
//通过RunBoot来创建容器
@SpringBootTest(classes = RunBoot.class)
public class TestShardingDatabase {

    @Resource
    PositionRepository positionRepository;

    @Test
    public void testAdd() {
        for (int i = 1; i < 20; i++) {
            Position position = new Position();
//            position.setId(i);
            position.setName("lagou" + i);
            position.setSalary(String.valueOf(i));
            position.setCity(String.valueOf(i));
            positionRepository.save(position);
        }
    }
}
