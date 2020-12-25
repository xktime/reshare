package com.xktime.crawler;

import com.xktime.model.ReshareModelApplication;
import com.xktime.model.pojo.comment.query.LoadQuery;
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
        LoadQuery loadQuery = new LoadQuery();
        loadQuery.setPage(1);
        loadQuery.setPageStartIndex(1);
        loadQuery.setSize(1);
        commentMapper.load(loadQuery);
    }
}
