// <%@page contentType="text/html; charset=utf-8" %>


var statusData = [[ '1', '草稿' ],	[ '2', '提交' ],	[ '3', '驳回' ],	[ '4', '通过' ]];
var caseSectionData = [['1','6:00-14:00'],['2','14:01-22:00'],['3','22:01-5:59']];
var casePointData = [['1','上班途中'],['2','下班途中'],['3','工作地点'],['4','家中'],['5','其它']];
var caseTypeData = [['1','工伤'],['2','职业病']];
var accidentTypeData = [['1','摔伤'],['2','扭伤'],['3','拉伤'],['4','划伤'],['5','压伤'],['6','砸伤'],['7','碰伤'],['8','撞伤'],['9','烫伤'],['10','烧伤'],['11','磕伤'],['12','咬伤'],['13','单方交通事故'],['14','双方交通事故'],['15','多方交通事故'],['16','残疾'],['17','死亡']];
var personTypeData = [['1','全日制员工'],['2','劳务派遣'],['3','非全日制'],['4','实习生'],['5','勤工助学'],['6','基地见习生'],['7','其他']];

Ext.namespace('SF.claim.event');

Ext.onReady(function(){
	new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			layout:'border',
			xtype:'SF.claim.event.mainPanel'
		}],
		listeners : {
			afterrender : function(view){
				view.items.get(0).searchHandler();
			}
		}
	});
});



/**
 * 出险管理-查询表单
 */
SF.claim.event.QueryPanel = Ext.extend(Ext.form.FormPanel,{
	constructor : function(config){
		config = Ext.apply({
			id:'eventQueryPanel',
			autoHeight:true,
			frame:true,
			labelWidth:120,
			labelAlign:'left',
			bodyStyle:'padding: 1px 5px 0px 1px',
			items:{
				xtype:'fieldset',
				title:"查询条件",
				layout:'column',
				defaults:{
					layout : "form",
					defaults:{
						anchor:'85%'
					}
				},
				items:[						   	
						 {
								columnWidth : .33,
								items : {
								xtype:'textfield',
								name:"event.eventId",
								fieldLabel:"事件工单号"
								}
							} ,{
								columnWidth : .33,
								items : {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "event.reportTmBegin",
									fieldLabel : '上报时间（开始）'
								}
							},{
								columnWidth : .33,
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "event.reportTmEnd",
									fieldLabel : '上报时间（结束）'
								}]
							},{
								columnWidth : .33,
								items : {
									xtype : "SF.comboBox",
									hiddenName : "event.status",
									fieldLabel : "状态",
									emptyText : '全部',
									hasAll:true,
									data:statusData
								}
							} ,{
								columnWidth : .33,
								items : {
									xtype : "SF.ims.deptField",
									name : "event.reportDeptName",
									fieldLabel : "上报地区/部门"
								}
							} ,{
								xtype:'hidden',
								id:'event.reportDept',
								name:'event.reportDept'
							},{
								columnWidth : .33,
								items : {
									xtype : "SF.comboBox",
									hiddenName : "event.includeChild",
									fieldLabel : "所辖网点",
									value : '1',
									data:[ [ '1', '包含' ],	['0', '不包含' ]]
								}
							}]
			}
		},config);
		SF.claim.event.QueryPanel.superclass.constructor.call(this,config);
	},
	expandHandler : function(combo){
		var store = combo.getStore();
		if(store.getCount()>0 && store.getAt(0).get('name') != "全部"){
			store.insert(0,new store.recordType({name:"全部",insureTypeId:null}));
		}
	}
});
Ext.reg('SF.claim.event.queryPanel',SF.claim.event.QueryPanel);

/**
 * 出险管理-列表显示
 */
