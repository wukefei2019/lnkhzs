<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../theme/taglibs.jsp" %>
<ubf:SelectField name="AreaSelect" label="区域选择" type="collect" resource="A区:A区,B区:B区" paras="" cell="1" visiable="1" defaultValue="A区" />
<ubf:AssignTreeField name="DealPerson" label="处理人" row="1" cell="1" single="1"  stepno="step01" selectType="2"  actionName="SubCompany" next="1" visiable="1" />
<ubf:AssignTreeField name="Atreefield" label="A区处理人" row="1" cell="1" single="1"  stepno="step01" selectType="2"  actionName="SubCompany" next="0" visiable="1" />
<ubf:AssignTreeField name="Btreefield" label="B区处理人" row="1" cell="1" single="1"  stepno="step01" selectType="2"  actionName="SubCompany" next="0" visiable="1" />
