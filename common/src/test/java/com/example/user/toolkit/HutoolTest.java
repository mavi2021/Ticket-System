package com.example.user.toolkit;

import cn.hutool.core.bean.BeanUtil;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

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

    @Test
    public void testBeanCopyProperties(){
        C c = C.builder()
                .id(1)
                .name("XIAOXIAO")
                .home("123")
                .hobbies("basketball").build();
        D d = new D();
        BeanUtil.copyProperties(c, d);
        System.out.println(d);
    }

    @Test
    public void testBeanUtilEmpty(){
        B b = B.builder().build();
        boolean empty = BeanUtil.isEmpty(b.getId());
        System.out.println(empty);
    }

}
@Builder
@Data
class B{
    @Getter
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