SF.claim.event.GridPanel = Ext.extend(Ext.grid.GridPanel,{
	constructor : function(config){
		var store = new Ext.data.JsonStore({
			url:'eventSearch.action',
			root:'data',
			id:'eventId',
			totalProperty:'total',
			fields:['eventId','caseOccourtime','caseFindtime','casePlace','caseDesc','returnReasion','reportUserName','reportDeptName','reportContact','reportTm','modifyUser','modifyTm','status'],
			listeners : {
				beforeload : this.beforeeventListStoreLoad.createDelegate(this)
			}
		});
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns:[
				{renderer:this.showMenu,width:30},{
					header:"事件工单号",
					width:90,
					dataIndex:'eventId'
				},{
					header:"案件类型",
					width:60,
					dataIndex:'caseType',
					rendererCall: function(value){
						return '员工险';
					}
				},{
					// 节点状态1=草稿2=提交3=驳回4=通过
					header:"状态",
					width:50,
					dataIndex:'status',
					rendererCall : function(value){
						return statusData[value][1];
					}
				},{
					header:"上报地区/部门",
					dataIndex:'reportDeptName'
				},{
					header:"上报时间",
					dataIndex:'reportTm',
					rendererCall : function(value){
						return Ext.renderDate(value);
					}
				},{
					header:"上报人",
					width:60,
					dataIndex:'reportUserName'
				},{
					header:"上报人联系方式",
					dataIndex:'reportContact'
				},{
					header:"案件发现时间",
					dataIndex:'caseFindtime',
					rendererCall : function(value){
						return Ext.renderDate(value);
					}
				},{
					header:"案件发生时间",
					dataIndex:'caseOccourtime',
					rendererCall: function(value){
						return Ext.renderDate(value);
					}
				},{
					header:"案件发生地点",
					dataIndex:'casePlace'
				},{
					header:"案件描述",
					dataIndex:'caseDesc'
				},{
					header:"驳回原因",
					dataIndex:'returnReasion'
				}
			]
		});
		config = Ext.apply({
			id:'eventGrid',
			store:store,
			frame:true,
			sm:sm,
			cm:cm,
			stripeRows: true,
			loadMask:true,
			tbar : new Ext.PagingToolbar({
				store : store,
				pageSize : pageSize,
				displayInfo : true
			}),
			listeners:{cellclick:this.onCellClick.createDelegate(this),rowcontextmenu:this.rightMenu.createDelegate(this)}
		},config);
		//初始化弹出菜单
		this.initContextMenu();
		SF.claim.event.GridPanel.superclass.constructor.call(this,config);
	},
	beforeeventListStoreLoad : function(store){
		store.baseParams = Ext.apply(Ext.getCmp('eventQueryPanel').getForm().getValues(),{
			start:0,
			limit:pageSize
		});
	},
	//初始化右键点击表格行菜单
	initContextMenu:function(){
		var contextMenu = new Ext.menu.Menu( {                
                width:100
        });
        
	    var commitMenu = {
                iconCls:'apply',
                text:'提交',
                scope:this,
                handler:function(){
                   contextMenu.hide(true);
                   this.commitHandler();
                }
            };
     	contextMenu.add(commitMenu);
     	
     	 var editMenu = {
                iconCls:'edit',
                text:'修改',
                scope:this,
                handler:function(){
                   contextMenu.hide(true);
                   this.editHandler();
                }
            };
     	contextMenu.add(editMenu);
     	
     	 var auditMenu = {
                iconCls:'audit',
                text:'通过',
                scope:this,
                handler:function(){
                   contextMenu.hide(true);
                   this.auditHandler();
                }
            };
     	contextMenu.add(auditMenu);
     	
     	 var opposeMenu = {
                iconCls:'oppose',
                text:'驳回',
                scope:this,
                handler:function(){
                   contextMenu.hide(true);
                   this.opposeHandler();
                }
            };
     	contextMenu.add(opposeMenu);
     	
     	 var delMenu = {
                iconCls:'delete',
                text:'删除',
                scope:this,
                handler:function(){
                   contextMenu.hide(true);
                   this.delHandler();
                }
            };
     	contextMenu.add(delMenu);
     	
     	 var insureMenu = {
                iconCls:'insure',
                text:'险种分配',
                scope:this,
                handler:function(){
                   contextMenu.hide(true);
                   this.insureHandler();
                }
            };
     	contextMenu.add(insureMenu);
     	
     	
     	this.contextMenu = contextMenu;
	},
	getInfoWin:function(){
		return Ext.getCmp('infoWin');
	},
	getInsureWin:function(){
		return Ext.getCmp('insureWin');
	},
	showMenu:function(value, cellmeta, record, rowIndex, columnIndex, store){
		return "<image style='cursor:hand;' src='../images/claim/select.gif' style='width:22px;height:20px;'></image>";
	},
	//右键菜单
	rightMenu:function(grid,rowIndex,e){
		this.onCellClick(grid,rowIndex,0,e);
	},
	onCellClick:function(grid,rowIndex,columnIndex,e){
		if(columnIndex != 0){
			return;
		}
		this.selectRecord = this.store.getAt(rowIndex);
     	e.preventDefault();
        this.contextMenu.showAt(e.getXY());
	},
	commitHandler:function(){
		var record = this.selectRecord;
		if(record.data.status>2){
			Ext.Msg.alert("提示","只能提交状态为‘草稿’或者‘提交’的记录！");
			return ;
		}
		var ids = record.id;
		
		Ext.Ajax.request({
							params: {ids: ids},
							url: "eventCommitDraft.action",
							success:function(response,opts){
								var msg = Ext.decode(response.responseText).msg;
								if(msg){
									Ext.Msg.alert('提示',msg);
								}else{
									Ext.Msg.alert('提示','提交成功!');
									this.getStore().reload();
								}
							}.createDelegate(this),
							failure:function(response,opts){
								var msg = Ext.decode(response.responseText).msg;
								Ext.Msg.alert("提示",msg);
							}
						});
		
	},
	editHandler : function(){
		var data = this.selectRecord.data;
		if(data.status!=1&&data.status!=3){
			Ext.Msg.alert("提示","只能修改状态为‘草稿’或者‘驳回’的记录！");
			return ;
		}
		var formData = {};
		for(var p in data){
			if((p == 'caseOccourtime'||p=='caseFindtime')&&data[p]!=null){
				data[p] = data[p].replace('T',' ');
			}else if(p == 'caseOccourtime'||p=='caseFindtime'){
				data[p] = '11';
			}
			formData['event.'+p] = data[p];
		}
		var infoWin = this.getInfoWin();
		//TODO
		//员工险表格加载数据
		infoWin.getGrid().store.load({params:{ids:data.eventId}});
		infoWin.setTitle("修改");
		infoWin.show();
		infoWin.getForm().setValues(formData);
	},
	auditHandler:function(){
		var record = this.selectRecord;
		if(record.data.status!=2){
			Ext.Msg.alert("提示","只能审核状态为‘提交’的记录！");
			return ;
		}
		Ext.Ajax.request({
							params: {ids: record.id},
							url: "eventAudit.action",
							success:function(response,opts){
								var msg = Ext.decode(response.responseText).msg;
								if(msg){
									Ext.Msg.alert('提示',msg);
								}else{
									Ext.Msg.alert('提示','审核通过!');
									this.getStore().reload();
								}
							}.createDelegate(this),
							failure:function(response,opts){
								var msg = Ext.decode(response.responseText).msg;
								Ext.Msg.alert("提示",msg);
							}
						});
		
	},
	opposeHandler:function(){
		var record = this.selectRecord;
		if(record.data.status!=2){
			Ext.Msg.alert("提示","只能驳回状态为‘提交’的记录！");
			return ;
		}
		Ext.Msg.prompt('提示','请输入驳回理由：',function(btn,text){
			if(btn=='ok'){
				Ext.Ajax.request({
									params: {ids: record.id,reason:text},
									url: "eventOppose.action",
									success:function(response,opts){
										var msg = Ext.decode(response.responseText).msg;
										if(msg){
											Ext.Msg.alert('提示',msg);
										}else{
											Ext.Msg.alert('提示','驳回成功!');
											this.getStore().reload();
										}
									}.createDelegate(this),
									failure:function(response,opts){
										var msg = Ext.decode(response.responseText).msg;
										Ext.Msg.alert("提示",msg);
									}
								});
			}
			
		}.createDelegate(this));
	},
	insureHandler:function(){
		var data = this.selectRecord.data;
		if(data.status!=4){
			Ext.Msg.alert("提示","只能给状态为‘通过’的记录分配险种！");
			return ;
		}
		var formData = {};
		for(var p in data){
			if((p == 'caseOccourtime'||p=='caseFindtime')&&data[p]!=null){
				data[p] = data[p].replace('T',' ');
			}
			formData['event.'+p] = data[p];
		}
		var insureWin = this.getInsureWin();
		insureWin.getForm().setValues(formData);
		//员工险表格加载数据
		insureWin.getGrid().store.load({params:{ids:data.eventId}});
		insureWin.show();
	},
	delHandler:function(){
		var data = this.selectRecord.data;
		if(data.status!=1&&data.status!=3){
			Ext.Msg.alert("提示","只能删除状态为‘草稿’或者‘驳回’的记录！");
			return ;
		}
		Ext.MessageBox.confirm('提示','确定要删除这条记录吗?', function(result){
			if (result == 'yes') {
				Ext.Ajax.request({
									params: {ids: this.selectRecord.id},
									url: "eventDel.action",
									success:function(response,opts){
										var msg = Ext.decode(response.responseText).msg;
										if(msg){
											Ext.Msg.alert('提示',msg);
										}else{
											Ext.Msg.alert('提示','删除成功!');
											this.getStore().reload();
										}
									}.createDelegate(this),
									failure:function(response,opts){
										var msg = Ext.decode(response.responseText).msg;
										Ext.Msg.alert("提示",msg);
									}
								});
			}
		}.createDelegate(this));
	
		
	},
	viewHandler:function(){
		
	}
	
});
Ext.reg('SF.claim.event.gridPanel',SF.claim.event.GridPanel);


