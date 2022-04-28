package dao;

import com.clear.RunBoot;
import com.clear.repository.CityRepository;
import com.clear.entity.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

//注解的意义在于Test测试类要使用注入的类，比如@Autowired注入的类，
@RunWith(SpringRunner.class)
//通过RunBoot来创建容器
@SpringBootTest(classes = RunBoot.class)
public class TestShardingMasterSlave {


    @Resource
    CityRepository cityRepository;

    @Test
    public void add() {
        City city = new City();
        city.setName("shanghai");
        city.setProvince("shanghai");
        cityRepository.save(city);
    }


    @Test
    public void masterSlave() {
        List<City> all = cityRepository.findAll();
        for (City city : all) {
            System.out.println(city);
        }
    }

}
