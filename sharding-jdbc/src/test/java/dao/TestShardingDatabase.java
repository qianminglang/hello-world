package dao;

import com.clear.RunBoot;
import com.clear.repository.BOrderRepository;
import com.clear.repository.CityRepository;
import com.clear.repository.PositionDetailRepository;
import com.clear.repository.PositionRepository;
import com.clear.entity.BOrder;
import com.clear.entity.City;
import com.clear.entity.Position;
import com.clear.entity.PositionDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

//注解的意义在于Test测试类要使用注入的类，比如@Autowired注入的类，
@RunWith(SpringRunner.class)
//通过RunBoot来创建容器
@SpringBootTest(classes = RunBoot.class)
public class TestShardingDatabase {

    @Resource
    PositionRepository positionRepository;

    @Resource
    PositionDetailRepository positionDetailRepository;

    @Resource
    CityRepository cityRepository;

    @Resource
    BOrderRepository bOrderRepository;

    @Test
    public void testAdd() {
        for (int i = 0; i < 20; i++) {
            Position position = new Position();
//            position.setId(i);
            position.setName("lagou" + i);
            position.setSalary(String.valueOf(i));
            position.setCity(String.valueOf(i));
            positionRepository.save(position);


            PositionDetail positionDetail = new PositionDetail();
            positionDetail.setPid(position.getId());
            positionDetail.setDescription("职位描述" + i);
            positionDetailRepository.save(positionDetail);
        }
    }

    @Test
    public void findById() {
        Object object = positionRepository.findPositionsById(716796791785259008L);
        Object[] object1 = (Object[]) object;
        System.out.println(object1[0] + " " + object1[1]);
    }

    @Test
    public void brocast() {
        City city = new City();
        city.setProvince("jiangsu");
        city.setName("lyg");
        cityRepository.save(city);
    }

    @Test
    @Repeat(100)
    public void BOrder(){
        Random random = new Random();
        int companyId = random.nextInt(10);
        BOrder order = new BOrder();
        order.setDel(false);
        order.setCompanyId(companyId);
        order.setPositionId(3242342);
        order.setUserId(2222);
        order.setPublishUserId(1111);
        order.setResumeType(1);
        order.setStatus("AUTO");
        order.setCreateTime(new Date());
        order.setOperateTime(new Date());
        order.setWorkYear("2");
        order.setName("lagou");
        order.setPositionName("Java");
        order.setResumeId(23233);
        bOrderRepository.save(order);
    }
}
