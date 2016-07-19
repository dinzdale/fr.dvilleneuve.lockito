package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class SimpleBeanPropertyDefinition
  extends BeanPropertyDefinition
{
  protected final AnnotationIntrospector _introspector;
  protected final AnnotatedMember _member;
  protected final String _name;
  
  @Deprecated
  public SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember)
  {
    this(paramAnnotatedMember, paramAnnotatedMember.getName(), null);
  }
  
  @Deprecated
  public SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, String paramString)
  {
    this(paramAnnotatedMember, paramString, null);
  }
  
  private SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, String paramString, AnnotationIntrospector paramAnnotationIntrospector)
  {
    _introspector = paramAnnotationIntrospector;
    _member = paramAnnotatedMember;
    _name = paramString;
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember)
  {
    String str = paramAnnotatedMember.getName();
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, str, paramMapperConfig);
    }
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, String paramString)
  {
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, paramString, paramMapperConfig);
    }
  }
  
  public AnnotatedMember getAccessor()
  {
    AnnotatedMethod localAnnotatedMethod = getGetter();
    Object localObject = localAnnotatedMethod;
    if (localAnnotatedMethod == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedParameter getConstructorParameter()
  {
    if ((_member instanceof AnnotatedParameter)) {
      return (AnnotatedParameter)_member;
    }
    return null;
  }
  
  public AnnotatedField getField()
  {
    if ((_member instanceof AnnotatedField)) {
      return (AnnotatedField)_member;
    }
    return null;
  }
  
  public PropertyName getFullName()
  {
    return new PropertyName(_name);
  }
  
  public AnnotatedMethod getGetter()
  {
    if (((_member instanceof AnnotatedMethod)) && (((AnnotatedMethod)_member).getParameterCount() == 0)) {
      return (AnnotatedMethod)_member;
    }
    return null;
  }
  
  public String getInternalName()
  {
    return getName();
  }
  
  public PropertyMetadata getMetadata()
  {
    return PropertyMetadata.STD_OPTIONAL;
  }
  
  public AnnotatedMember getMutator()
  {
    Object localObject2 = getConstructorParameter();
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = getSetter();
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = getField();
      }
    }
    return (AnnotatedMember)localObject1;
  }
  
  public String getName()
  {
    return _name;
  }
  
  public AnnotatedMember getNonConstructorMutator()
  {
    AnnotatedMethod localAnnotatedMethod = getSetter();
    Object localObject = localAnnotatedMethod;
    if (localAnnotatedMethod == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedMember getPrimaryMember()
  {
    return _member;
  }
  
  public AnnotatedMethod getSetter()
  {
    if (((_member instanceof AnnotatedMethod)) && (((AnnotatedMethod)_member).getParameterCount() == 1)) {
      return (AnnotatedMethod)_member;
    }
    return null;
  }
  
  public PropertyName getWrapperName()
  {
    if (_introspector == null) {
      return null;
    }
    return _introspector.findWrapperName(_member);
  }
  
  public boolean hasConstructorParameter()
  {
    return _member instanceof AnnotatedParameter;
  }
  
  public boolean hasField()
  {
    return _member instanceof AnnotatedField;
  }
  
  public boolean hasGetter()
  {
    return getGetter() != null;
  }
  
  public boolean hasSetter()
  {
    return getSetter() != null;
  }
  
  public boolean isExplicitlyIncluded()
  {
    return false;
  }
  
  public SimpleBeanPropertyDefinition withName(PropertyName paramPropertyName)
  {
    return withSimpleName(paramPropertyName.getSimpleName());
  }
  
  @Deprecated
  public SimpleBeanPropertyDefinition withName(String paramString)
  {
    return withSimpleName(paramString);
  }
  
  public SimpleBeanPropertyDefinition withSimpleName(String paramString)
  {
    if (_name.equals(paramString)) {
      return this;
    }
    return new SimpleBeanPropertyDefinition(_member, paramString, _introspector);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */