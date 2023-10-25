package com.example.user.toolkit;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @create 2023/10/25 19:59
 */
public class HutoolTest {
    @Builder
    @Data
    static class C{
        private Integer id;
        private String name;
        private String home;
        private String hobbies;
    }

    @Data
    static class D{
        private Integer id;
        private String name;
        private String home;
        private String parents;
    }
    public static void main(String[] args) {
        C c = C.builder()
                .id(1)
                .name("XIAOXIAO")
                .home("123")
                .hobbies("basketball").build();
        D d = BeanUtil.copyProperties(c, D.class);
        System.out.println(d);
    }
}
@Builder
@Data
class B{
    private Integer id;
    private String name;
    private String home;
    private String hobbies;
}

@Data
class A{
    private Integer id;
    private String name;
    private String home;
    private String parents;
}