/**
 * 案件录入-基本信息表单
 */
SF.claim.event.FormPanel = Ext.extend(Ext.form.FormPanel,{
	constructor : function(config){
		config = Ext.apply({
			frame:true,
			labelWidth:100,
			labelAlign:'left',
			autoHeight:true,
			items:[{
				xtype:'SF.claim.baseInfoFieldSet'
			}]
		},config);
		SF.claim.event.FormPanel.superclass.constructor.call(this,config);
	}
	
});
Ext.reg('SF.claim.event.formPanel',SF.claim.event.FormPanel);


/*
 * 员工险表字段 EVENT_EMP_ID NUMBER 员工险模板ID EVENT_ID VARCHAR2(16) 事件工单号 EMP_CODE
 * VARCHAR2(100) Y 工号 EMP_NAME VARCHAR2(300) Y 姓名 IDCARD VARCHAR2(100) Y 身份证号码
 * PERSION_TYPE VARCHAR2(300) Y 用工形式 DUTY_NAME VARCHAR2(300) Y 工作岗位 DEPT_ID
 * NUMBER Y 所属地区 DIVISION_TM DATE Y 入职时间 ENTRY_TIME NUMBER Y 入职时长（月）
 * CASE_PROCESS VARCHAR2(4000) Y 出险过程 CASE_SECTION NUMBER Y
 * 出险时间段:1=(6：00-14：00)2=(14：01-22：00)3=(22：01-5：59) CASE_POINT NUMBER Y
 * 出险地点:1=上班途中2=下班途中3=工作地点4=家中5=其它 CASE_TYPE NUMBER Y 出险类型:1=工伤、2=职业病
 * ACCIDENT_TYPE NUMBER Y
 * 事故类型：1=摔伤2=扭伤3=拉伤4=划伤5=压伤6=砸伤7=碰伤8=撞伤9=烫伤10=烧伤11=磕伤12=咬伤13=单方交通事故14=双方交通事故15=多方交通事故16=残疾17=死亡
 * 
 */

