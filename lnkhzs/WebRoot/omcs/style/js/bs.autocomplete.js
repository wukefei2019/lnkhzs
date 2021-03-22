$(document).ready(autocomplete_helper_ready);
		
		
function autocomplete_helper_ready(){
    $(".bs-autocomplete").attr("autocomplete","off");
    
    // 科室（部门）选择
    autocomplete_helper.init($(".bs-autocomplete.wg_bs_dep"),{
        id:"bs_autocomplete_wg_bs_dep",
        columns: ["NAME"],
        sqlname: "SQL_ORG_DepList.query",
        win_url: $ctx + '/organize/dep/selectWin.action',
        matchInfo: "NAME",
        relation: {
        },
        effect:{
            "PID":$(".bs-autocomplete-effect.dep-pid")
        }
    });
    
    // 人员选择
    autocomplete_helper.init($(".bs-autocomplete.wg_bs_user"),{
        id:"bs_autocomplete_wg_bs_user",
        columns: ["USERNAME"],
        sqlname: "SQL_ORG_UserList.query",
        win_url: $ctx + '/organize/user/selectWin.action',
        matchInfo: "USERNAME",
        relation: {
        },
        effect:{
        	"MOBILE":$(".bs-autocomplete-effect.mobile"),
            "LOGINNAME":$(".bs-autocomplete-effect.loginname")
            
        }
    });



}