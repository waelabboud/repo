/*
 * Copyright
 * ------------------------------------------------------------------------
 * (C) Copyright 2002-2006, Tangoe, Inc.
 *
 * Tangoe retains all ownership rights to this source code. No
 * warranty is expressed or implied by Tangoe, if Tangoe grants
 * the right to use or re-use this source code.
 * ------------------------------------------------------------------------
 
 VERSION 2
 
 */

package com.tangoe.cmp.common.business.beans;

import java.util.HashMap;

import com.tangoe.common.beans.ITangoeBeanFactory;
import com.tangoe.common.beans.SpringBeanFactory;

/**
 * Singleton class for obtaining an ITangoeBeanFactory instance
 * that will load CMP beans.
 *
 * @author Doug Benham
 */
public class CmpBeanFactory
{
  /** String identifier for the core application context. */
  public static final String CMP_CORE_APP_CONTEXT = "cmpBean-context";
  /** String identifier for the core Dao context. */
  public static final String CMP_CORE_DAO_CONTEXT = "cmpBean-context";

  private static String dfltContextIdent = CMP_CORE_APP_CONTEXT;
  private static HashMap contexts = new HashMap();

  /**
   * Gets an instance of an ITangoeBeanFactory using the current default
   * context identifier.
   *
   * @return the ITangoeBeanFactory
   */
  public static ITangoeBeanFactory getInstance()
  {
    return getInstance(dfltContextIdent);
  }

  /**
   * Gets an instance of an ITangoeBeanFactory using the passed
   * context identifier.
   *
   * @param ctxtIdent context identifier to get a bean factory for.
   * @return the ITangoeBeanFactory
   */
  public static ITangoeBeanFactory getInstance(String ctxtIdent)
  {
    ITangoeBeanFactory theFactory = (ITangoeBeanFactory)contexts.get(ctxtIdent);
    if (theFactory == null)
    {
      theFactory = createBeanFactory(ctxtIdent);
      contexts.put(ctxtIdent, theFactory);
    }

    return theFactory;
  }

  /**
   * Sets the identifier to be used for the default context.
   * @param ident the context identifying String
   */
  public static void setDefaultContextIdentifier(String ident)
  {
    dfltContextIdent = ident;
  }

  /**
   * Gets the current default context identifier.
   * @return the current default context identifying String
   */
  public static String getDefaultContextIdentifier()
  {
    return dfltContextIdent;
  }

  /**
   * Creates an ITangoeBeanFactory using the passed identifier.
   * @param ctxtIdent the context identifier
   * @return the created ITangoeBeanFactory
   */
  protected static ITangoeBeanFactory createBeanFactory(String ctxtIdent)
  {
    String baseUrl = System.getProperty("tangoe.spring.config.location.base-url");
    String fullUrl = baseUrl + "/cmpApplicationContexts.xml";
    return new SpringBeanFactory(fullUrl, ctxtIdent);
  }

  /** Protected constructor to prevent instance instantiation. */
  protected CmpBeanFactory()
  {
  }
}