/* 出险管理-案件录入-新增-员工基本信息表单 */
SF.claim.event.EmpFormSet = Ext.extend(Ext.form.FieldSet,{
	constructor : function(config){
		SF.deptCode = '001',
		config = Ext.apply({
			title:'员工信息',
			layout:'column',
			autoHeight:true,
			autoWidth:true,
			defaults:{
				layout:'form'
			},
			items:[{
				columnWidth:.5,
				items:{
					xtype:'empfield',
					name:'empCode',
					loadForm:this,
					fieldLabel:"工号",
					allowBlank:false,
					anchor:'85%'
				}
			},{
				columnWidth:.5,
				items:{
					xtype:'textfield',
					name:'empName',
					fieldLabel:"姓名",
					allowBlank:false,
					anchor:'85%'
				}
			},{
				columnWidth:.5,
				items:{
					xtype:'textfield',
					name:'idCard',
					fieldLabel:"身份证号码",
					allowBlank:false,
					anchor:'85%'
				}	
			},{
				columnWidth:.5,
				items:{
					xtype : "SF.comboBox",
					hiddenName : "personType",
					fieldLabel : "用工形式",
					data:personTypeData,
					allowBlank:false,
					anchor:'85%'
				}	
			},{
				columnWidth:.5,
				items:{
					xtype:'textfield',
					name:'dutyName',
					fieldLabel:"工作岗位",
					allowBlank:false,
					anchor:'85%'
				}	
			},{
				columnWidth:.5,
				items:{
					xtype:'textfield',
					name:'deptName',
					fieldLabel:"所属地区",
					allowBlank:false,
					anchor:'85%'
				}	
			},{
				xtype:'hidden',
				name:'deptId'
			},{
				columnWidth:.5,
				items:{
					xtype:'datefield',
					name:'sfDate',
					fieldLabel:"入职时间",
					format : 'Y-m-d',
					allowBlank:false,
					anchor:'85%'
				}	
			}]
			
		},config);
		SF.claim.event.EmpFormSet.superclass.constructor.call(this,config);
	},
	setData : function(data){
		    this.findParentByType('form').getForm().setValues([{id:'empCode',value:data.empCode},{id:'empName',value:data.empName},{id:'idCard',value:data.nationalIdentifier},{id:'personType',value:this.getPersonType(data.personType)},{id:'dutyName',value:data.empDutyName},{id:'deptName',value:data.deptName},{id:'deptId',value:data.deptId},{id:'sfDate',value:data.sfDate.split("T")[0]}]);
	},
	getPersonType:function(value){
		for(var i=0;i<personTypeData.length;i++){
			if(personTypeData[i][1]==value){
				return personTypeData[i][0];
			}
		}
	}
});

Ext.reg('SF.claim.event.empFormSet',SF.claim.event.EmpFormSet);


/* 出险管理-案件录入-新增-事故出险情况表单 */
SF.claim.event.EmpAccFormSet = Ext.extend(Ext.form.FieldSet,{
	constructor : function(config){
		config = Ext.apply({
			title:'事故出险情况',
			layout:'column',
			autoHeight:true,
			autoWidth:true,
			defaults:{
				layout:'form',
				defaults:{
					anchor:'85%'
				}
			},
			items:[{
				columnWidth:.5,
				items:{
					xtype:'SF.comboBox',
					hiddenName:'caseSection',
					fieldLabel:"出险时间段",
					allowBlank:false,
					data:caseSectionData
					
				}
			},{
				columnWidth:.5,
				items:{
					xtype:'SF.comboBox',
					hiddenName:'casePoint',
					fieldLabel:"出险地点",
					allowBlank:false,
					data:casePointData
				}	
			},{
				columnWidth:.5,
				items:{
					xtype:'SF.comboBox',
					hiddenName:'caseType',
					fieldLabel:"出险类型",
//					editable: true,
					data:caseTypeData
				}	
			},{
				columnWidth:.5,
				items:{
					xtype:'SF.comboBox',
					hiddenName:'accidentType',
					fieldLabel:"事故类型",
					allowBlank:false,
					data:accidentTypeData
				}	
			},{
				columnWidth:1,
				items:{
					xtype:'textarea',
					name:'caseProcess',
					fieldLabel:"出险过程",
					allowBlank:false,
					anchor:'92.5%'
				}
			}]
			
		},config);
		SF.claim.event.EmpAccFormSet.superclass.constructor.call(this,config);
	}
});

