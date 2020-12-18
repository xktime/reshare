package com.xktime.model.util;

import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dos.VerifyDo;
import com.xktime.model.article.dtos.c2s.VerifyDto;
import org.springframework.beans.BeanUtils;

public class TransferUtils {

    public static com.xktime.model.article.dos.LoadDo toDO(com.xktime.model.article.dtos.c2s.LoadDto dto) {
        if (dto == null) {
            return new com.xktime.model.article.dos.LoadDo();
        }
        com.xktime.model.article.dos.LoadDo loadDo = new com.xktime.model.article.dos.LoadDo();
        BeanUtils.copyProperties(dto, loadDo);
        return loadDo;
    }

    public static VerifyDo toDO(VerifyDto dto) {
        if (dto == null) {
            return new VerifyDo();
        }
        VerifyDo verifyDo = new VerifyDo();
        BeanUtils.copyProperties(dto, verifyDo);
        return verifyDo;
    }

    public static com.xktime.model.comment.dos.LoadDo toDO(com.xktime.model.comment.dtos.c2s.LoadDto dto) {
        if (dto == null) {
            return new com.xktime.model.comment.dos.LoadDo();
        }
        com.xktime.model.comment.dos.LoadDo loadDo = new com.xktime.model.comment.dos.LoadDo();
        BeanUtils.copyProperties(dto, loadDo);
        return loadDo;
    }
}
