package com.xktime.crawler;

import com.xktime.model.ReshareModelApplication;
import com.xktime.model.comment.dos.LoadDo;
import com.xktime.model.mappers.comment.CommentMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReshareModelApplication.class)
@RunWith(SpringRunner.class)
public class CommonTest {

    @Autowired
    CommentMapper commentMapper;

    @org.junit.Test
    public void test() {
        LoadDo loadDo = new LoadDo();
        loadDo.setPage(1);
        loadDo.setPageStartIndex(1);
        loadDo.setSize(1);
        commentMapper.load(loadDo);
    }
}
