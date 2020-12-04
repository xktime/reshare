package com.xktime.model.util;

import com.xktime.model.article.dos.LoadDo;
import com.xktime.model.article.dos.VerifyDo;
import com.xktime.model.article.dtos.LoadDto;
import com.xktime.model.article.dtos.VerifyDto;
import org.springframework.beans.BeanUtils;

public class TransferUtils {

    public static LoadDo toDO(LoadDto dto) {
        if (dto == null) {
            return new LoadDo();
        }
        LoadDo loadDo = new LoadDo();
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
}