Ext.reg('SF.claim.event.empAccFormSet',SF.claim.event.EmpAccFormSet);

/**
 * 案件录入-新增员工窗口
 */
SF.claim.event.EmpWin = Ext.extend(Ext.Window,{
	constructor : function(config){
		config = Ext.apply({
			closeAction:'hide',
			modal:true,
			plain:true,
			resizable:false,
			domain:null,
    		autoHeight:true,
			width:600,
			layout:'fit',
			items:[{
				xtype:'form',
				frame:true,
				autoHeight:true,
				autoWidth:true,
				items:[{xtype:'SF.claim.event.empFormSet'},{xtype:'SF.claim.event.empAccFormSet'}]
			}],
			tbar:[{
				text:"保存",
				iconCls:'save',
				handler : this.save.createDelegate(this)
			}]
		},config);
		SF.claim.event.EmpWin.superclass.constructor.call(this,config);
	},
	save : function(){
		if(!this.items.get(0).getForm().isValid()){
			Ext.Msg.alert("提示", "数据校验错误!");
			return ;
		}
		// 将form数据添加到表格
		if(this.editRecord){
			this.store.remove(this.editRecord);
		}
		try{
			this.store.add(new this.store.recordType(this.items.get(0).getForm().getValues(), this.items.get(0).getForm().findField('empCode').getValue()));
		}catch(e){
			Ext.Msg.alert("提示", "同一个员工只能被添加一次！");
			return;
		}
		this.hide();
	}
});
Ext.reg('SF.claim.event.empWin',SF.claim.event.EmpWin);

/**
 * 案件录入-员工险列表
 */
SF.claim.event.EmpGridPanel = Ext.extend(Ext.grid.GridPanel,{
	empWin: new SF.claim.event.EmpWin(),
	constructor : function(config){
		var store = new Ext.data.JsonStore({
			url:'eventEmpSearch.action',
			root:'eventEmps',
			id:'empCode',
			fields:['id','eventId','empCode', 'empName','idCard', 'personType','dutyName','deptId','sfDate','caseProcess','caseSection','casePoint','caseType','accidentType']
		});
		this.empWin.store = store;
		this.empWin.grid = this;
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			 defaults: {
		        width: 60
   			 },
			columns:[
				new Ext.grid.RowNumberer(),sm,{
					header:"工号",
					dataIndex:'empCode'
				},{
					header:"姓名",
					dataIndex:'empName'
				},{
					header:"身份证号码",
					width:80,
					dataIndex:'idCard'
				},{
					header:"用工形式",
					width:80,
					dataIndex:'personType',
					rendererCall :function(value){
						return personTypeData[value-1][1];
					}
				},{
					header:"工作岗位",
					dataIndex:'dutyName'
				},{
					header:"所属地区",
					width:70,
					dataIndex:'deptId'
				},{
					header:"入职时间",
					dataIndex:'sfDate',
					width:80,
					rendererCall:function(value){
						return value.split('T')[0];
					}
				},{
					header:"入职时长（月）",
					dataIndex:'sfDate',
					rendererCall :function(value){
						
					}
				},{
					header:"出险过程",
					width:150,
					dataIndex:'caseProcess'
				},
				// 出险时间段:1=(6：00-14：00)2=(14：01-22：00)3=(22：01-5：59)
					{
					header:"出险时间段",
					width:80,
					dataIndex:'caseSection',
					rendererCall :function(value){
						return caseSectionData[value-1][1];
					}
				},
				// 出险地点:1=上班途中2=下班途中3=工作地点4=家中5=其它
					{
					header:"出险地点",
					dataIndex:'casePoint',
					rendererCall :function(value){
						return casePointData[value-1][1];
					}
				},
				// 出险类型:1=工伤、2=职业病
				{
					header:"出险类型",
					dataIndex:'caseType',
					rendererCall :function(value){
						try{
							return caseTypeData[value-1][1];
						}catch(e){
							return value;
						}
					}
				},
				// 事故类型：1=摔伤2=扭伤3=拉伤4=划伤5=压伤6=砸伤7=碰伤8=撞伤9=烫伤10=烧伤
				// 11=磕伤12=咬伤13=单方交通事故14=双方交通事故15=多方交通事故16=残疾17=死亡
					{
					header:"事故类型",
					dataIndex:'accidentType',
					rendererCall :function(value){
						return accidentTypeData[value-1][1];
					}
				}	

			]
		});
		config = Ext.apply({
			store:store,
			sm:sm,
			cm:cm,
			stripeRows: true,
			loadMask:true,
			tbar : [{text:'新增',iconCls:'add',handler:this.add.createDelegate(this)},{text:'修改',iconCls:'edit',handler:this.edit.createDelegate(this)},{text:'删除',iconCls:'delete',handler:this.del.createDelegate(this)}]
		
		},config);
		SF.claim.event.EmpGridPanel.superclass.constructor.call(this,config);
	},
	add:function(){
		this.empWin.setTitle('新增');
		this.empWin.editRecord = null;
		this.empWin.show();
	},
	edit:function(){
		var sm = this.getSelectionModel();
		if(sm.getCount() < 1){
			Ext.Msg.alert("提示","请选择要修改的记录!");
			return ;
		}
		if(sm.getCount() > 1){
			Ext.Msg.alert("提示","一次只能修改一条记录!");
			return ;
		}
		var form = this.empWin.items.get(0).getForm();
		form.loadRecord(sm.getSelected());
		this.empWin.editRecord = sm.getSelected();
		this.empWin.setTitle("修改");
		this.empWin.show();
	},
	del:function(){
		var sm = this.getSelectionModel();
		if(sm.getCount() < 1){
			Ext.Msg.alert("提示","请选择要删除的记录!");
			return ;
		}
		Ext.MessageBox.confirm('提示','确定要删除选中的记录吗?', function(result){
			if (result == 'yes') {
				Ext.each(sm.getSelections(),function(record){
					//TODO each方法会自动设置this指向外层this?
					this.store.remove(record);
				});
			}
		});
	}
	
});

