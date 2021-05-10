// <%@page contentType="text/html; charset=utf-8" %>
Ext.namespace('SF.claim');

SF.claim.BillEmpForm = Ext.extend(Ext.form.FormPanel,{
	constructor : function(config){
		config = Ext.apply({
			//autoHeight : true,
			frame:true,
			collapsible:true,
			height:1450,
			title:"个性化数据",
			items : [{xtype : 'fieldset',
					 labelWidth : 100,
					 autoHeight:true,
					 title : "员工信息",
					 layout : 'column',
					 items : [{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:'textfield',
										width:125,
										value:"${caseEmp.empCode}",
										readOnly:true,
										fieldLabel:"工号"
										}]
							},{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:'textfield',
										width:125,
										value:"${caseEmp.empName}",
										readOnly:true,
										fieldLabel:"姓名"
										}]
							},{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:'textfield',
										width:125,
										value:"${caseEmp.idcard}",
										readOnly:true,
										fieldLabel:"身份证号码"
										}]
							},{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:"combo",
										readOnly:true,
										store:new Ext.data.SimpleStore({data: [['1','全日制员工'],['2','非全日制员工'],['3','基地见习生'],['4','劳务派遣'],['5','实习生'],['6','非员工']], 
										fields: ['key','value']}),
										valueField:"key",
										displayField:"value",
										width:125,
										value:"${caseEmp.personType}",
										triggerAction:"all",
										fieldLabel:"用工形式",
										editable:false,
										mode:"local"
										}]
							},{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:'textfield',
										width:125,
										value:"${caseEmp.dutyName}",
										readOnly:true,
										fieldLabel:"工作岗位"
										}]
							},{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:'textfield',
										width:125,
										value:"${caseEmp.deptName}",
										readOnly:true,
										fieldLabel:"所属地区"
										}]
							},{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:'textfield',
										width:125,
										name:"caseEmp.divitionTm",
										value:"${caseEmp.divitionTm}",
										readOnly:true,
										fieldLabel:"入职时间"
										}]
							},{
								xtype : "panel",
								columnWidth : .5,
								labelWidth : 100,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:'textfield',
										width:125,
										value:"${caseEmp.entryTime}",
										readOnly:true,
										fieldLabel:"入职时长(月)"
										}]
							},{
	     						xtype : "panel",
	     						columnWidth : .5,
	     						labelWidth : 100,
	     						labelAlign : "left",
	     						layout : "form",
	     						items : [{
			     							xtype:"combo",
	     									value:"${caseEmp.insureStatus}",
	     									readOnly:true,
	     									store:new Ext.data.SimpleStore({data: [['1','已投保'],['0','未投保']], fields: ['key','value']}),
	     									valueField:"key",
	     									displayField:"value",
	     									width:125,
	     									triggerAction:"all",
	     									fieldLabel:"是否投保",
	     									editable:false,
	     									mode:"local"}]
	     					}]
			         } ,{//事故出险情况
			        	 	xtype : 'fieldset',
				        	labelWidth : 100,
			        		autoHeight:true,
			        		title : "事故出险情况",
			        		layout : 'column',
			        		items : [{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
											  		xtype:"combo",
													readOnly:true,
													store:new Ext.data.SimpleStore({data: [['1','6:00-14:00'],['2','14:01-22:00'],['3','22:01-5:59']], fields: ['key','value']}),
													valueField:"key",
													displayField:"value",
													width:125,
													value:"${caseEmp.caseSection}",
													triggerAction:"all",
													fieldLabel:"出险时间段",
													editable:false,
													mode:"local"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:"combo",
													readOnly:true,
													store:new Ext.data.SimpleStore({data: [['1','上班途中'],['2','下班途中'],['3','工作地点'],['4','家中'],['5','其它']],fields: ['key','value']}),
													valueField:"key",
													displayField:"value",
													width:125,
													value:"${caseEmp.casePoint}",
													triggerAction:"all",
													fieldLabel:"出险地点",
													editable:false,
													mode:"local"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:"combo",
													readOnly:true,
													store:new Ext.data.SimpleStore({data: [['1','工伤'],['2','职业病']], fields: ['key','value']}),
													valueField:"key",
													displayField:"value",
													width:125,
													value:"${caseEmp.caseType}",
													triggerAction:"all",
													fieldLabel:"出险类型",
													editable:false,
													mode:"local"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
					 								xtype:"combo",
					 								readOnly:true,
					 								store:new Ext.data.SimpleStore({data: [['1','摔伤'],['2','扭伤'],['3','拉伤'],['4','划伤'],['5','压伤'],['6','砸伤'],['7','碰伤'],['8','撞伤'],['9','烫伤'],['10','烧伤'],['11','磕伤'],['12','咬伤'],['13','单方交通事故'],['14','双方交通事故'],['15','多方交通事故'],['16','残疾'],['17','死亡']], fields: ['key','value']}),
					 								valueField:"key",
					 								displayField:"value",
					 								width:125,
													value:"${caseEmp.accidentType}",
					 								triggerAction:"all",
					 								fieldLabel:"事故类型",
					 								editable:false,
					 								mode:"local"
					 								}]
										},{
											xtype : "panel",
											columnWidth : 1,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textarea',
													height:80,
													width:390,
													value:"${caseEmp.caseProcess}",
													readOnly:true,
													fieldLabel:"出险过程"
													}]
										}]
			         } ,{//赔付情况
				        	xtype : 'fieldset',
				        	labelWidth : 100,
			        		autoHeight:true,
			        		title : "赔付情况",
			        		layout : 'column',
			        		items : [{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textfield',
													width:125,
													value:"${caseEmp.billFee}",
													readOnly:true,
													fieldLabel:"票据金额"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textfield',
													width:125,
													value:"${caseEmp.medicalFee}",
													readOnly:true,
													fieldLabel:"医疗费"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textfield',
													width:125,
													value:"${caseEmp.hospitalFee}",
													readOnly:true,
													fieldLabel:"住院津贴"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textfield',
													width:125,
													value:"${caseEmp.disablityFee}",
													readOnly:true,
													fieldLabel:"伤残赔偿金"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textfield',
													width:125,
													value:"${caseEmp.deathFee}",
													readOnly:true,
													fieldLabel:"身故赔偿金"
													}]
										},{
											xtype : "panel",
											columnWidth : .5,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textfield',
													width:125,
													value:"${caseEmp.totalFee}",
													readOnly:true,
													fieldLabel:"总赔付金额"}]
										},{
											xtype : "panel",
											columnWidth : 1,
											labelWidth : 100,
											labelAlign : "left",
											layout : "form",
											items : [{
													xtype:'textarea',
													height:80,
													width:390,
													value:"${caseEmp.remark}",
													readOnly:true,
													fieldLabel:"备注"
													}]
										} ]
				         }]
		},config);
		SF.claim.BillEmpForm.superclass.constructor.call(this,config);
	}
});


Ext.reg('SF.claim.billEmpForm',SF.claim.BillEmpForm);