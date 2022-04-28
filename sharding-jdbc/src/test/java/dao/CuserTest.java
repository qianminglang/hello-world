package dao;

import com.clear.RunBoot;
import com.clear.repository.CuserRepository;
import com.clear.entity.CUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class CuserTest {

    @Resource
    private CuserRepository cuserRepository;
    @Test
    public void testAdd(){
        CUser cUser = new CUser();
        cUser.setName("qml");
        cUser.setPwd("abc");
        cuserRepository.save(cUser);
    }

    @Test
    public void findAll(){
        List<CUser> all = cuserRepository.findByPwd("abc");
        for (CUser cUser : all) {
            System.out.println(cUser);
        }

    }

}