Ext.reg('SF.claim.event.empGridPanel',SF.claim.event.EmpGridPanel);


/*
 * 保险类型树
 */
SF.claim.InsureTree = Ext.extend(Ext.tree.TreePanel,{
	constructor : function(config){
								
		var treeLoader = new Ext.tree.TreeLoader({
		baseAttrs:{uiProvider:Ext.tree.TreeCheckNodeUI},
		dataUrl:"processDeal_searchCheckTreeByParentId.action?textField=name&idField=insureTypeId&leafField=&clsField=&childrenField="
		});
		var rootNode = new Ext.tree.AsyncTreeNode({id:"1",text:"保险类型"});
		config = Ext.apply({
			onlyLeafCheckable:true,
			autoScroll:true,
			width:200,
			height:350,
			loader: treeLoader,
			root: {id:"1",text:"保险类型"},
			tbar:[{
				text:"保存",
				iconCls:'save',
				handler : this.save.createDelegate(this)
			}],
			listeners : {
				afterrender : function(node) {
					this.expandAll();
				}
			}	
						
		},config);
		SF.claim.InsureTree.superclass.constructor.call(this,config);
	},
	checkInsure:function(eventEmpId){
		    this.eventEmpId = eventEmpId;
			Ext.Ajax.request({
							params: {eventEmpId: eventEmpId},
							url: "event_insureTypes.action",
							success:function(response,opts){
								//TODO 选中返回的类型
								var msg = Ext.decode(response.responseText).msg;
								var insureTypes = Ext.decode(response.responseText).insureTypes;
								for(var i=0;i<insureTypes.length;i++){
									this.getNodeById(insureTypes[i]).select();
									this.getNodeById(insureTypes[i]).disable();
								}
							}.createDelegate(this),
							failure:function(response,opts){
								var msg = Ext.decode(response.responseText).msg;
								Ext.Msg.alert("提示",msg);
							}
						});
	},
	save:function(){
				var selectNodes = this.getChecked();
				var ids =[];
				Ext.each(selectNodes,function(node){
					ids.push(node.id);
				});
				Ext.Ajax.request({
							params: {eventEmpId: this.eventEmpId,
									 ids:ids},
							url: "event_saveEventEmpType.action",
							success:function(response,opts){
								//TODO
								var msg = Ext.decode(response.responseText).msg;
								if(msg){
											Ext.Msg.alert('提示',msg);
										}else{
											Ext.Msg.alert('提示','保存成功!');
											this.checkInsure(this.eventEmpId);
										}
//								
							}.createDelegate(this),
							failure:function(response,opts){
								var msg = Ext.decode(response.responseText).msg;
								Ext.Msg.alert("提示",msg);
							}
						});
	}

});
Ext.reg('SF.claim.insureTree',SF.claim.InsureTree);
	
/**
 * 出险管理-险种分配窗口
 */
SF.claim.event.InsureTreeWin = Ext.extend(Ext.Window,{
	constructor : function(config){
		config = Ext.apply({
			title:'险种分配',
			closeAction:'hide',
			modal:true,
			plain:true,
			resizable:false,
			domain:null,
    		autoHeight:true,
			width:600,
			layout:'fit',
			items:[{
				xtype:'SF.claim.insureTree'
			}]
			
		},config);
		SF.claim.event.InsureTreeWin.superclass.constructor.call(this,config);
	}
	
});
Ext.reg('SF.claim.event.insureTreeWin',SF.claim.event.InsureTreeWin);	

/*
 * 员工险险种分配表格
 */
