(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3ec4fe66"],{5609:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAACWklEQVRoQ+2ZQVbCMBBAM4W6VddCd0rd6Qn0CHoC4QTiSdQTgCfQG/i8gTuK7oquha0V4iu0UkrTTJKJfTxlS5r5v8mkyQTYhv9gw/nZv0DVI/g3RuC1UT/Zf/t6+s23/bLnHh28R8+ymKUjMGzWzjg4PcZgZ9ER7/phdCvr1OT/wHMvGYObJN4Y+KzTGk0fRH0KBRbwtfv8g8B5vzWKOiaQomeHTbfHAdrrMafnIgmhQOC5H8s3v9qlDQkRfDoSfhjtFokXCgwa9VNwnMeyt0wpUQ6/oHCm/LgoJ0pGYIvLpgmFBAY+5vDDz0LWsinUZQyubUpg4RnjV34YJYmdm85lgIOm2weACxsSWHjO+d3hKFpL7JRJ+iGzIUEFH0tIBeJGlBKU8GgBKglqeCUBUwkb8MoCuhK24LUEVCXi9kXbg/zKJlttlPdCsqUTm9iyfuaCkqWydEeACSBqQyFhAq89hbJCJhKm8CQCKjmRFaeAJxNQlaCCJxXALpXzoISHItRWQpboKvA/mzAiCWMBHXhKCSMBE3gqCW0BCngKCS0BLHy82syT1tKhSGsVUoFPT1LYj53O6qQ0Ajrw6TSxJYEWMIG3KYESoIC3JSEVoIS3ISEp7hbXKikOI1Q5YVzYMtmYYSW0CluBJy8tmsCrTiel0iKmuEsBryKhUdx1x4zBdtFOlBIeJ8EnfhgllyyrRMoXHDbgZRLANS444k6TK6b+ciTEVWLZmQH7f+C5mao4nwCftbWumLIBsRduWEhMO2xM6YcME6zKNv8CVb59rfNA1cD5+Bs/hb4Bw4nmQA/pn2EAAAAASUVORK5CYII="},c91a:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticStyle:{"margin-right":"10px","margin-left":"15px"}},[s("div",{staticClass:"q-row",staticStyle:{"margin-top":"8px"}},[s("span",[t._v("问题：\n        "),s("div",{staticClass:"btn-group",staticStyle:{"background-color":"white",float:"right","margin-right":"5px"}},[s("button",{staticClass:"btn btn-Default",staticStyle:{"border-color":"#ccc","background-color":"white",height:"10px","line-height":"1px","font-size":"10px"},attrs:{type:"button",id:"showSelectedIsAnswer"}},[t._v(t._s(t.mData.isanswer))]),t._m(0),s("ul",{staticClass:"dropdown-menu",staticStyle:{"min-width":"80px","font-size":"10px"}},[s("li",[s("a",{on:{click:function(e){return t.selectChangeIsAnswer(1)}}},[t._v("必答")])]),s("li",{staticClass:"divider",staticStyle:{margin:"3px 0"},attrs:{role:"separator"}}),s("li",[s("a",{on:{click:function(e){return t.selectChangeIsAnswer(2)}}},[t._v("非必答")])])])]),s("textarea",{directives:[{name:"model",rawName:"v-model",value:t.mData.content,expression:"mData.content"}],staticClass:"question",attrs:{rows:"2"},domProps:{value:t.mData.content},on:{blur:t.titleCompire,input:function(e){e.target.composing||t.$set(t.mData,"content",e.target.value)}}})]),s("span",[t._v("备注"),s("textarea",{directives:[{name:"model",rawName:"v-model",value:t.mData.ps,expression:"mData.ps"}],staticClass:"question",attrs:{rows:"2"},domProps:{value:t.mData.ps},on:{input:function(e){e.target.composing||t.$set(t.mData,"ps",e.target.value)}}})])]),0==t.mData.remark?s("div",t._l(t.mData.options,function(e,i){return s("div",{key:i,staticClass:"q-row"},[s("span",{staticClass:"option-lb"},[t._v("第"+t._s(i+1)+"选项.    ")]),s("input",{directives:[{name:"model",rawName:"v-model",value:e.optText,expression:"opt.optText"}],staticClass:"option",attrs:{type:"text",name:"opt"},domProps:{value:e.optText},on:{input:function(a){a.target.composing||t.$set(e,"optText",a.target.value)}}}),s("img",{staticClass:"del-btn",attrs:{src:a("5609"),alt:"删除"},on:{click:function(e){return t.deleteOpt(i)}}})])}),0):s("div",t._l(t.mData.options,function(e,i){return s("div",{key:i,staticClass:"q-row"},[i+1==t.mData.options.length?s("div",[s("span",{staticClass:"option-lb"},[t._v("其      他.    ")]),s("input",{directives:[{name:"model",rawName:"v-model",value:e.optText,expression:"opt.optText"}],staticClass:"option",attrs:{type:"text",name:"opt",disabled:"disabled"},domProps:{value:e.optText},on:{input:function(a){a.target.composing||t.$set(e,"optText",a.target.value)}}}),s("img",{staticClass:"del-btn",attrs:{src:a("5609"),alt:"删除"},on:{click:function(e){return t.deleteQT(i)}}})]):s("div",[s("span",{staticClass:"option-lb"},[t._v("第"+t._s(i+1)+"选项.    ")]),s("input",{directives:[{name:"model",rawName:"v-model",value:e.optText,expression:"opt.optText"}],staticClass:"option",attrs:{type:"text",name:"opt"},domProps:{value:e.optText},on:{input:function(a){a.target.composing||t.$set(e,"optText",a.target.value)}}}),s("img",{staticClass:"del-btn",attrs:{src:a("5609"),alt:"删除"},on:{click:function(e){return t.deleteOpt(i)}}})])])}),0),s("div",{staticClass:"q-row center"},[s("button",{staticClass:"btn addOpt",on:{click:t.addOpt}},[t._v("添加选项")]),s("button",{staticClass:"btn addOpt",on:{click:t.addOptQT}},[t._v("添加其他")])]),s("hr"),t._l(t.mData.hideitems,function(e,i){return s("div",{key:i,staticClass:"q-row"},[s("div",{staticClass:"q-row",staticStyle:{float:"right"}},[s("span",{staticClass:"option-lb",staticStyle:{float:"left",width:"24%","margin-top":"5px"}},[t._v("选项号")]),s("input",{directives:[{name:"model",rawName:"v-model",value:t.mData.hideitems[i].optitem,expression:"mData.hideitems[index].optitem"}],staticClass:"option",staticStyle:{width:"60%"},attrs:{type:"text",name:"opt",readonly:""},domProps:{value:t.mData.hideitems[i].optitem},on:{click:function(e){return t.toChooseItems(i)},input:function(e){e.target.composing||t.$set(t.mData.hideitems[i],"optitem",e.target.value)}}})]),s("div",{staticClass:"q-row",staticStyle:{float:"right"}},[s("span",{staticClass:"option-lb",staticStyle:{float:"left",width:"24%","margin-top":"5px"}},[t._v("隐藏题    ")]),s("input",{directives:[{name:"model",rawName:"v-model",value:t.mData.hidenum[i].optitem,expression:"mData.hidenum[index].optitem"}],staticClass:"option",staticStyle:{width:"60%"},attrs:{type:"number",name:"opt"},domProps:{value:t.mData.hidenum[i].optitem},on:{input:function(e){e.target.composing||t.$set(t.mData.hidenum[i],"optitem",e.target.value)}}}),s("img",{staticClass:"del-btn",attrs:{src:a("5609"),alt:"删除"},on:{click:function(e){return t.clearSelected(i)}}})])])}),s("div",{staticClass:"q-row center"},[s("button",{staticClass:"btn addOpt",staticStyle:{"margin-top":"3px"},on:{click:t.addOptHide}},[t._v("添加隐藏项")])])],2)},i=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("button",{staticClass:"btn btn-Default dropdown-toggle",staticStyle:{"border-color":"#ccc","background-color":"white",height:"10px"},attrs:{type:"button","data-toggle":"dropdown","aria-haspopup":"true","aria-expanded":"false"}},[a("span",{staticClass:"caret",staticStyle:{"margin-top":"-20px"}}),a("span",{staticClass:"sr-only"},[t._v("Toggle Dropdown")])])}],o={name:"danXuanEdit",props:["editData"],data:function(){return{mData:this.$props.editData,stateList:[{value:"1",label:"第1选项"},{value:"2",label:"第2选项"},{value:"3",label:"第3选项"},{value:"4",label:"第4选项"},{value:"5",label:"第5选项"},{value:"6",label:"第6选项"},{value:"7",label:"第7选项"},{value:"8",label:"第8选项"},{value:"9",label:"第9选项"},{value:"10",label:"第10选项"},{value:"11",label:"第11选项"},{value:"12",label:"第12选项"},{value:"13",label:"第13选项"},{value:"14",label:"第14选项"},{value:"15",label:"第15选项"},{value:"16",label:"第16选项"}]}},methods:{deleteOpt:function(t){var e=this.mData;e.options.splice(t,1),this.$set(this.mData,e)},addOpt:function(){var t=this.mData,e=t.options.length+1;if(17==e)alert("最多16个选项");else if(1==t.remark){var a=this.mData;a.options.splice(a.options.length-1,1),this.$set(this.mData,a);var s=a.options.length+1;a.options.push({optText:"选项"+s}),this.$set(this.mData,a),a.options.push({optText:"其他"}),this.$set(this.mData,a)}else t.options.push({optText:"选项"+e}),this.$set(this.mData,t)},addOptQT:function(){var t=this.mData;if(1==t.remark)alert("只能有一个其他选项");else{var e=this.mData,a=e.options.length+1;17==a?alert("最多16个选项"):(e.options.push({optText:"其他"}),e.remark=1,this.$set(this.mData,e))}},addOptHide:function(){var t=this.mData;t.hideitems.length;t.hideitems.push({optitem:""}),t.hidenum.push({optitem:""})},deleteQT:function(t){var e=this.mData;e.options.splice(t,1),e.remark=0,this.$set(this.mData,e)},selectChangeIsAnswer:function(t){var e=this.mData;1==t?(e.isanswer="必答",$("#showSelectedIsAnswer").html("必答")):(e.isanswer="非必答",$("#showSelectedIsAnswer").html("非必答"))},clearSelected:function(t){var e=this.mData;e.hideitems.splice(t,1),e.hidenum.splice(t,1),this.$set(this.mData,e)},onParamChange:function(t){},toChooseItems:function(t){openwindow("/lnfwzl/lnkhzs/survey/dist/chooseOption.html?id="+this.mData.id+"&num="+t+"&param="+JSON.stringify(this.mData.options))},titleCompire:function(){}}},n=o,l=a("2877"),r=Object(l["a"])(n,s,i,!1,null,"376fc4f3",null);e["default"]=r.exports}}]);
//# sourceMappingURL=chunk-3ec4fe66.d525dbd3.js.map