<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../theme/taglibs.jsp"%>
<ubf:PanelGroup name="PG_Main" cell="4" type="tab" visiable="1" showtitle="1">
	<ubf:Panel name="Txt_INCContentTitle" label="工单信息" visiable="1">
		<ubf:CharacterField name="BaseSummary" label="主题" row="1" cell="4" visiable="1" length="2000" />
		<ubf:SelectField name="BaseCity" label="地市" type="sysdic" resource="BaseCity" paras="" cell="1" visiable="1"   />
		<ubf:SelectField name="BaseCountry" label="区县" type="sysdic" resource="BaseCity" paras="" cell="1" visiable="1"   />
		<ubf:TimeField name="effectDate" label="生效日期" row="1" cell="1" visiable="1" format="yyyy-MM-dd"  />
		<ubf:CharacterField name="siteName" label="站址名称" row="1" cell="1" visiable="1" length="500" />
		<ubf:ViewField name="billList" label="起租表信息" row="5" cell="4" type="frame" visiable="1" url="${ctx}/omcs/baseinfo/tower_rent_sheet/wfDetail.jsp?bill_code=${displayCxt.fieldMap.bill_code}&fromUrl=wf"/>
		<ubf:TimeField name="BaseDealOutTime" label="处理时限" row="1" cell="1" visiable="1" format="yyyy-MM-dd HH:mm:ss"  />
		<ubf:TimeField name="BaseAcceptOutTime" label="受理时限" row="1" cell="1" visiable="1" format="yyyy-MM-dd HH:mm:ss"  />
		<ubf:HiddenField name="bill_code" label="起租表ID" cell="1" length="500" />
		<ubf:AttachmentField name="AttachmentsList" label="附件列表" types="*.*" visiable="1">
			<atta:fileupload id="AttachmentsList_tag"
		 		uploadBtnIsVisible="false" fileTypes="${AttachmentsList_types}" uploadable="${AttachmentsList_uploadable}"
				progressIsVisible="true" uploadTableVisible="true" isMultiDownload="true" isAutoUpload="true" downTableVisible="true" isMultiUpload="true"
				attchmentGroupId="${AttachmentsList_relcationCode}" operationParams="0,${AttachmentsList_edit},1" uploadDestination="${path}"
		      displayAllAtt="self" baseSchema="WF4_EL_SHARE_ADD" baseID="${AttachmentsList_baseID}" 
		      templateID=""
				flashUrl="${ctx}/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.swf" >
			</atta:fileupload>
		</ubf:AttachmentField>
	</ubf:Panel>
	<ubf:Panel name="DealDescPanelField" label="流程记录" visiable="1">
		<ubf:ViewField name="DealInfoViewField"  row="30" cell="4" type="frame" visiable="1" url="${ctx}/workflow/sheet/query/BaseInfoViewBpp.jsp?baseschema=${displayCxt.baseSchema}&baseid=${displayCxt.baseID}&tplid=${displayCxt.defCode}"/>
	</ubf:Panel>
</ubf:PanelGroup>
