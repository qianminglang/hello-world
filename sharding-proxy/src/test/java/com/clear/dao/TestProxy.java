package com.clear.dao;

import com.clear.RunBoot;
import com.clear.entity.Position;
import com.clear.repository.PositionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestProxy {
    @Resource
    private PositionRepository positionRepository;
    @Test
    @Repeat(value = 10)
    public void add(){
        Position position = new Position();
        position.setCity("lyg");
        position.setName("qml");
        positionRepository.save(position);
    }

    @Test
    public void findAll(){
        List<Position> all = positionRepository.findAll();
        for (Position position : all) {
            System.out.println(position);
        }
    }

}
