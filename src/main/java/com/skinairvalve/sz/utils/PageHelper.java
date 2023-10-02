package com.skinairvalve.sz.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @create on 2023/10/2-2:16 PM
 */
public class PageHelper {
    public static <T, U> Page<U> convertPage(Page<T> page, Function<T, U> converter) {
        if (page == null) {
            return null;
        } else {
            Page<U> resultPage = new Page<>();
            BeanUtils.copyProperties(page, resultPage);
            resultPage.setRecords(page.getRecords()
                    .stream()
                    .map(converter)
                    .collect(Collectors.toList())
            );
            return resultPage;
        }
    }
}
