package com.shencode.ownwebplatform.module.condition.jackson;

import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.BeanDeserializer;
import org.codehaus.jackson.map.deser.BeanDeserializerFactory;
import org.codehaus.jackson.type.JavaType;

public class ExtendBeanDeserializerFactory extends BeanDeserializerFactory {
    public ExtendBeanDeserializerFactory() {
    }

    public JsonDeserializer<Object> createBeanDeserializer(DeserializationConfig config, DeserializerProvider p, JavaType type, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<Object> deserializer = super.createBeanDeserializer(config, p, type, property);
        return (JsonDeserializer)(deserializer instanceof BeanDeserializer ? new ExtendBeanDeserializer((BeanDeserializer)deserializer) : deserializer);
    }
}
