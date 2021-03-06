// Copyright 2017, Yahoo Holdings Inc.
// Licensed under the terms of the Apache License 2.0. Please see LICENSE file in project root for terms.
package com.yahoo.maha.service.config.dynamic

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * Created by panditsurabhi on 24/09/18.
  */
class DynamicConfigurationUtilsTest extends AnyFunSuite with Matchers {
  implicit val formats = org.json4s.DefaultFormats


  test("getDynamicFields should return all dynamic fields in the json") {
    val jsonStr = s"""{"key1": "val1", "key2": "<%(dynamic.val2,30000)%>"}"""
    val json = parse(jsonStr)
    val dynamicFields = DynamicConfigurationUtils.getDynamicFields(json)
    assert(dynamicFields.size == 1)
    assert("key2".equals(dynamicFields.apply(0)._1))
    assert(dynamicFields.apply(0)._2.extract[String].contains("dynamic.val2"))
    assert(DynamicConfigurationUtils.getDynamicFields(parse("""{"key":1}""")).size == 0)
  }

  test("extractDynamicFields should return all dynamic fields in the json") {
    val jsonStr = s"""{"key1": "val1", "key2": "<%(dynamic.val2,30000)%>"}"""
    val json = parse(jsonStr)
    val dynamicFields = DynamicConfigurationUtils.extractDynamicFields(json)
    assert(dynamicFields.size == 1)
    assert(dynamicFields("dynamic.val2")._1.equals("key2"), dynamicFields)
    assert(dynamicFields("dynamic.val2")._2.equals("30000"), dynamicFields)
  }

}