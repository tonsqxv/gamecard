package com.macower.core.dao;

import java.io.Serializable;

public abstract interface IEntity extends Serializable, Comparable<Object>
{
  public abstract Long getId();

  public abstract String getCode();
}
