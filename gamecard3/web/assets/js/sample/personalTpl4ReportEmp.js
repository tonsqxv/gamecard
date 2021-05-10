// <%@page contentType="text/html; charset=utf-8" %>
Ext.namespace('SF.claim.emp');

SF.claim.emp.PersonalEmpForm = Ext.extend(Ext.form.FormPanel,{
	constructor : function(config){
		config = Ext.apply({
			autoHeight : true,
			frame:true,
			collapsible:true,
			title:"个性化数据",
			url:"caseEmp_saveReport.action",
			items : [
			         {
			        	xtype : 'fieldset',
			        	labelWidth : 100,
		        		autoHeight:true,
		        		title : "员工信息",
		        		layout : 'column',
		        		items : [
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{	
		     									xtype:"textfield",
		     									width:125,
		     									name:"caseEmp.empCode",
		     									readOnly:true,
		     									fieldLabel:"工号"}]
		     					},
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{
		     									xtype:"textfield",
		     									width:125,
		     									name:"caseEmp.empName",
		     									allowBlank:false,
		     									fieldLabel:"姓名"}]
		     					},
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{
		     									xtype:"textfield",
		     									width:125,
		     									name:"caseEmp.idcard",
		     									allowBlank:false,
		     									fieldLabel:"身份证号码"}]
		     					},
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{
		     									xtype:"combo",
		     									hiddenName:"caseEmp.personType",
		     									readOnly:true,
		     									store:new Ext.data.SimpleStore({data: [['1','全日制员工'],['2','非全日制员工'],['3','基地见习生'],['4','劳务派遣'],['5','实习生'],['6','非员工']], 
		     									fields: ['key','value']}),
		     									valueField:"key",
		     									displayField:"value",
		     									width:125,
		     									triggerAction:"all",
		     									fieldLabel:"用工形式",
		     									editable:false,
		     									mode:"local"}]
		     					},
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{
		     									xtype:"textfield",
		     									width:125,
		     									name:"caseEmp.dutyName",
		     									readOnly:true,
		     									fieldLabel:"工作岗位"}]
		     					},
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{
		     									xtype:"textfield",
		     									width:125,
		     									name:"caseEmp.deptName",
		     									readOnly:true,
		     									fieldLabel:"所属地区"}]
		     					},{
		     					xtype:'hidden',
		     					name:'caseEmp.deptId'
		     					},
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{
		     									xtype:"textfield",
		     									width:125,
		     									name:"caseEmp.divitionTm",
		     									readOnly:true,
		     									fieldLabel:"入职时间"}]
		     					},
		     					{
		     						xtype : "panel",
		     						columnWidth : .33,
		     						labelWidth : 100,
		     						labelAlign : "left",
		     						layout : "form",
		     						items : [{
		     									xtype:"textfield",
		     									width:125,
		     									name:"caseEmp.entryTime",
		     									readOnly:true,
		     									fieldLabel:"入职时长(月)"}]
		     					}
		     	         ]
			         }
			         ,{//事故出险情况
			        	 	xtype : 'fieldset',
				        	labelWidth : 100,
			        		autoHeight:true,
			        		title : "事故出险情况",
			        		layout : 'column',
			        		items : [
			     					{
			     						xtype : "panel",
			     						columnWidth : .33,
			     						labelWidth : 100,
			     						labelAlign : "left",
			     						layout : "form",
			     						items : [{
			     							  		xtype:"combo",
			     									hiddenName:"caseEmp.caseSection",
			     									store:new Ext.data.SimpleStore({data: [['1','6:00-14:00'],['2','14:01-22:00'],['3','22:01-5:59']], fields: ['key','value']}),
			     									valueField:"key",
			     									displayField:"value",
			     									width:125,
			     									triggerAction:"all",
			     									fieldLabel:"出险时间段",
			     									allowBlank:false,
			     									editable:false,
			     									mode:"local"}]
			     					},
			     					{
			     						xtype : "panel",
			     						columnWidth : .33,
			     						labelWidth : 100,
			     						labelAlign : "left",
			     						layout : "form",
			     						items : [{
			     									xtype:"combo",
			     									hiddenName:"caseEmp.casePoint",
			     									store:new Ext.data.SimpleStore({data: [['1','上班途中'],['2','下班途中'],['3','工作地点'],['4','家中'],['5','其它']],fields: ['key','value']}),
			     									valueField:"key",
			     									displayField:"value",
			     									width:125,
			     									triggerAction:"all",
			     									fieldLabel:"出险地点",
			     									allowBlank:false,
			     									editable:false,
			     									mode:"local"}]
			     					},
			     					{
			     						xtype : "panel",
			     						columnWidth : .33,
			     						labelWidth : 100,
			     						labelAlign : "left",
			     						layout : "form",
			     						items : [{
			     									xtype:"combo",
			     									hiddenName:"caseEmp.caseType",
			     									store:new Ext.data.SimpleStore({data: [['1','工伤'],['2','职业病']], fields: ['key','value']}),
			     									valueField:"key",
			     									displayField:"value",
			     									width:125,
			     									triggerAction:"all",
			     									fieldLabel:"出险类型",
			     									editable:false,
			     									mode:"local"}]
			     					},
			     					{
			     						xtype : "panel",
			     						columnWidth : .33,
			     						labelWidth : 100,
			     						labelAlign : "left",
			     						layout : "form",
			     						items : [{
			     								xtype:"combo",
			     								hiddenName:"caseEmp.accidentType",
			     								store:new Ext.data.SimpleStore({data: [['1','摔伤'],['2','扭伤'],['3','拉伤'],['4','划伤'],['5','压伤'],['6','砸伤'],['7','碰伤'],['8','撞伤'],['9','烫伤'],['10','烧伤'],['11','磕伤'],['12','咬伤'],['13','单方交通事故'],['14','双方交通事故'],['15','多方交通事故'],['16','残疾'],['17','死亡']], fields: ['key','value']}),
			     								valueField:"key",
			     								displayField:"value",
			     								width:125,
			     								triggerAction:"all",
			     								fieldLabel:"事故类型",
			     								allowBlank:false,
			     								editable:false,
			     								mode:"local"
			     								}]
			     					},
			     					{
			     						xtype : "panel",
			     						columnWidth : 1,
			     						labelWidth : 100,
			     						labelAlign : "left",
			     						layout : "form",
			     						items : [{
			     									xtype:'textarea',
			     									height:80,
			     									width:620,
			     									allowBlank:false,
			     									name:"caseEmp.caseProcess",
			     									fieldLabel:"出险过程"}]
			     					}
			     	         ]
			         }
			         
			]
		},config);
		SF.claim.emp.PersonalEmpForm.superclass.constructor.call(this,config);
	}
});


Ext.reg('SF.claim.emp.personalEmpForm',SF.claim.emp.PersonalEmpForm);