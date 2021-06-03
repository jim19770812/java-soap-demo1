package com.example.soap.demo1.client;

import lombok.NonNull;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class SoapResult {
    private Object[] objects;
    public SoapResult(@NonNull Object[] objects) {
        this.objects=objects;
    }

    /**
     * 获得首个字符串结果
     * @return
     */
    public Optional<String> firstString(){
        Optional<Object> ret=firstObject();
        return ret.filter(o -> o instanceof String).map(o -> (String) o);
    }

    /**
     * 获取首个对象结果
     * @return
     */
    public Optional<Object> firstObject(){
        if (this.objects.length==0){
            return Optional.empty();
        }
        return Objects.nonNull(this.objects[0])?Optional.of(this.objects[0]):Optional.empty();
    }

    /**
     * 获取对象数组结果
     * @return
     */
    public Optional<Object[]> objects(){
        return Objects.nonNull(this.objects)?Optional.of(this.objects):Optional.empty();
    }

    /**
     * 获取流
     * @return
     */
    public Stream<?> stream(){
        Assert.notNull(this.objects, String.format("[%s].stream()传入的数组是空，无法创建成流", this.getClass().getSimpleName()));
        return Arrays.stream(this.objects);
    }
}
