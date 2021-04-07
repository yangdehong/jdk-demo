package com.ydh.redsheep.mapstruct;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * bean拷贝
 *
 * @author : yangdehong
 * @date : 2021/4/6 10:12
 */
@Mapper
//@Mapper(componentModel="spring") // 使用spring的依赖注入
public interface PersonConverter {

    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    @Mappings({
            @Mapping(source = "birthday", target = "birth"),
            @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
            @Mapping(source = "user.age", target = "age"),
            @Mapping(target = "email", ignore = true)
    })
    PersonDTO domain2dto(Person person);

    List<PersonDTO> domain2dto(List<Person> people);

    // 比如在 PersonConverter 里面加入如下，@InheritConfiguration 用于继承刚才的配置
    @InheritConfiguration(name = "domain2dto")
    void update(Person person, @MappingTarget PersonDTO personDTO);

}
