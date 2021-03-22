var setting = {
    view : {
        showIcon : false
    },
    data : {
        simpleData : {
            enable : true
        }
    },
    check : {
        enable : true,
        chkboxType : {
            "Y" : "",
            "N" : ""
        }
    },
    callback : {
        onCheck : onCheck,
        onClick : onClick
    }
};

$(document).ready(function() {
	if(single == "1"){
		var check = {
		        enable: true,
		        chkStyle: "radio",  //单选框
		        radioType: "all"   //对所有节点设置单选
		    }
			setting.check=check;
			
	}
	
    $.post($ctx + "/deptree/ajaxGetDeptTree.action", {
        // "bean.name":"中国移动通信集团辽宁有限公司"
        /*"bean.pid" : "57fd43e13aa0418db1f4b19a17280bf5",*/
        "bean.parent" : true
    }).done(function(entities) {
        for (var i = 0; i < entities.length; i++) {
            var entity = entities[i];

            if ($.inArray(entity.id,depid) > -1) {
                entity.checked = true;
            }
        }
        $.fn.zTree.init($("#treeDemo"), setting, entities);
        // 已选获取部门名称
        for (var i = 0; i < depid.length; i++) {
            for (var j = 0; j < entities.length; j++) {
                var entity = entities[j];
                if(depid[i] == entity.id){
                    depname.push(entity.name)
                }
            }
        }
        $("#depid").val(depid.join(","));
        $("#depname").val(depname.join(","));
    });
    

});
function onCheck(e, treeId, node) {
    var zTree = $.fn.zTree.getZTreeObj(treeId), checkNodes = zTree.getCheckedNodes(true);
    var depid = [];
    var depname = [];
    for ( var i in checkNodes) {
        depid.push(checkNodes[i].id);
        depname.push(checkNodes[i].name);
    }
    $("#depid").val(depid.join(","));
    $("#depname").val(depname.join(","));
}

function onClick(e, treeId, node) {
    var $A = $(e.target);
    if(e.target.tagName != "A"){
        $A = $A.parents("A");
    }
    $A.prev(".button.chk").click()
}