SF.claim.event.EmpInsureGridPanel = Ext.extend(Ext.grid.GridPanel,{
		insureTreeWin: new SF.claim.event.InsureTreeWin(),
		constructor : function(config){
		var store = new Ext.data.JsonStore({
			url:'eventEmpSearch.action',
			root:'eventEmps',
			fields:['id','eventId','empCode', 'empName','idCard', 'personType','dutyName','deptId','sfDate','caseProcess','caseSection','casePoint','caseType','accidentType']
		});

		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			 defaults: {
		        width: 60
   			 },
			columns:[
				new Ext.grid.RowNumberer(),sm,{
					header:"工号",
					dataIndex:'empCode'
				},{
					header:"姓名",
					dataIndex:'empName'
				},{
					header:"身份证号码",
					width:80,
					dataIndex:'idCard'
				},{
					header:"用工形式",
					width:80,
					dataIndex:'personType',
					rendererCall :function(value){
						return personTypeData[value-1][1];
					}
				},{
					header:"工作岗位",
					dataIndex:'dutyName'
				},{
					header:"所属地区",
					width:70,
					dataIndex:'deptId'
				},{
					header:"入职时间",
					dataIndex:'sfDate',
					width:80,
					rendererCall:function(value){
						return value.split('T')[0];
					}
				},{
					header:"入职时长（月）",
					dataIndex:'sfDate',
					rendererCall :function(value){
						
					}
				},{
					header:"出险过程",
					width:150,
					dataIndex:'caseProcess'
				},
				// 出险时间段:1=(6：00-14：00)2=(14：01-22：00)3=(22：01-5：59)
					{
					header:"出险时间段",
					width:80,
					dataIndex:'caseSection',
					rendererCall :function(value){
						return caseSectionData[value-1][1];
					}
				},
				// 出险地点:1=上班途中2=下班途中3=工作地点4=家中5=其它
					{
					header:"出险地点",
					dataIndex:'casePoint',
					rendererCall :function(value){
						return casePointData[value-1][1];
					}
				},
				// 出险类型:1=工伤、2=职业病
				{
					header:"出险类型",
					dataIndex:'caseType',
					rendererCall :function(value){
						try{
							return caseTypeData[value-1][1];
						}catch(e){
							return value;
						}
					}
				},
				// 事故类型：1=摔伤2=扭伤3=拉伤4=划伤5=压伤6=砸伤7=碰伤8=撞伤9=烫伤10=烧伤
				// 11=磕伤12=咬伤13=单方交通事故14=双方交通事故15=多方交通事故16=残疾17=死亡
					{
					header:"事故类型",
					dataIndex:'accidentType',
					rendererCall :function(value){
						return accidentTypeData[value-1][1];
					}
				}	

			]
		});
		config = Ext.apply({
			store:store,
			sm:sm,
			cm:cm,
			stripeRows: true,
			loadMask:true,
			tbar : [{text:'险种分配',handler:this.insure.createDelegate(this)}]
		
		},config);
		SF.claim.event.EmpInsureGridPanel.superclass.constructor.call(this,config);
	},
	//TODO 险种分配
	insure:function(){
		var sm = this.getSelectionModel();
		if(sm.getCount() < 1){
			Ext.Msg.alert("提示","请选择要修改的记录!");
			return ;
		}
		if(sm.getCount() > 1){
			Ext.Msg.alert("提示","一次只能修改一条记录!");
			return ;
		}
		this.insureTreeWin.show();
		setTimeout(function(){
						this.insureTreeWin.items.get(0).checkInsure(sm.getSelected().id);
					}.createDelegate(this),1000);
		
	}

});
Ext.reg('SF.claim.event.empInsureGridPanel',SF.claim.event.EmpInsureGridPanel);




/**
 * 案件录入-个性化信息
 */
SF.claim.event.InsureTabPanel = Ext.extend(Ext.TabPanel,{
	constructor : function(config){
		config = Ext.apply({
			activeTab:0,
			items:[
			 	{
			 	   title:'员工险',
			       xtype:'SF.claim.event.empGridPanel'
			    }
		 	]
		},config);
		SF.claim.event.InsureTabPanel.superclass.constructor.call(this,config);
	}
});
Ext.reg('SF.claim.event.insureTabPanel',SF.claim.event.InsureTabPanel);

SF.claim.event.InsureTypeTabPanel = Ext.extend(Ext.TabPanel,{
	constructor : function(config){
		config = Ext.apply({
			activeTab:0,
			items:[
			 	{
			 	   title:'员工险',
			       xtype:'SF.claim.event.empInsureGridPanel'
			    }
		 	]
		},config);
		SF.claim.event.InsureTypeTabPanel.superclass.constructor.call(this,config);
	}
});
Ext.reg('SF.claim.event.insureTypeTabPanel',SF.claim.event.InsureTypeTabPanel);

/**
 * 案件录入窗口
 */
