package com.xktime.model.behavior.dtos;


import com.xktime.model.article.pojos.Article;
import lombok.Data;

import java.util.List;

@Data
public class ShowBehaviorDto {

    Integer equipmentId;
    List<Article> articleIds;

}
