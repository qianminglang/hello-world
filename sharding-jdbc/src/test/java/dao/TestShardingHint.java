package dao;

import com.clear.RunBoot;
import com.clear.dao.CityRepository;
import com.clear.entity.City;
import org.apache.shardingsphere.api.hint.HintManager;
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
public class TestShardingHint {


    @Resource
    CityRepository cityRepository;

    @Test
    public void test1(){
        HintManager hintManager = HintManager.getInstance();
        hintManager.setDatabaseShardingValue(1L); //强制路由到ds${xx%2}
        List<City> list = cityRepository.findAll();
        list.forEach(city->{
            System.out.println(city.getId()+" "+city.getName()+" "+city.getProvince());
        });
    }




}