SF.claim.event.Win = Ext.extend(Ext.Window,{
	constructor : function(config){
		config = Ext.apply({
			id:'infoWin',
			closeAction:'hide',
			modal:true,
			plain:true,
			resizable:false,
			domain:null,
			width:800,
			height:600,
			layout:'border',
			items:[{
				region:'north',
				xtype:'SF.claim.event.formPanel'
			},
			  {
				region:'center',
				xtype:'SF.claim.event.insureTabPanel'
			  }
			],
			tbar:[{
				text:"保存草稿",
				iconCls:'save',
				handler : this.draft.createDelegate(this)
			},{
				text:"提交",
				iconCls:'apply',
				handler : this.commit.createDelegate(this)
			}]
			
		},config);
		SF.claim.event.Win.superclass.constructor.call(this,config);
	},
	getForm : function(){
		return  this.items.get(0).getForm();
	},
	getGrid: function(){
		return this.items.get(1).items.get(0);
	},
	getBaseParams : function(){
		var store = this.getGrid().store;
		var params = {};
		for(var i=0;i<store.getCount();i++){	
			var r = store.getAt(i).data;
			for(var j in r){
				params['eventEmps['+i+'].'+j] = r[j];
			}
		}
		return params;
	},
	clearFields:function(){
		this.getForm().setValues({
						'event.eventId':null,
						'event.reportContact':null,
						'event.caseOccourtime':null,
						'event.caseFindtime':null,
						'event.casePlace':null,
						'event.caseDesc':null});
//		this.getForm().clearInvalid();
	},
	doSubmit:function(url){
		var form = this.getForm();
		form.baseParams = this.getBaseParams();
		form.submit({
			url:url,
			waitTitle:"提示",
			waitMsg:"正在保存,请稍后......",
			success:function(form,action){
				var msg = action.result.msg;
				if(msg == null){
					Ext.Msg.alert("提示","保存成功!");
					Ext.getCmp('eventGrid').getStore().reload();
					this.hide();
				}else{
					Ext.Msg.alert("提示",msg);
				}
			}.createDelegate(this),
			failure:function(form,action){
				var msg = action.result.msg;
				Ext.Msg.alert("提示",msg);
			}
		});
	},
	save : function(){
		var form = this.getForm();
		if(!form.isValid()){
			Ext.Msg.alert("提示", "数据校验错误!");
			return ;
		}
		if(this.getGrid().store.getCount()<1){
			Ext.Msg.alert("提示", "员工险至少需要添加一条记录!");
			return ;
		}
		if(this.action == 'draft'){
			 this.doSubmit('eventDraft.action');
		}else if(this.action == 'commit'){
			Ext.MessageBox.confirm('提示','确定要提交吗?', function(result){
			if (result == 'yes') {
					this.doSubmit('eventCommit.action');
			}}.createDelegate(this));
		}
		
	},
	draft : function(){
		this.action = 'draft';
		this.save();
	},
	commit : function(){
		this.action = 'commit';
		this.save();
	}
});
Ext.reg('SF.claim.event.win',SF.claim.event.Win);

/**
 * 险种分配窗口
 */
SF.claim.event.InsureWin = Ext.extend(Ext.Window,{
	constructor : function(config){
		config = Ext.apply({
			id:'insureWin',
			title:'险种分配',
			closeAction:'hide',
			modal:true,
			plain:true,
			resizable:false,
			domain:null,
			width:800,
			height:600,
			layout:'border',
			items:[{
				region:'north',
				xtype:'SF.claim.event.formPanel'
			},
			  {
				region:'center',
				xtype:'SF.claim.event.insureTypeTabPanel'
			  }
			]
						
		},config);
		SF.claim.event.InsureWin.superclass.constructor.call(this,config);
	},
	getForm : function(){
		return  this.items.get(0).getForm();
	},
	getGrid: function(){
		return this.items.get(1).items.get(0);
	}
});
Ext.reg('SF.claim.event.insureWin',SF.claim.event.InsureWin);


/**
 * 主界面
 */
SF.claim.event.MainPanel = Ext.extend(Ext.Panel,{
	infoWin : new SF.claim.event.Win(),
	insureWin: new SF.claim.event.InsureWin(),
	constructor : function(config){
		config = Ext.apply({
			id:'mainPanel',
			frame:true,
			tbar : new Ext.Toolbar({
				items:['-'
					<app:isPermission code="/claim/eventSearch.action">
					,{
						text:"查询",
						iconCls:'search',
						handler : this.searchHandler.createDelegate(this)
					}
					</app:isPermission>
					<app:isPermission code="/claim/eventAdd.action">
					,{
						text:"案件录入",
						iconCls:'add',
						handler : this.addHandler.createDelegate(this)
					}
					</app:isPermission>
					]
			}),
			items:[{
				region:'north',
				xtype:'SF.claim.event.queryPanel'
			},{
				region:'center',
				xtype:'SF.claim.event.gridPanel'
			}]
		},config);
		SF.claim.event.MainPanel.superclass.constructor.call(this,config);
	},
	getForm : function(){
		return this.items.get(0).getForm();
	},
	getGrid : function(){
		return this.items.get(1);
	},
	searchHandler : function(){
		if(!this.getForm().isValid()){
			Ext.Msg.alert("提示", "数据校验失败!");
			return ;
		}
		var store = this.getGrid().getStore();
		store.load({
			callback : function(r,option,success){
				if(!success){
					store.removeAll();
					if(this.reader && this.reader.jsonData){
						Ext.Msg.alert("提示", this.reader.jsonData.msg);
					}
				}
			}
		});
	},
	
	addHandler : function(){
		//TODO reset方法有时不清空数据
		this.infoWin.clearFields();
		this.infoWin.getGrid().store.removeAll();
		this.infoWin.setTitle("案件录入");
		this.infoWin.show();
	}
	
});

Ext.reg('SF.claim.event.mainPanel',SF.claim.event.MainPanel);
