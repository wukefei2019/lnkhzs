<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../theme/taglibs.jsp"%>
<ubf:PanelGroup name="PG_Main" cell="4" type="tab" visiable="1" showtitle="1">
	<ubf:Panel name="P_Main" label="基本信息" visiable="1">
		<ubf:CharacterField name="BaseSummay" label="工单主题" row="1" cell="4" visiable="1" length="200" />
		<ubf:SelectField name="BaseCity" label="地市" type="sysdic" resource="BaseCity" paras="" cell="1" visiable="1"   />
		<ubf:CharacterField name="BaseCityOrCompany" label="地市" row="1" cell="1" visiable="0" length="200" />
		<ubf:AttachmentField name="AttachmentsList" label="附件列表" types="*.*" visiable="1">
			<atta:fileupload id="AttachmentsList_tag"
		 		uploadBtnIsVisible="false" fileTypes="${AttachmentsList_types}" uploadable="${AttachmentsList_uploadable}"
				progressIsVisible="true" uploadTableVisible="true" isMultiDownload="true" isAutoUpload="true" downTableVisible="true" isMultiUpload="true"
				attchmentGroupId="${AttachmentsList_relcationCode}" operationParams="0,${AttachmentsList_edit},1" uploadDestination="${path}"
		      displayAllAtt="self" baseSchema="IC_TEST_APPROVAL" baseID="${AttachmentsList_baseID}" 
		      templateID=""
				flashUrl="${ctx}/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.swf" >
			</atta:fileupload>
		</ubf:AttachmentField>
	</ubf:Panel>
	<ubf:Panel name="DealDescPanelField" label="流程记录" visiable="1">
		<ubf:ViewField name="DealInfoViewField"  row="30" cell="4" type="frame" visiable="1" url="${ctx}/workflow/sheet/query/BaseInfoViewBpp.jsp?baseschema=${displayCxt.baseSchema}&baseid=${displayCxt.baseID}&tplid=${displayCxt.defCode}"/>
	</ubf:Panel>
</ubf:PanelGroup>
